package pl.parser.nbp.reader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pl.parser.nbp.connect.Connect;

import java.util.ArrayList;
import java.util.List;

public class XmlReader {

    public ArrayList<Double> listOfSalesPrices = new ArrayList<Double>();
    public ArrayList<Double> listOfPrices = new ArrayList<Double>();

    public void read(Connect connect, String s) {

        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(connect.url);
            List<Node> nodes = document.selectNodes("/tabela_kursow/pozycja[kod_waluty = '" + s + "']");

            for (Node node : nodes) {
                String price = node.selectSingleNode("kurs_kupna").getText();
                String priceOfSale = node.selectSingleNode("kurs_sprzedazy").getText();

                listOfSalesPrices.add(Double.parseDouble(priceOfSale.replace(",", ".")));
                listOfPrices.add(Double.parseDouble(price.replace(",", ".")));
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}

