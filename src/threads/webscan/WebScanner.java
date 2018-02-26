package threads.webscan;

public class WebScanner {
    private String[] urls = new String[]{"https://www.dr.dk/",
            "https://www.borsen.dk", "https://www.cnn.com", "https://www.b.dk", "https://www.ing.dk"
    ,"https://www.nytimes.com","https://www.bbc.co.uk","https://www.cnbc.com/world"};
    public static void main(String[] args) {
        new WebScanner();

    }

    public WebScanner(){
        for(String url: urls) {
            new Thread(new URLReader(url, "Trump", this)).start();
        }
//        URLReader urlReader = new URLReader("Trump", this); // Uden tråde, kører det dobbelt så langsomt
//        for(String url: urls) {
//            urlReader.setUrl(url);
//            urlReader.scan();
//        }
    }

    public void printResult(String company, String url, String result){
        System.out.println("At: " + url + " key: " + company + " result: " + result);
    }
}
