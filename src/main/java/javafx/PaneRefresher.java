package javafx;

import crypto.CurrencyContainer;
import crypto.CurrencyThread;

import javax.swing.*;
import java.util.ArrayList;

public class PaneRefresher extends Thread{

    JTable cryptoPane;
    CurrencyContainer container;

    public PaneRefresher(JTable cryptoPane, CurrencyContainer container){
        this.cryptoPane = cryptoPane;
        this.container = container;
    }

    @Override
    public void run(){

        while(true){
            try{Thread.sleep(1000);}catch(InterruptedException e ){
                System.exit(901);
            }
            String[] headers = { "crypto", "eur", "usd" ,"pln" ,"cur1" ,"cur1" ,"cur1"};
            String[][] content = new String[container.getAllCurrentThreads().length][7];
            CurrencyThread thread;
            for(int i = 0; i < container.getAllCurrentThreads().length; i++){
                thread = container.getAllCurrentThreads()[i];
                String eur = thread.data.get("EUR");
                eur = eur!=null ? eur : "--";
                String dol = thread.data.get("USD");
                dol = dol!=null ? dol : "--";
                String pln = thread.data.get("PLN");
                pln = pln!=null ? pln : "--";
                content[i] = new String[]{thread.name, eur, dol, pln, "--", "--", "--"};
            }
            this.cryptoPane = new JTable(content, headers);
        }
    }
}
