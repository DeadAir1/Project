import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Koszyk  {

    private double suma;
    private String klientID;
    private Pakiet pakiet;
    static HashMap<String, ArrayList<Pakiet>> mapKoszyk=new HashMap<>();
    ArrayList<Pakiet> listOfCopies;


    public Koszyk(String klientID) {
        this.klientID=klientID;
    mapKoszyk.put(klientID,new ArrayList<>());
    listOfCopies=new ArrayList<>();

    }
    public ArrayList<Pakiet> getKoszyk(){
        return Koszyk.mapKoszyk.get(klientID);
    }

    private void add(Pakiet pakiet){
        this.pakiet=pakiet;
        mapKoszyk.get(klientID).add(this.pakiet);

    }

    public double getSuma() {
        return suma;
    }

    public Pakiet getLowestPricePakiet(){
        Iterator<Pakiet> iterator=getKoszyk().iterator();
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

    public ArrayList<Pakiet> getListOfCopies() {
        return listOfCopies;
    }

    public void updateSum(){
        suma=0;
        for(Pakiet pakiet : this.getKoszyk()){
            suma+= pakiet.getCena() * pakiet.getIlosc();
        }
   }
   public void remowePakiet(Pakiet pakiet){
        Koszyk.mapKoszyk.remove(klientID).remove(pakiet);
   }

   public void clear(){
     getKoszyk().clear();
   }

    @Override
    public String toString() {
        String str=new String();
        str +=klientID + ": \n";
        if(getKoszyk().isEmpty()) return str + " -- pusto";
        else {
            for (Pakiet pakiet :getKoszyk()){
                str+= pakiet.toString() ;
            }

        }
        return str;
    }
}
