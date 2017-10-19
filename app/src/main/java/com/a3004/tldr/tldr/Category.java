package com.a3004.tldr.tldr;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Category {
    public ArrayList<Article> articles;
    private XmlPullParserFactory xmlFactoryObj;

    public ArrayList<Article> getAllArticles() {
        return this.articles;
    }

    public void parseXML(String site){
        try{
            /* Make connection with the url*/
            URL url = new URL(site);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            /* Parse the XML */
            xmlFactoryObj = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObj.newPullParser();
            parser.setInput(is, null);
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                if (eventType == XmlPullParser.START_DOCUMENT){

                } else if (eventType == XmlPullParser.START_TAG){
                    Article article;
                    if (parser.getName().equals("item")){

                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
