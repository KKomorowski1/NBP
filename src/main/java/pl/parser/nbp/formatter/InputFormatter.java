package pl.parser.nbp.formatter;

import pl.parser.nbp.reader.TxtReader;

import java.util.ArrayList;
import java.util.List;

public class InputFormatter {

    private TxtReader txtReader = new TxtReader();

    private String formatStartDate(String s) {
        String b = s.replace("-", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b).delete(0, 6).delete(6, s.length());

        return stringBuilder.toString();
    }

    private String formatEndDate(String s) {
        String b = s.replace("-", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b).delete(0, 15);

        return stringBuilder.toString();
    }

    private String formatToStartYear(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s).delete(0, 4);
        stringBuilder.delete(4, s.length());

        return stringBuilder.toString();
    }

    public String formatToCurrency(String s) {

        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.append(s).delete(3, s.length()).toString();
    }

    private String formatStartToDir(String s) {
        String numberOfNbpTable = "";

        // Rok poczatkowy do dir
        String dir = txtReader.readTxt("https://www.nbp.pl/kursy/xml/dir" + formatToStartYear(s) + ".txt");

        // ostatnie 6 cyfr z dir
        String fromScanner = formatStartDate(s);

        // index poczatkowy 6 cyfr z dir
        int b = dir.indexOf(fromScanner);

        if (b < 0) {
            System.out.println("w ten dzien nie byly zbierane pomiary");
        } else {

            // caly link z dir
            numberOfNbpTable = dir.subSequence(b - 5, b + 6).toString();
        }
        return numberOfNbpTable;

    }

    private String formatEndToDir(String s) {
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

    private String rangeInDir(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String dir = txtReader.readTxt("https://www.nbp.pl/kursy/xml/dir" + formatToStartYear(s) + ".txt");
        String b = formatStartToDir(s);
        String c = formatEndToDir(s);
        int indexOfStartDate = dir.indexOf(b);
        int indexOfEndDate = dir.indexOf(c);

        stringBuilder.append(dir).delete(0, indexOfStartDate).delete(indexOfEndDate - indexOfStartDate + 11, dir.length());

        return stringBuilder.toString();
    }

    private  <T> ArrayList<T> removeDuplicats(ArrayList<T> list) {

        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {

            if (!newList.contains(element)) {

                newList.add(element);
            }
        }
        return newList;
    }

    public List<String> listOfDir(String s) {
        ArrayList<Integer> list;
        list = new ArrayList<>();
        String x = rangeInDir(s);
        int z;
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> urlList = new ArrayList<>();

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



