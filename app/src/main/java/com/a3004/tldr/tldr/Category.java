package com.a3004.tldr.tldr;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Category {
    private ArrayList<Article> articles = new ArrayList<>();

    public ArrayList<Article> getArticles() { return this.articles; }

    public void parseXML(String site){
        try{
            /* Make connection with the url*/
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
                //System.out.println("Article" + (i+1) + " " + titleLine.getTextContent());
                String titleString = titleLine.getTextContent();

                //get links
                NodeList link = element.getElementsByTagName("link");
                Element linkLine = (Element) link.item(0);
                //System.out.println("Link" + (i+1) + " " + linkLine.getTextContent());
                String linkString = linkLine.getTextContent();


                //getting descriptions
                NodeList description = element.getElementsByTagName("description");
                Element descLine = (Element) description.item(0);
                //System.out.println("Description" + (i+1) + descLine.getTextContent());
                String descString = descLine.getTextContent();

                Article article = new Article(linkString, titleString, descString);
                articles.add(article);
            }




        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main (String[] args){
        Category NYT;
        NYT = new Category();

        NYT.parseXML("http://rss.nytimes.com/services/xml/rss/nyt/Education.xml");

        for (int i = 0; i < NYT.getArticles().size(); i++){
            System.out.println(NYT.getArticles().get(i).getArticleID());
        }
    }
}
