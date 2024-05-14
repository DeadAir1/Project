public abstract class Pakiet {
    private String nazwa;
    private int ilosc;
    private int cena;
    private int IDPakiet;
    protected Typ typ;



    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }



    public Pakiet(String nazwa, int ilosc) {
        this.nazwa = nazwa;
        this.ilosc=ilosc;
    }

    public void reduceIlosc() {
        this.ilosc--;

    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getIlosc() {
        return ilosc;
    }
    public int getCena() {
        return cena;
    }

    public int getIDPakiet() {
        return IDPakiet;
    }

    @Override
    public String toString() {
        String str=getClass().toString();
        String tab[]=str.split(" ");
        return nazwa + ", "+ "typ:"+ tab[1]+", " + ilosc + " okresy, cena " + (cena>0? cena : "brak") + "\n";
    }

    public Typ getTyp() {
        return typ;
    }
}
class Krotki extends Pakiet{

    public Krotki(String nazwa, int ilosc) {
        super(nazwa, ilosc);
        this.typ=Typ.KROTKI;
    }
}
class Sredni extends Pakiet{

    public Sredni(String nazwa, int ilosc) {
        super(nazwa, ilosc);
        this.typ=Typ.SREDNI;
    }
}
class Dlugi extends Pakiet{

    public Dlugi(String nazwa, int ilosc) {
        super(nazwa, ilosc);
        this.typ=Typ.DLUGI;
    }
}
class Darmo extends Pakiet{

    public Darmo(String nazwa, int ilosc) {
        super(nazwa, ilosc);
        this.typ=Typ.DARMO;
    }
}
