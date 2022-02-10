/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.controller.classes.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author epilif3sotnas
 */
public class Coin_Info {
    private final static String SITE = "http://min-api.cryptocompare.com/data/price?fsym=";
    
    public Float getPrice (String coin, float units) throws MalformedURLException, IOException {
        return 100.0f;
        // error SSLHandshakeException
//        String url = SITE + coin + "&tsyms=USD"
//                + "&api_key=05f56f7cacb7ba7dba8cb8c22b023ae3d24f8506ac73b2dc8aef3400accd741a";
//        String charset = "UTF-8";
//        URLConnection con = new URL(url).openConnection();
//        con.setRequestProperty("Accept-Charset", charset);
//        InputStream response = con.getInputStream();
//        try ( Scanner scanner = new Scanner(response)) {
//            String responseBody = scanner.useDelimiter("\\A").next();
//            System.out.println(responseBody);
//            JSONObject obj = new JSONObject(responseBody);
//            return obj.getFloat("USD") * units;
//        }
    }
}
