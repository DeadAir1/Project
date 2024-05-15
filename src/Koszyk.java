import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Koszyk extends Lista {
    private double suma;
    static HashMap<String,Koszyk> mapKoszyk=new HashMap<>();
    ArrayList<Pakiet> listOfCopies;
    private ArrayList<Pakiet> listKoszyk=new ArrayList<>();

    public ArrayList<Pakiet> getListKoszyk() {
        return listKoszyk;
    }

    public Koszyk(String klientID) {
        super(klientID);
        mapKoszyk.put(klientID,this);
        listOfCopies=new ArrayList<>();


    }
    public Koszyk pobierzKoszyk(){
        return Koszyk.mapKoszyk.get(klientID);
    }


    public double getSuma() {
        return suma;
    }

    public Pakiet getLowestPricePakiet(){
        Iterator<Pakiet> iterator=pobierzKoszyk().getListKoszyk().iterator();
        Pakiet pakietLowestPrice=iterator.next();
        while(iterator.hasNext()){
            Pakiet pakiet= iterator.next();
            if(pakiet.getCena()< pakietLowestPrice.getCena()) pakietLowestPrice=pakiet;
        }
        return pakietLowestPrice;
    }
    public void remoweOneCopy(Pakiet pakiet){
        if(pakiet.getIlosc()>1) {
            pakiet.reduceIlosc();
            listOfCopies.add(pakiet);
        }else remowePakiet(pakiet);

    }

    public void updateSum(){
        suma=0;
        for(Pakiet pakiet : this.getListKoszyk()){
            suma+= pakiet.getCena() * pakiet.getIlosc();
        }
    }
    public void remowePakiet(Pakiet pakiet){
        Koszyk.mapKoszyk.remove(klientID).getListKoszyk().remove(pakiet);
    }

    public void clear(){
        pobierzKoszyk().getListKoszyk().clear();
    }

    @Override
    public String toString() {
        String str=new String();
        str +=klientID + ": \n";
        if(getListKoszyk().isEmpty()) return str + " -- pusto";
        else {
            for (Pakiet pakiet :getListKoszyk()){
                str+= pakiet.toString() ;
            }

        }
        return str;
    }
}