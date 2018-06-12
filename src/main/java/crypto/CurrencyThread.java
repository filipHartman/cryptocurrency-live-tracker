package crypto;

import java.util.HashMap;

public class CurrencyThread extends Thread {

    public  String name;
    public HashMap<String, String> data;

    public CurrencyThread(String cryptoName){
        super();
        this.name = cryptoName;
        this.data = new HashMap<>();
    }

    public void run(){
        System.out.println("started thread " + this.name);
    }
}
