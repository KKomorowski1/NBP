package pl.parser.nbp.formatter;


import pl.parser.nbp.Reader.TxtReader;

import java.util.ArrayList;
import java.util.List;


public class InputFormatter {

    TxtReader txtReader = new TxtReader();

    public String formatStartDate(String s) {
        String b = s.replace("-", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b).delete(0, 6).delete(6, s.length());


        return stringBuilder.toString();
    }

    public String formatEndDate(String s) {
        String b = s.replace("-", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b).delete(0, 15);


        return stringBuilder.toString();
    }

    public String formatToStartYear(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s).delete(0, 4);
        stringBuilder.delete(4, s.length());


        return stringBuilder.toString();
    }


    public String formatToCurrency(String s) {

        StringBuilder stringBuilder = new StringBuilder();


        String formatedDate = stringBuilder.append(s).delete(3, s.length()).toString();

        return formatedDate;
    }


    public String formatStartToDir(String s) {
        int b;
        String numberOfNbpTable = "";

        // Rok poczatkowy do dir
        String dir = txtReader.readTxt("https://www.nbp.pl/kursy/xml/dir" + formatToStartYear(s) + ".txt");

        // ostatnie 6 cyfr z dir
        String fromScanner = formatStartDate(s);

        // index poczatkowy 6 cyfr z dir
        b = dir.indexOf(fromScanner);

        if (b < 0) {
            System.out.println("w ten dzien nie byly zbierane pomiary");
        } else {

            // caly link z dir
            numberOfNbpTable = dir.subSequence(b - 5, b + 6).toString();
        }
        return numberOfNbpTable;

    }

    public String formatEndToDir(String s) {
        int b;
        String numberOfNbpTable = "";

        // Rok poczatkowy do dir
        String dir = txtReader.readTxt("https://www.nbp.pl/kursy/xml/dir" + formatToStartYear(s) + ".txt");

        // ostatnie 6 cyfr z dir
        String fromScanner = formatEndDate(s);

        // index poczatkowy 6 cyfr z dir
        b = dir.indexOf(fromScanner);

        if (b < 0) {
            System.out.println("w ten dzien nie byly zbierane pomiary");
        } else {

            // caly link z dir
            numberOfNbpTable = dir.subSequence(b - 5, b + 6).toString();
        }
        return numberOfNbpTable;

    }

    public String rangeInDir(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        String dir = txtReader.readTxt("https://www.nbp.pl/kursy/xml/dir" + formatToStartYear(s) + ".txt");

        String b = formatStartToDir(s);

        String c = formatEndToDir(s);
        // Zwraca index poczatkowy daty startowej w pliku dir
        int a = dir.indexOf(b);
        // Zwraca index poczatkowy daty koncowej w pliku dir
        int d = dir.indexOf(c);

        stringBuilder.append(dir).delete(0, a).delete(d - a + 11, dir.length());


        return stringBuilder.toString();
    }


    public <T> ArrayList<T> removeDuplicats(ArrayList<T> list) {

        // Utworzenie nowego ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Przejscie przez pierwsza liste
        for (T element : list) {

            // Jezeli nie ma lementu w newList to go dodaj
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // Zwroc nowa liste
        return newList;
    }

    public List listOfDir(String s) {
        ArrayList<Integer> list = new ArrayList();

        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> urlList = new ArrayList<String>();

        String x = rangeInDir(s);
        int z;
        for (int i = 0; i <= x.length(); i++) {
            // dodanie do listy indexow litery "c" z dir
            z = stringBuilder.append(x).indexOf("c", i);
            list.add(z);
        }
        ArrayList<Integer> newList = removeDuplicats(list);
        newList.remove(newList.size() - 1);


        for (int i = 0; i < newList.size(); i++) {

            urlList.add(x.substring(newList.get(i), newList.get(i) + 11));

        }


        return urlList;
    }


}



