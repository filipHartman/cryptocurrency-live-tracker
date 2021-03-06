package view;

import WebData.CurrencyDataProvider;
import com.sun.java.swing.plaf.windows.WindowsButtonListener;
import crypto.CurrencyContainer;
import crypto.CurrencyThread;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Gui extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private boolean isChanging;

    public Gui(CurrencyContainer container) {
        super("Cryptocurrency Live Tracker");
        setLayout(new FlowLayout());

        JTextField logo = new JTextField("SUPER DUPER CRYPTO LIVE TRACKER");
        logo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        logo.setEditable(false);
        add(logo);

        String[] currencies = container.getAllCryptoCurrencies();
        JComboBox dropdownMenu = new JComboBox(currencies);
        dropdownMenu.setAlignmentX(0);
        add(dropdownMenu);

        JInternalFrame checkboxPanel = new JInternalFrame();
        checkboxPanel.setVisible(true);
        checkboxPanel.setLayout(new FlowLayout());
        checkboxPanel.add(new JCheckBox("EUR"));
        checkboxPanel.add(new JCheckBox("USD"));
        checkboxPanel.add(new JCheckBox("PLN"));
        checkboxPanel.add(new JCheckBox("JPY"));

        checkboxPanel.setBorder(null);
        BasicInternalFrameUI bi = (BasicInternalFrameUI)checkboxPanel.getUI();
        bi.setNorthPane(null);
        add(checkboxPanel);

        JButton followButton = new JButton("Follow");
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selectedCurrencies = new ArrayList<>();
                for(Component c : checkboxPanel.getContentPane().getComponents()){
                    JCheckBox checkbox = (JCheckBox) c;
                    if(checkbox.isSelected()){
                        selectedCurrencies.add(checkbox.getText());
                    }
                }

                container.add(dropdownMenu.getSelectedItem().toString(), listToArr(selectedCurrencies), tableModel);
                tableModel.fireTableDataChanged();
            }
        });
        add(followButton);

        isChanging = false;

        String[] headers = { "CRYPTO", "EUR", "USD" ,"PLN" ,"JPY" ,"cur1" ,"cur2"};
        tableModel = new DefaultTableModel(headers,0){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        tableModel.addRow(headers);
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(isChanging) return;
                String[][] content = new String[container.getAllCurrentThreads().length][7];
                CurrencyThread thread;
                for(int i = 0; i < container.getAllCurrentThreads().length; i++){
                    thread = container.getAllCurrentThreads()[i];
                    String eur = thread.data.get("\"EUR\"");
                    eur = eur!=null ? eur : "--";
                    String dol = thread.data.get("\"USD\"");
                    dol = dol!=null ? dol : "--";
                    String pln = thread.data.get("\"PLN\"");
                    pln = pln!=null ? pln : "--";
                    String yen = thread.data.get("\"JPY\"");
                    yen = yen!=null ? yen : "--";
                    content[i] = new String[]{thread.name, eur, dol, pln, yen, "--", "--"};
                }

                updateTable(content);
            }
        });
        table = new JTable(tableModel);
        add(table);

        JButton deleteButton = getDeleteButton(container);
        add(deleteButton);

    }

    public JButton getDeleteButton(CurrencyContainer container) {
        JButton deleteButton = new JButton("Clear");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                String [][] content = new String[0][];
                updateTable(content);
            }
        });
        return deleteButton;
    }

    public static String[] listToArr(ArrayList<String> list){
        String[] data = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            data[i] = list.get(i);
        }
        return data;
    }

    public void updateTable(String[][] content){
        isChanging = true;
        for(int i = tableModel.getRowCount()-1; i>0; i--){
            try {
                tableModel.removeRow(i);
            }catch(IndexOutOfBoundsException e){
                // Table started refreshing right after adding new element - actual rowCount is +1 to the state of table where the old table
                System.out.println("!-- Read the comment in GUI.java:122;\nTL;DR: refresh operation was too fast");
            }
        }

        for(int i = 0; i<content.length; i++) {
            tableModel.addRow(content[i]);
        }

        this.table = new JTable(tableModel);
        isChanging = false;
    }
}
