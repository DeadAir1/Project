import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Koszyk extends Lista {
    private double suma;
    static HashMap<String, ArrayList<Pakiet>> mapKoszyk=new HashMap<>();
    ArrayList<Pakiet> listOfCopies;


    public Koszyk(String klientID) {
        super(klientID);
        mapKoszyk.put(klientID,new ArrayList<>());
        listOfCopies=new ArrayList<>();

    }
    public ArrayList<Pakiet> pobierzKoszyk(){
        return Koszyk.mapKoszyk.get(klientID);
    }


    public double getSuma() {
        return suma;
    }

    public Pakiet getLowestPricePakiet(){
        Iterator<Pakiet> iterator=pobierzKoszyk().iterator();
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
        for(Pakiet pakiet : this.pobierzKoszyk()){
            suma+= pakiet.getCena() * pakiet.getIlosc();
        }
    }
    public void remowePakiet(Pakiet pakiet){
        Koszyk.mapKoszyk.remove(klientID).remove(pakiet);
    }

    public void clear(){
        pobierzKoszyk().clear();
    }

    @Override
    public String toString() {
        String str=new String();
        str +=klientID + ": \n";
        if(pobierzKoszyk().isEmpty()) return str + " -- pusto";
        else {
            for (Pakiet pakiet :pobierzKoszyk()){
                str+= pakiet.toString() ;
            }

        }
        return str;
    }
}