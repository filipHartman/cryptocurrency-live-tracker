import crypto.CurrencyContainer;
import view.Gui;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Gui gui = new Gui(new CurrencyContainer());
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(650, 600);
        gui.setResizable(false);
        gui.setVisible(true);
    }
}