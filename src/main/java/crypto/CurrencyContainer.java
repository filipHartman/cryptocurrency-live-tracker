package crypto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyContainer {

    private HashMap<String, CurrencyThread> threads = new HashMap<>();

    public void add(String threadName){
        this.threads.put(threadName, new CurrencyThread(threadName, new HashMap<>()));
        this.threads.get(threadName).run();
    }

    public void remove(String threadName){
        this.threads.remove(threadName);
    }

    public boolean contains(String threadName){
        return threads.containsKey(threadName);
    }

    public List<CurrencyThread> getAllCurrentThreads(){
        List<CurrencyThread> all = new ArrayList<>();
        for(String name : this.threads.keySet()){
            all.add(this.threads.get(name));
        }
        return all;
    }
}
