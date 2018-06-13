package crypto;

import java.util.HashMap;

public class CurrencyThread extends Thread {

    public  String name;
    public HashMap<String, String> data;

    public CurrencyThread(String cryptoName, HashMap<String, String> data){
        super();
        this.name = cryptoName;
        this.data = data;
    }

    public void run(){
        System.out.println("started thread " + this.name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String realCurrencyName : data.keySet()) {
            sb.append(" ").append(realCurrencyName).append(" : ").append(data.get(realCurrencyName)).append(" ");
        }
        return "Name: " + name +
                sb.toString();
    }
}
