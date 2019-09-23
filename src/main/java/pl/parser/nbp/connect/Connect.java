package pl.parser.nbp.connect;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Connect {

    private Document doc;
    public URL url;

    public void connectTo(String connectionUrl) {

        try {
            url = new URL(connectionUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(urlConnection.getInputStream());

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}