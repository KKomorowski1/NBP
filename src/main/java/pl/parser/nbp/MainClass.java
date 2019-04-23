package pl.parser.nbp;


import pl.parser.nbp.Connect.Connect;
import pl.parser.nbp.Reader.XmlReader;
import pl.parser.nbp.count.Count;
import pl.parser.nbp.formatter.InputFormatter;

import java.text.DecimalFormat;
import java.util.Scanner;


public class MainClass {
    public static void main(String[] args) {

        Connect connect = new Connect();

        XmlReader xmlReader = new XmlReader();
        InputFormatter inputFormatter = new InputFormatter();
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0000");
        Count count = new Count();


        String sc = scanner.nextLine();


        for (int i = 0; i < inputFormatter.listOfDir(sc).size(); i++) {

            connect.connectTo("http://www.nbp.pl/kursy/xml/" + inputFormatter.listOfDir(sc).get(i) + ".xml");
            xmlReader.read(connect, inputFormatter.formatToCurrency(sc));

        }


        count.Average(xmlReader, df);


        count.standardDeviation(xmlReader, df);


    }


}
