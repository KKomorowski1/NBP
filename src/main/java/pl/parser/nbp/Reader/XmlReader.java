package pl.parser.nbp.Reader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pl.parser.nbp.Connect.Connect;

import java.util.ArrayList;
import java.util.List;

public class XmlReader {

    public ArrayList<Double> listaSprzedazy = new ArrayList<Double>();
    public ArrayList<Double> listaKupna = new ArrayList<Double>();

    public void read(Connect connect, String s) {


        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(connect.url);

            Element classElement = document.getRootElement();

            List<Node> nodes = document.selectNodes("/tabela_kursow/pozycja[kod_waluty = '" + s + "']");

            for (Node node : nodes) {
                String kupno = node.selectSingleNode("kurs_kupna").getText();
                String sprzedaz = node.selectSingleNode("kurs_sprzedazy").getText();


                listaSprzedazy.add(Double.parseDouble(sprzedaz.replace(",", ".")));
                listaKupna.add(Double.parseDouble(kupno.replace(",", ".")));


            }


        } catch (DocumentException e) {

        }

    }

}

