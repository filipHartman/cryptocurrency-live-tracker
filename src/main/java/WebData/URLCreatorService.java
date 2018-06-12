package WebData;

public class URLCreatorService {
    public static final String SOURCE_URL = "https://min-api.cryptocompare.com/data/";
    public static final String PRICES_COMMAND = "pricemulti";
    public static final String AFTER_COMMAND_SYMBOL = "?";
    public static final String CURRENCIES_SEPARATOR = ",";
    public static final String SYMBOLS_OF_INTEREST = "fsyms";
    public static final String SYMBOLS_TO_CONVERT = "tsyms";
    public static final String INTERSET_CONVERT_SEPARATOR = "&";
    public static final String[] DEFAULT_CRYPTOCURRENCY_SYMBOLS = new String[] {
            "BTC", "ETH", "XRP", "BCH", "ADA", "XLM", "NEO", "LTC", "EOS", "XEM"
    };

    public String createURL(String[] cryptocurrencySymbols, String[] currenciesSymbolsToConvert) {
        StringBuilder sb = new StringBuilder(SOURCE_URL);
        sb.append(PRICES_COMMAND).append(AFTER_COMMAND_SYMBOL);

        sb.append(SYMBOLS_OF_INTEREST).append("=");
        for(String cryptocurrency: cryptocurrencySymbols) {
            sb.append(cryptocurrency).append(CURRENCIES_SEPARATOR);
        }
        sb.append(INTERSET_CONVERT_SEPARATOR);

        sb.append(SYMBOLS_TO_CONVERT).append("=");
        for(String currency: currenciesSymbolsToConvert) {
            sb.append(currency).append(CURRENCIES_SEPARATOR);
        }
        return sb.toString();
    }
}
