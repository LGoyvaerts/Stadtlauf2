package github.lgoyvaerts.stadtlauf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Kategorie {
    private static final String MESSRESULTATE = "src/main/resources/messresultate.txt";
    Integer nummer;
    Person[] personen;

    public Kategorie() {
    }

    public Kategorie(Integer nummer, Person[] personen) throws Exception {
        this.nummer = nummer;
        this.personen = personen;
    }

    public Kategorie(Integer nummer) {
        this.nummer = nummer;
    }

    public Person[] getPersonen() {
        return personen;

    }

    public void setPersonen(Person[] personen) {
        this.personen = personen;
    }

    public Integer getNummer() {
        return nummer;
    }

    public void setNummer(Integer nummer) {
        this.nummer = nummer;
    }

    public void setPersonsTime() throws Exception {

        //startlistBufferedReader = new BufferedReader(new FileReader(FILENAME));
        FileReader startlistFilereader = new FileReader(MESSRESULTATE);
        BufferedReader startlistBufferedReader = new BufferedReader(startlistFilereader);

        String sCurrentLine;

        while ((sCurrentLine = startlistBufferedReader.readLine()) != null) {
            String[] personDeatil;
            personDeatil = sCurrentLine.split("\t");

            Integer pStartnummer = Integer.parseInt(personDeatil[0]);
            String pZeit = personDeatil[1];

            int i = 0;
            for (Person p : personen) {
                if (Objects.equals(p.getStartnummer(), pStartnummer)) {
                    p.setZeit(pZeit);
                }
                i++;
            }

        }
    }

    public void bubbleSortByTimes() throws Exception {

        for (Person p : personen) {
            if (p.getZeit() != null) {
                char[] splitted = p.getZeit().toCharArray();
                String newTime = splitted[0] + "" + splitted[1] + "" + splitted[3] + "" + splitted[4] + "" + splitted[6] + "" + splitted[7];
                p.setZeit(newTime);
            } else {
                p.setZeit("999999");
            }
        }

        Person temp = null;
        for (int i = 0; i < personen.length; i++) {
            for (int j = 1; j < (personen.length - i); j++) {
                if (Integer.parseInt(personen[j - 1].getZeit()) > Integer.parseInt(personen[j].getZeit())) {
                    temp = personen[j - 1];
                    personen[j - 1] = personen[j];
                    personen[j] = temp;
                }

            }
        }
    }

    public void bubbleSortByNames() throws Exception{
        Person temp;
        for (int a = 0; a < personen.length - 1; a++) {
            for (int j = 0; j < personen.length - 1; j++) {
                if (personen[j].getName().compareTo(personen[j + 1].getName()) > 0) {
                    temp = personen[j];
                    personen[j] = personen[j + 1];
                    personen[j + 1] = temp;
                }
            }
        }
    }

    public void handleTimes() {
        for (Person p : personen) {
            if (!p.getZeit().equals("DNF*\t")) {
                char[] repaired = p.getZeit().toCharArray();
                String newTime = repaired[0] + "" + repaired[1] + ":" + repaired[2] + "" + repaired[3] + ":" + repaired[4] + "" + repaired[5];
                p.setZeit(newTime);
            }
        }
    }

    public void handleDifferences() throws Exception{
        for (Person p : personen) {
            if (!p.getZeit().equals("DNF*\t")) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date1 = format.parse(p.getZeit());
                Date date2 = format.parse(personen[0].getZeit());
                Long difference = date2.getTime() - date1.getTime();

                String result = String.format("%02d min, %02d sec",
                        TimeUnit.MILLISECONDS.toMinutes(difference),
                        TimeUnit.MILLISECONDS.toSeconds(difference) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference))
                );


                String resultFormatted = String.format("%-16s", result);
                p.setDifferenz(resultFormatted);
            }
        }
    }

    public void handleDNFs() {
        for (Person p : personen) {
            if (p.getZeit().equals("999999")) {
                p.setZeit("DNF*\t");
                p.setDifferenz("Did not run ...\t");
            }
        }
    }

    public void handleRanks() {
        int i = 1;
        for (Person p : personen) {
            p.setRang(i);
            i++;
        }
    }
}
