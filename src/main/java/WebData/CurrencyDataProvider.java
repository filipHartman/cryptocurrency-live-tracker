package WebData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public class CurrencyDataProvider {
    private static URLCreatorService urlCreator = new URLCreatorService();

    public static void main(String[] args) throws Exception {
        String url = urlCreator.createURL(urlCreator.DEFAULT_CRYPTOCURRENCY_SYMBOLS, new String[]{"USD", "EUR", "PLN"});

        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        String result = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            result = inputLine;
        in.close();
        System.out.println(result);
        String newResult = result.substring(1,result.length()-1);
        System.out.println(newResult);
        System.out.println(Arrays.toString(newResult.split("},")));
        for(String cryptoCurrency: newResult.split("},")) {
            String[] splitedCurrencies = cryptoCurrency.split(":\\{");
            String cryptoCurrencyName = splitedCurrencies[0];
            String realCurencies = splitedCurrencies[1];
            System.out.println(cryptoCurrencyName);
            System.out.println(realCurencies);
        }
    }
}
