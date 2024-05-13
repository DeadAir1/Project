import java.util.HashMap;

public class Cennik {
   private static Cennik obiekt;

   private HashMap<Typ, PaketInfo> map=new HashMap<>();

   private Cennik(){

   }
   public static  Cennik pobierzCennik(){
       if(obiekt==null)
           obiekt=new Cennik();
       return obiekt;
   }
   public void dodaj(Typ typ,String nazwa,int cenaJeden,int cenaUnder,int cenaOver,int limit){
      map.put(typ,new PaketInfo(nazwa,typ,cenaJeden,cenaUnder,cenaOver,limit));
   }
   public void dodaj(Typ typ,String nazwa,int cenaUnder,int cenaOver,int limit){
      map.put(typ,new PaketInfo(nazwa,typ,cenaUnder,cenaOver,limit));
   }
   public void dodaj(Typ typ,String nazwa,int cenaBezAbonament,int cenaAbonament){
      map.put(typ,new PaketInfo(nazwa,typ,cenaBezAbonament,cenaAbonament));
   }
   public void dodaj(Typ typ,String nazwa){
      map.put(typ,new PaketInfo(nazwa,typ));
   }
   public PaketInfo findByName(String name){
      for(Typ t: map.keySet())
         if(map.get(t).getNazwa().toString().equals(name))
            return map.get(t);
      return null;
   }



}
