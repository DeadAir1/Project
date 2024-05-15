import java.util.ArrayList;
import java.util.HashMap;

public class ListaZyczen extends Lista {

    static HashMap<String,ListaZyczen> mapListZyczen=new HashMap<>();
    private ArrayList<Pakiet> list=new ArrayList<>();


    public ListaZyczen(String klientID) {
        super(klientID);
        mapListZyczen.put(klientID,this);
    }


    public void add(Pakiet pakiet){
        findCena(pakiet);
        mapListZyczen.get(klientID).getList().add(pakiet);
    }
    public ListaZyczen getListaZyczen(){
        return this;
    }

    public ArrayList<Pakiet> getList() {
        return list;
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
        this.getListaZyczen().getList().clear();
    }
    @Override
    public String toString() {
        String str=new String();
        str +=klientID + ": \n";
        if(list.isEmpty()) return str + "-- pusto";
        else {
            for (Pakiet pakiet :list){
                str+= pakiet.toString() ;
            }

        }
        return str;
    }



}