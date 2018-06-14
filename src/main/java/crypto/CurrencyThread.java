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
    private String revision;
    private float lastValue;

    public CurrencyThread(String cryptoName, String[] realCurrencies, DefaultTableModel table){
        super();
        this.name = cryptoName;
        this.data = new HashMap<>();
        this.realCurrencies = realCurrencies;
        this.table = table;
        this.revision = " ";
        this.lastValue = 0;
    }

    public void run(){

        while(true){
            this.data = dataProvider.getCurrencyData(name, realCurrencies);
            table.fireTableDataChanged();
            checkValueShift();
            try {
                Thread.sleep(10000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkValueShift() {
        float currentValue = Float.valueOf(data.get("\"PLN\""));
        if(currentValue > lastValue) {
            setRevision("      +");
            setLastValue(currentValue);
        } else if(currentValue < lastValue) {
            setRevision("      -");
            setLastValue(currentValue);
        } else {
            setRevision(" ");
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

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public void setLastValue(float lastValue) {
        this.lastValue = lastValue;
    }

    public String getRevision() {
        return revision;
    }
}
