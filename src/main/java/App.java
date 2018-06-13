

// import WebData.URLCreatorService;

// public class App {
//     public static void main(String[] args) {
//         URLCreatorService dataUrl = new URLCreatorService();
//         System.out.println(dataUrl.createURL(dataUrl.DEFAULT_CRYPTOCURRENCY_SYMBOLS, new String[]{"USD", "EUR"}));

// import controllers.RootController;

// public class App {
//     public static void main(String[] args) {

//         RootController rootController = new RootController();
//         rootController.start();

//     }
// }

import crypto.CurrencyContainer;
import javafx.Gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    public static void main(String[] args) {
        Gui gui = new Gui(new CurrencyContainer());
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(650, 600);
        gui.setResizable(false);
        gui.setVisible(true);
    }
}