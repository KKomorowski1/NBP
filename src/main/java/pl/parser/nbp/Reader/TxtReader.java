package pl.parser.nbp.Reader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TxtReader {


    public String readTxt(String dirLink) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {


            URL url = new URL(dirLink);

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
            in.close();


        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

        return stringBuilder.toString();

    }


}
