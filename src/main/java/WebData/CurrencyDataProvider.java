package WebData;

import crypto.CurrencyThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class CurrencyDataProvider {
    private static URLCreatorService urlCreator = new URLCreatorService();

    private static final String[] DEFAULT_CRYPTOCURRENCY_SYMBOLS = new String[] {
            "BTC", "ETH", "XRP", "BCH", "ADA", "XLM", "NEO", "LTC", "EOS", "XEM"
    };

    public static String[] getDefaultCryptocurrencySymbols() {
        return DEFAULT_CRYPTOCURRENCY_SYMBOLS;
    }

    public HashMap<String, String> getCurrencyData(String cryptoCurrency, String[] realCurrencies) {
        HashMap<String, String> currencies = new HashMap<>();
        String urlAddress = urlCreator.createURL(new String[]{cryptoCurrency}, realCurrencies);
        URL dataUrl;
        try {
            dataUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(dataUrl.openStream()));
            String inputLine;
            String webData = "";
            if ((inputLine = in.readLine()) != null) {
                webData = inputLine;
                System.out.println(webData);
            }
            String[] allCryptoCurrenciesData = webData.substring(1, webData.length() - 1).split("},");
            for (String crypto : allCryptoCurrenciesData) {

                String[] splitedCurrencies = crypto.split(":\\{");
                String cryptoCurrencyName = splitedCurrencies[0];
                String[] realCurrenciesData = splitedCurrencies[1].split(",");
                for (String realCurrency : realCurrenciesData) {
                    String[] splitedRealCurrency = realCurrency.split(":");
                    currencies.put(splitedRealCurrency[0], splitedRealCurrency[1].replace("}",""));
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
