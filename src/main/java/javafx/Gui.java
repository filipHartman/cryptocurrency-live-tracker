package javafx;

import crypto.CurrencyContainer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class Gui extends JFrame {

    private JList threads;
    private CurrencyContainer currencyContainer;

    public Gui() {
        super("Cryptocurrency live tracker");
        setLayout(new FlowLayout());
        String [] toTest = new String[10];
        toTest[0] = "tteerhthtvtveyervrrrrrrrrrrrrrrrr6e5s6vy65e756e";
        toTest[1] = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww";
        toTest[2] = "tteerhthtvtveyerv6e5s6eeewvy65e756e";
        toTest[3] = "tteerhthtvtveyerv6e5sgg6vy65e756e";
        threads = new JList(toTest);
        threads.setFixedCellHeight(20);
        threads.setFixedCellWidth(700);


        threads.setVisibleRowCount(10);
        threads.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(threads));
    }
}
