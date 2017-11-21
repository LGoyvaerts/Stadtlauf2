package github.lgoyvaerts.stadtlauf;

public class Person {
    Integer startnummer;
    Integer kategorie;
    String name;
    String zeit;
    int rang;

    public Person() {
    }

    public Person(Person person) {
        this.startnummer = person.getStartnummer();
        this.kategorie = person.getKategorie();
        this.name = person.getName();
        this.zeit = person.getZeit();
    }

    public Person(Integer startnummer, Integer kategorie, String name) {
        this.startnummer = startnummer;
        this.kategorie = kategorie;
        this.name = name;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartnummer() {
        return startnummer;
    }

    public void setStartnummer(Integer startnummer) {
        this.startnummer = startnummer;
    }

    public Integer getKategorie() {
        return kategorie;
    }

    public void setKategorie(Integer kategorie) {
        this.kategorie = kategorie;
    }
}
