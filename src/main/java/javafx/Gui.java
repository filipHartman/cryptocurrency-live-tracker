package javafx;

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

    private JList threads;
    private JTable table;
    private DefaultTableModel tableModel;
    private boolean isChanging;

    public Gui(CurrencyContainer container) {
        super("Cryptocurrency Live Tracker");
        setLayout(new FlowLayout());

        threads = new JList(container.getAllCurrentThreads());
        threads.setFixedCellHeight(20);
        threads.setFixedCellWidth(630);

        threads.setVisibleRowCount(10);
        threads.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JTextField logo = new JTextField("SUPER DUPER CRYPTO LIVE TRACKER");
        logo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        logo.setEditable(false);
        add(logo);

        String[] currencies = {"BTC", "ETH"};
        JComboBox dropdownMenu = new JComboBox(currencies);
        dropdownMenu.setAlignmentX(0);
        add(dropdownMenu);

        JInternalFrame checkboxPanel = new JInternalFrame();
        checkboxPanel.setVisible(true);
        checkboxPanel.setLayout(new FlowLayout());
        checkboxPanel.add(new JCheckBox("EUR"));
        checkboxPanel.add(new JCheckBox("USD"));
        checkboxPanel.add(new JCheckBox("PLN"));

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
                //System.out.println(dropdownMenu.getSelectedItem() + " : " + new StringBuilder().append(selectedCurrencies).toString());

                container.add(dropdownMenu.getSelectedItem().toString(), listToArr(selectedCurrencies), tableModel);
                tableModel.fireTableDataChanged();
            }
        });
        add(followButton);

        isChanging = false;
        tableModel = new DefaultTableModel(0,7);
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(isChanging) return;
                String[] headers = { "crypto", "eur", "usd" ,"pln" ,"cur1" ,"cur1" ,"cur1"};
                String[][] content = new String[container.getAllCurrentThreads().length][7];
                CurrencyThread thread;
                for(int i = 0; i < container.getAllCurrentThreads().length; i++){
                    thread = container.getAllCurrentThreads()[i];
                    System.out.println(thread);
                    String eur = thread.data.get("\"EUR\"");
                    eur = eur!=null ? eur : "--";
                    String dol = thread.data.get("\"USD\"");
                    dol = dol!=null ? dol : "--";
                    String pln = thread.data.get("\"PLN\"");
                    pln = pln!=null ? pln : "--";
                    content[i] = new String[]{thread.name, eur, dol, pln, "--", "--", "--"};
                }

                updateTable(content, headers);
            }
        });
        table = new JTable(tableModel);

        JScrollPane cryptoPane = new JScrollPane(threads);
        add(table);
        //(new PaneRefresher(table,container)).start();
    }

    public static String[] listToArr(ArrayList<String> list){
        String[] data = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            data[i] = list.get(i);
        }
        return data;
    }

    public void updateTable(String[][] content, String[] headers){
        isChanging = true;
        for(int i =tableModel.getRowCount()-1;i>0; i--){
            tableModel.removeRow(i);
        }

        for(int i = 0; i<content.length; i++) {
            tableModel.addRow(content[i]);
        }

        this.table = new JTable(tableModel);
        isChanging = false;
    }
}
