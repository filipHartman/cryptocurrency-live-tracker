package crypto;

import WebData.CurrencyDataProvider;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class CurrencyThread extends Thread {

    private static CurrencyDataProvider dataProvider = new CurrencyDataProvider();
    public  String name;
    private String[] realCurrencies;
    public HashMap<String, String> data;
    private DefaultTableModel table;

    public CurrencyThread(String cryptoName, String[] realCurrencies, DefaultTableModel table){
        super();
        this.name = cryptoName;
        this.data = new HashMap<>();
        this.realCurrencies = realCurrencies;
        this.table = table;
    }

    public void run(){

        while(true){
            this.data = dataProvider.getCurrencyData(name, realCurrencies);
            table.fireTableDataChanged();
            try {
                Thread.sleep(10000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String realCurrencyName : data.keySet()) {
            sb.append(" ").append(realCurrencyName).append(" : ").append(data.get(realCurrencyName)).append(" ");
        }
        return "Name: " + name + sb.toString();
    }
}
