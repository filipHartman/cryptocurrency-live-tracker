package WebData;

class URLCreatorService {
    static final String SOURCE_URL = "https://min-api.cryptocompare.com/data/";
    static final String PRICES_COMMAND = "pricemulti";
    static final String AFTER_COMMAND_SYMBOL = "?";
    static final String CURRENCIES_SEPARATOR = ",";
    static final String SYMBOLS_OF_INTEREST = "fsyms";
    static final String SYMBOLS_TO_CONVERT = "tsyms";
    static final String INTERSET_CONVERT_SEPARATOR = "&";


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
