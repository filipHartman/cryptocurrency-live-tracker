package crypto;

public class CurrencyThread extends Thread {

    public  String name;

    public CurrencyThread(String cryptoName){
        super();
        this.name = cryptoName;
    }

    public void run(){
        System.out.println("started thread " + this.name);
    }
}
