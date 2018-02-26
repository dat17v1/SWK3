package threads.webscan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
// JSOUP https://jsoup.org/download
public class URLReader implements Runnable{

    private String url;
    private String company;
    private WebScanner webScanner;
    public URLReader(String url, String company, WebScanner webScanner){
        this.url = url;
        this.company = company;
        this.webScanner = webScanner;
    }

    public URLReader(String company, WebScanner webScanner){
        this.company = company;
        this.webScanner = webScanner;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        scan();
    }

    public void scan() {
        try {
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

            String inputLine;
            String out = "";
            while ((inputLine = in.readLine()) != null) {
                out += inputLine + "\n";
                //  System.out.println(inputLine);
            }

            in.close();
            Document doc = Jsoup.parse(out);
            String document = doc.body().text().toLowerCase();
            // System.out.println(document);
            int pos = document.indexOf(company.toLowerCase());
            if (pos >= 0) {
                int end = document.length() > pos + 50 ? pos + 50 : document.length();
                webScanner.printResult(company, url, document.substring(pos, end));
            } else {
                webScanner.printResult(company, url, "no match. length:" + document.length() + " out:" + out.length());
            }
        }catch (MalformedURLException e){
            System.out.println("bad address");
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}