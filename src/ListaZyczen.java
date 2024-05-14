import java.util.ArrayList;
import java.util.HashMap;

public class ListaZyczen  {

     static HashMap<String,ArrayList<Pakiet>> mapListZyczen=new HashMap<>();
    private String klientID;

    public ListaZyczen(String klientID) {
       mapListZyczen.put(klientID,new ArrayList<>());
        this.klientID=klientID;
    }


    public void add(Pakiet pakiet){
        findCena(pakiet);
      mapListZyczen.get(klientID).add(pakiet);
    }
    public ArrayList<Pakiet> getListaZyczen(){
        return ListaZyczen.mapListZyczen.get(klientID);
    }
    private void findCena(Pakiet pakiet) {
        Cennik cennik=Cennik.pobierzCennik();
        PaketInfo paketInfo=cennik.findByName(pakiet.getNazwa());
        if(paketInfo!=null){
        switch (paketInfo.getTyp()){
            case KROTKI -> {
                if(pakiet.getIlosc()==1) pakiet.setCena(paketInfo.getCenaJeden());
                else if(pakiet.getIlosc()<= paketInfo.getLimit()) pakiet.setCena(paketInfo.getCenaUnderLimit());
                else if(pakiet.getIlosc() > paketInfo.getLimit())pakiet.setCena(paketInfo.getCenaOverLimit());

            }
            case SREDNI -> {
                if(pakiet.getIlosc()<= paketInfo.getLimit()) pakiet.setCena(paketInfo.getCenaUnderLimit());
                else pakiet.setCena(paketInfo.getCenaOverLimit());

            }
            case DLUGI -> { for(Klient klient: Klient.klients){
                if(klient.getIdentyficator().equals(klientID)) {
                    if(klient.getAbonament().equals(Abonament.TAK)) pakiet.setCena(paketInfo.getCenaAbonament());
                    else pakiet.setCena(paketInfo.getCenaBezAbonament());
                    }

                }
            }
            case DARMO -> {
                pakiet.setCena(0);

            }
        }
        } else pakiet.setCena(-1);


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
