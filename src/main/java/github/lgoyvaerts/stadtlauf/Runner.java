package github.lgoyvaerts.stadtlauf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static final String STARTLISTE = "src/main/resources/startliste.txt";

    public static void run() throws Exception {

        BufferedReader startlistBufferedReader = null;
        FileReader startlistFilereader = null;

        Kategorie[] kategories = new Kategorie[3];
        List<Person> personen = new ArrayList<>();

        //startlistBufferedReader = new BufferedReader(new FileReader(FILENAME));
        startlistFilereader = new FileReader(STARTLISTE);
        startlistBufferedReader = new BufferedReader(startlistFilereader);

        String sCurrentLine;

        while ((sCurrentLine = startlistBufferedReader.readLine()) != null) {
            String[] personDeatil;
            personDeatil = sCurrentLine.split("\t");

            Integer pStartnummer = Integer.parseInt(personDeatil[0]);
            Integer pKategorie = Integer.parseInt(personDeatil[1]);
            String pName = personDeatil[2];
            Person person = new Person(pStartnummer, pKategorie, pName);
            personen.add(person);
        }

        for (int i = 0; i < 3; i++) {
            kategories[i] = new Kategorie(i + 1);
        }

        setKategoriesSize(personen, kategories[0], kategories[1], kategories[2]);
        kategories = setKategoriesPersons(personen, kategories);


        for (Kategorie k : kategories) {
            k.setPersonsTime();
            k.bubbleSort();
            k.handleDNFs();
            k.handleTimes();
            k.handleRanks();
        }

        for (Kategorie k : kategories) {
            PrintWriter writer = new PrintWriter("src/main/resources/rangliste_" + k.getNummer() + ".txt", "UTF-8");
            writer.println("Kategorie " + k.getNummer() + " Rangliste\n");
            writer.println("\nRang\tSN\t\tZeit\t\tName");
            writer.println("----------------------------------------");
            int i = 1;
            for (Person p : k.getPersonen()) {
                writer.println(p.getRang()+"\t\t"+p.getStartnummer() + "\t\t" + p.getZeit() + "\t" + p.getName());
                i++;
            }
            writer.close();
        }


    }

    private static void setKategoriesSize(List<Person> personen, Kategorie kategorie1Personen, Kategorie kategorie2Personen, Kategorie kategorie3Personen) {
        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 0;
        for (Person p : personen) {
            if (p.getKategorie() == 1) {
                temp1++;
            } else if (p.getKategorie() == 2) {
                temp2++;
            } else if (p.getKategorie() == 3) {
                temp3++;
            }
        }

        kategorie1Personen.personen = new Person[temp1];
        kategorie2Personen.personen = new Person[temp2];
        kategorie3Personen.personen = new Person[temp3];
    }

    private static Kategorie[] setKategoriesPersons(List<Person> persons, Kategorie[] kategories) {
        Person[] kat1Pers = kategories[0].getPersonen();
        int k1 = 0;
        Person[] kat2Pers = kategories[1].getPersonen();
        int k2 = 0;
        Person[] kat3Pers = kategories[2].getPersonen();
        int k3 = 0;
        for (Person p : persons) {
            if (p.getKategorie() == 1) {
                kat1Pers[k1] = p;
                k1++;
            } else if (p.getKategorie() == 2) {
                kat2Pers[k2] = p;
                k2++;
            } else if (p.getKategorie() == 3) {
                kat3Pers[k3] = p;
                k3++;
            }
        }
        Kategorie[] temp = new Kategorie[3];
        int i = 0;
        for (Kategorie k : temp) {
            temp[i] = kategories[i];
            i++;
        }
        temp[0].setPersonen(kat1Pers);
        temp[1].setPersonen(kat2Pers);
        temp[2].setPersonen(kat3Pers);

        return temp;
    }

}
