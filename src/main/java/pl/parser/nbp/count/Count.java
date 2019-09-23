package pl.parser.nbp.count;

import pl.parser.nbp.reader.XmlReader;

import java.text.DecimalFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Count {


    public void standardDeviation(XmlReader xmlReader, DecimalFormat df) {

        double sredniaSprzedazy, step1, step2, step3, step4, sume = 0.0, sumaSprzedazy = 0.0;

        for (int i = 0; i < xmlReader.listOfSalesPrices.size(); i++) {

            sumaSprzedazy += xmlReader.listOfSalesPrices.get(i);

        }
        sredniaSprzedazy = sumaSprzedazy / xmlReader.listOfSalesPrices.size();

        for (int i = 0; i < xmlReader.listOfSalesPrices.size(); i++) {
            step1 = xmlReader.listOfSalesPrices.get(i) - sredniaSprzedazy;
            step2 = pow(step1, 2.0);
            sume += step2;


        }
        step3 = sume / xmlReader.listOfSalesPrices.size();

        step4 = sqrt(step3);

        System.out.println(df.format(step4));
    }

    public void Average(XmlReader xmlReader, DecimalFormat df) {
        double sumaKupna = 0.0;
        double sredniaKupna;


        for (int i = 0; i < xmlReader.listOfPrices.size(); i++) {

            sumaKupna += xmlReader.listOfPrices.get(i);

        }
        sredniaKupna = sumaKupna / xmlReader.listOfPrices.size();
        System.out.println(df.format(sredniaKupna));
    }


}
