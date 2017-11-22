package com.a3004.tldr.tldr;


import com.prof.rssparser.Article;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Category {
    private String title;
    private ArrayList<Article> articles = new ArrayList<>();

    public ArrayList<Article> getArticles() { return this.articles; }
    public String getTitle() {
        return title;
    }

    public Category() {

    }

    public Category(String title, ArrayList<Article> articles) {
        this.title = title;
        this.articles = articles;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    /*public void parseXML(String site){
        try{

            URL url = new URL(site);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            //get titles of articles
            for (int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);

                //get titles
                NodeList title = element.getElementsByTagName("title");
                Element titleLine = (Element) title.item(0);
                String titleString = titleLine.getTextContent();

                //get links
                NodeList link = element.getElementsByTagName("link");
                Element linkLine = (Element) link.item(0);
                String linkString = linkLine.getTextContent();


                //getting descriptions
                NodeList description = element.getElementsByTagName("description");
                Element descLine = (Element) description.item(0);
                String descString = descLine.getTextContent();

                Article article = new Article(linkString, titleString, descString);
                articles.add(article);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }*/
}
