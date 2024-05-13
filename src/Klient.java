import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Klient {
    private final String identyficator;
    private double portfel;
    private Abonament abonament;
    private Koszyk koszyk;
     ListaZyczen listaZyczen;
    public static  ArrayList<Klient> klients=new ArrayList<>();

    public Klient(String identyficator, int portfel, Abonament abonament) {
        this.identyficator = identyficator;
        this.portfel = portfel;
        this.abonament = abonament;
        this.listaZyczen=new ListaZyczen(this.identyficator);
        this.koszyk=new Koszyk(this.identyficator);
        klients.add(this);
    }
    public void przepakuj(){
      ArrayList<Pakiet> list=ListaZyczen.mapListZyczen.get(this.identyficator);
        Iterator<Pakiet> iterator=list.iterator();
        while(iterator.hasNext()){
            Pakiet pakiet= iterator.next();
            if(pakiet.getCena()>0) {
                Koszyk.mapKoszyk.get(identyficator).add(pakiet);
                iterator.remove();
            }
        }
    }
    public void zaplac(MetodaPlatnosci metoda,boolean nadmiarReduce){
        this.koszyk.updateSum();
        double sum=this.pobierzKoszyk().getSuma();
        switch (metoda){
            case KARTA -> {
                if(!(sum+((sum/100)*2)<=this.portfel)) {
                    if(nadmiarReduce) {
                        while(sum+((sum/100)*2)>this.portfel){
                            koszyk.remoweOneCopy(koszyk.getLowestPricePakiet());
                        this.koszyk.updateSum();
                        }
                    }
                    else {
                        koszyk.clear();
                        this.koszyk.updateSum();
                    }
                }
                portfel-= (sum+((sum/100)*2));
            }
            case PRZELEW -> {
                if(sum>portfel) {
                    if(nadmiarReduce) {
                        while(sum+((sum/100)*2)>this.portfel){
                            koszyk.remoweOneCopy(koszyk.getLowestPricePakiet());
                        this.koszyk.updateSum();
                            sum=this.pobierzKoszyk().getSuma();
                        }
                    }
                    else {
                        koszyk.clear();
                        this.koszyk.updateSum();
                        sum=this.pobierzKoszyk().getSuma();
                    }
                }
                this.portfel-=sum;
            }
        }
        new Transakcja(this.identyficator,new ArrayList<>(Koszyk.mapKoszyk.get(identyficator)));

        this.listaZyczen.clear();
        this.koszyk.clear();
        Iterator<Pakiet> iterator=koszyk.listOfCopies.iterator();
        while(iterator.hasNext()){
            Pakiet pakiet= iterator.next();
            Koszyk.mapKoszyk.get(identyficator).add(pakiet);
        }


    }
    public void zwroc(Typ typ, String nazwa, int ilosc){
        ArrayList<Pakiet> list=Transakcja.mapTransakcji.get(identyficator);
        for (Pakiet pakiet : list){
            if(pakiet.getNazwa().equals(nazwa) && pakiet.getTyp().equals(typ) && ilosc<= pakiet.getIlosc()){
                Koszyk.mapKoszyk.get(identyficator).add(pakiet);
                portfel+=pakiet.getCena();
                pakiet.reduceIlosc();
            }
        }
    }
    public String pobierzPortfel(){
        DecimalFormat df=new DecimalFormat("#.##");
        return df.format(portfel);
    }
    public void dodaj(Pakiet pakiet){
        this.listaZyczen.add(pakiet);
    }
    public ListaZyczen pobierzListeZyczen(){
        return this.listaZyczen;
    }
    public Koszyk pobierzKoszyk(){
        return this.koszyk;
    }

    public String getIdentyficator() {
        return identyficator;
    }

    public Abonament getAbonament() {
        return abonament;
    }
}
