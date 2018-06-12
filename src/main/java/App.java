import WebData.URLCreatorService;

public class App {
    public static void main(String[] args) {
        URLCreatorService dataUrl = new URLCreatorService();
        System.out.println(dataUrl.createURL(dataUrl.DEFAULT_CRYPTOCURRENCY_SYMBOLS, new String[]{"USD", "EUR"}));
    }
}
