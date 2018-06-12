package javafx;

import crypto.CurrencyContainer;
import crypto.CurrencyThread;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

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
        add(new JScrollPane(threads));

        item1 = new JTextField(10);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = e.getActionCommand();
                toTest.add(new CurrencyThread(input));
            }
        });
        add(item1);

        button = new JButton("Add crypto");
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        System.out.println("dziala");
                    }
                }
        );
        add(button);

    }
}
