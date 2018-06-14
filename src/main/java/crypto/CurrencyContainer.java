package crypto;

import WebData.CurrencyDataProvider;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyContainer {

    private HashMap<String, CurrencyThread> threads = new HashMap<>();

    public void add(String threadName, String[] realCurrencies, DefaultTableModel table){
        CurrencyThread thread = new CurrencyThread(threadName,realCurrencies, table);
        thread.start();
        this.threads.put(threadName, thread);
    }

    public void remove(String threadName){
        this.threads.remove(threadName);
    }

    public void removeAll() {
        threads.clear();
    }

    public boolean contains(String threadName){
        return threads.containsKey(threadName);
    }

    public CurrencyThread[] getAllCurrentThreads(){
        CurrencyThread[] all = new CurrencyThread[threads.size()];
        int i = 0;
        for(String name : this.threads.keySet()){
            all[i++] = this.threads.get(name);
        }
        return all;
    }

    public String [] getAllCryptoCurrencies() {
        return CurrencyDataProvider.getDefaultCryptocurrencySymbols();
    }

}
