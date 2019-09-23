package pl.parser.nbp.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TxtReader {

    public String readTxt(String dirLink) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        try {

            URL url = new URL(dirLink);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();

    }
}
