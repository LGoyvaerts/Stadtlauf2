package github.lgoyvaerts.stadtlauf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Searcher {

    public static void search() throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Bestand suchen: ");
        String input = in.nextLine().toLowerCase();
        if (input.equals("0") || input.equals("")) {
            System.exit(0);
        }
        for (int i = 1; i <= 3; i++) {


            BufferedReader startlistBufferedReader = null;
            FileReader startlistFilereader = null;

            startlistFilereader = new FileReader("src/main/resources/rangliste_" + i + ".txt");
            startlistBufferedReader = new BufferedReader(startlistFilereader);

            String sCurrentLine;

            while ((sCurrentLine = startlistBufferedReader.readLine()) != null) {


                if (sCurrentLine.toLowerCase().contains(input.toLowerCase())) {
                    System.out.println(sCurrentLine);
                }

            }
        }


    }
}
