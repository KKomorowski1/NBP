package pl.parser.nbp.count;

import pl.parser.nbp.Reader.XmlReader;

import java.text.DecimalFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Count {


    public void standardDeviation(XmlReader xmlReader, DecimalFormat df) {

        double sredniaSprzedazy, step1, step2, step3, step4, sume = 0.0, sumaSprzedazy = 0.0;

        for (int i = 0; i < xmlReader.listaSprzedazy.size(); i++) {

            sumaSprzedazy += xmlReader.listaSprzedazy.get(i);

        }
        sredniaSprzedazy = sumaSprzedazy / xmlReader.listaSprzedazy.size();

        for (int i = 0; i < xmlReader.listaSprzedazy.size(); i++) {
            step1 = xmlReader.listaSprzedazy.get(i) - sredniaSprzedazy;
            step2 = pow(step1, 2.0);
            sume += step2;


        }
        step3 = sume / xmlReader.listaSprzedazy.size();

        step4 = sqrt(step3);

        System.out.println(df.format(step4));
    }

    public void Average(XmlReader xmlReader, DecimalFormat df) {
        double sumaKupna = 0.0;
        double sredniaKupna;


        for (int i = 0; i < xmlReader.listaKupna.size(); i++) {

            sumaKupna += xmlReader.listaKupna.get(i);

        }
        sredniaKupna = sumaKupna / xmlReader.listaKupna.size();
        System.out.println(df.format(sredniaKupna));
    }


}
