import java.util.ArrayList;
import java.util.HashMap;

public class Transakcja {
    private String klientID;
    private ArrayList<Pakiet> zakup;
    static HashMap<String, ArrayList<Pakiet>> mapTransakcji=new HashMap<>();

    public  Transakcja(String klientID, ArrayList<Pakiet> zakup) {
        this.klientID = klientID;
        this.zakup = zakup;
        Transakcja.mapTransakcji.put(klientID,zakup);
    }

    public ArrayList<Pakiet> getZakup() {
        return zakup;
    }
}
