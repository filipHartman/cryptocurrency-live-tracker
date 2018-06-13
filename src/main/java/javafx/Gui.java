package javafx;

import crypto.CurrencyContainer;
import crypto.CurrencyThread;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Gui extends JFrame {

    private JList threads;
    private CurrencyContainer container;
    private JTextField item1;
    private JButton button;

    public Gui() {
        super("Cryptocurrency live tracker");
        this.container = new CurrencyContainer();
        setLayout(new FlowLayout());
        List<CurrencyThread> toTest = container.getAllCurrentThreads();

        threads = new JList(toTest.toArray());
        threads.setFixedCellHeight(20);
        threads.setFixedCellWidth(700);

        threads.setVisibleRowCount(10);
        threads.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JTextField logo = new JTextField("SUPER DUPER CRYPTO LIVE TRACKER");
        logo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        logo.setEditable(false); // powiekszyc logo na cala szerokosc
        add(logo);

        String[] currencies = {"BTC", "ETH"};
        JComboBox dropdownMenu = new JComboBox(currencies);
        dropdownMenu.setAlignmentX(0);
        add(dropdownMenu);

        JInternalFrame checkboxPanel = new JInternalFrame();
        checkboxPanel.setVisible(true);
        checkboxPanel.setLayout(new GridLayout(2,2));
        checkboxPanel.add(new JCheckBox("EUR"));
        checkboxPanel.add(new JCheckBox("USD"));
        checkboxPanel.add(new JCheckBox("PLN"));

        checkboxPanel.setBorder(null);
        BasicInternalFrameUI bi = (BasicInternalFrameUI)checkboxPanel.getUI();
        bi.setNorthPane(null);
        add(checkboxPanel);

        JButton followButton = new JButton("Follow");
        add(followButton);

        add(new JScrollPane(threads));
    }
}
