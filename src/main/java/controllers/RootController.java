package controllers;

import crypto.CurrencyContainer;
import crypto.CurrencyThread;

import java.util.Scanner;

public class RootController {

    private Scanner scanner = new Scanner(System.in);
    private CurrencyContainer currencyContainer = new CurrencyContainer();

    public void start(){
        String input;
        String[] slicedInput;
        while(true){
            if(scanner.hasNextLine()){
                input = scanner.nextLine();
                slicedInput = input.split(" ");
                switch(slicedInput[0]){
                    case "start":
                    case "START":
                        if(slicedInput.length < 2) break;
                        currencyContainer.add(slicedInput[1]);
                        break;
                    case "stop":
                    case "STOP":
                        if(slicedInput.length < 2) break;
                        currencyContainer.remove(slicedInput[1]);
                        break;
                    case "check":
                    case "CHECK":
                        for(CurrencyThread t : currencyContainer.getAllCurrentThreads()){
                            System.out.println(t.name);
                        }
                        break;
                    default:
                        System.out.println("unknown command");
                        break;
                }
            }
        }
    }
}
