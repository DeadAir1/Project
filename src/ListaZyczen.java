import java.util.ArrayList;
import java.util.HashMap;

public class ListaZyczen  {

     static HashMap<String,ArrayList<Pakiet>> mapListZyczen=new HashMap<>();
    private String klientID;
    private Pakiet pakiet;

    public ListaZyczen(String klientID) {
       mapListZyczen.put(klientID,new ArrayList<>());
        this.klientID=klientID;
    }


    public void add(Pakiet pakiet){
        this.pakiet=pakiet;
        findCena();
      mapListZyczen.get(klientID).add(this.pakiet);
    }
    public ArrayList<Pakiet> getListaZyczen(){
        return ListaZyczen.mapListZyczen.get(klientID);
    }
    private void findCena() {
        Cennik cennik=Cennik.pobierzCennik();
        PaketInfo paketInfo=cennik.findByName(this.pakiet.getNazwa());
        if(paketInfo!=null){
        switch (paketInfo.getTyp()){
            case KROTKI -> {
                if(this.pakiet.getIlosc()==1) this.pakiet.setCena(paketInfo.getCenaJeden());
                else if(this.pakiet.getIlosc()<= paketInfo.getLimit()) this.pakiet.setCena(paketInfo.getCenaUnderLimit());
                else if(this.pakiet.getIlosc() > paketInfo.getLimit())this.pakiet.setCena(paketInfo.getCenaOverLimit());

            }
            case SREDNI -> {
                if(this.pakiet.getIlosc()<= paketInfo.getLimit()) this.pakiet.setCena(paketInfo.getCenaUnderLimit());
                else this.pakiet.setCena(paketInfo.getCenaOverLimit());

            }
            case DLUGI -> { for(Klient klient: Klient.klients){
                if(klient.getIdentyficator().equals(klientID)) {
                    if(klient.getAbonament().equals(Abonament.TAK)) this.pakiet.setCena(paketInfo.getCenaAbonament());
                    else this.pakiet.setCena(paketInfo.getCenaBezAbonament());
                    }

                }
            }
            case DARMO -> {
                this.pakiet.setCena(0);

            }
        }
        } else this.pakiet.setCena(-1);


    }
    public void clear(){
        this.getListaZyczen().clear();
    }


    @Override
    public String toString() {
        String str=new String();
        str +=klientID + ": \n";
        if(getListaZyczen().isEmpty()) return str + "-- pusto";
        else {
            for (Pakiet pakiet :getListaZyczen()){
            str+= pakiet.toString() ;
        }

        }
    return str;
}
}
