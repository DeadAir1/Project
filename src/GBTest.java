
public class GBTest {

    // cena pakietów danego typu z koszyka
    static int cena(Koszyk k, String nazwaPakietu) {
        for (Pakiet pakiet : k.getListKoszyk() ){
            if(pakiet.getNazwa().equals(nazwaPakietu)){
                return pakiet.getIlosc() * pakiet.getCena();
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        // cennik
        Cennik cennik = Cennik.pobierzCennik();

        // dodawanie nowych cen do cennika
        cennik.dodaj(Typ.KROTKI, "5GB", 20, 15, 10, 3); 	// 20 zł za 1 okres (jednorazowy),
        // 15 zł za 1 okres (jeśli klient zakupi od 2 do 3 kolejnych okresów)
        // 10 zł za 1 okres (jeśli klient zakupi co najmniej 4 kolejne okresy)

        cennik.dodaj(Typ.SREDNI, "10GB", 25, 20, 2);	// 25 zł za 1 okres (jeśli klient zakupi do 2 kolejnych okresów),
        // 20 zł za 1 okres (jeśli klient zakupi od 3 kolejnych okresów)

        cennik.dodaj(Typ.DLUGI, "30GB", 35, 30);		// 30 zł za 1 okres jeśli klient ma abonament,
        // 35 zł za 1 okres jeśli klient nie ma abonamentu

        cennik.dodaj(Typ.DARMO, "1GB");			// darmowy dostęp (tylko na 1 okres oraz tylko 1 raz na klienta)


        // Klient lte deklaruje kwotę 200 zł na zamównienia; TAK oznacza, że klient posiada abonament
        Klient lte = new Klient("lte", 200, Abonament.TAK);

        // Klient lte dodaje do listy życzeń różne pakiety:
        // "5GB" typu krotkoterminowego na 4 kolejne okresy
        // "10GB" typu średnioterminowego na 3 kolejne okresy,
        // "30GB" typu długoterminowego na 2 kolejne okresy,
        // "2GB" typu darmowego na 2 kolejne okresy (ale może mieć tylko 1 okres)
        lte.dodaj(new Krotki("5GB", 4));
        lte.dodaj(new Sredni("10GB", 3));
        lte.dodaj(new Dlugi("30GB", 2));
        lte.dodaj(new Darmo("2GB", 2));

        // Lista życzeń klienta lte
        ListaZyczen listaLte = lte.pobierzListeZyczen();

        System.out.println("Lista życzeń klienta " + listaLte);

        // Przed płaceniem, klient przepakuje pakiety z listy życzeń do koszyka (po uprzednim wyczyszczeniu).
        // Możliwe, że na liście życzeń są pakiety niemające ceny w cenniku,
        // w takim przypadku nie trafiłyby do koszyka
        Koszyk koszykLte = lte.pobierzKoszyk();
        lte.przepakuj();

        // Co jest na liście życzeń klienta lte
        System.out.println("Po przepakowaniu, lista życzeń klienta " + lte.pobierzListeZyczen());

        // Co jest w koszyku klienta lte
        System.out.println("Po przepakowaniu, koszyk klienta " + koszykLte);

        // Ile wynosi cena wszystkich pakietów typu długoterminowego w koszyku klienta lte
        System.out.println("Pakiety 5GB w koszyku klienta lte kosztowały:  " + cena(koszykLte, "5GB"));

        // Klient zapłaci...
        lte.zaplac(MetodaPlatnosci.KARTA, false);	// płaci kartą płatniczą, prowizja 2%
        // true oznacza, że w przypadku braku środków aplikacja sama odłoży nadmiarowe pakiety,
        // false oznacza rezygnację z płacenia razem z wyczyszczeniem koszyka i listy życzeń

        // Ile klientowi lte zostało pieniędzy?
        System.out.println("Po zapłaceniu, klientowi lte zostało: " + lte.pobierzPortfel() + " zł");

        // Mogło klientowi zabraknąć srodków, wtedy, opcjonalnie, pakiety mogą być odkładane,
        // w przeciwnym przypadku, koszyk jest pusty po zapłaceniu
        System.out.println("Po zapłaceniu, koszyk klienta " + lte.pobierzKoszyk());
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykLte);

        // Teraz przychodzi klient gsm,
        // deklaruje 65 zł na zamówienia
        Klient gsm = new Klient("gsm", 65, Abonament.NIE);

        // Zamówił za dużo jak na tę kwotę
        gsm.dodaj(new Sredni("10GB", 2));
        gsm.dodaj(new Dlugi("30GB", 1));

        // Co klient gsm ma na swojej liście życzeń
        System.out.println("Lista życzeń klienta " + gsm.pobierzListeZyczen());

        Koszyk koszykGsm = gsm.pobierzKoszyk();
        gsm.przepakuj();

        // Co jest na liście życzeń klienta gsm
        System.out.println("Po przepakowaniu, lista życzeń klienta " + gsm.pobierzListeZyczen());

        // A co jest w koszyku klienta gsm
        System.out.println("Po przepakowaniu, koszyk klienta " + gsm.pobierzKoszyk());

        // klient gsm płaci
        gsm.zaplac(MetodaPlatnosci.PRZELEW, true);	// płaci przelewem, bez prowizji

        // Ile klientowi gsm zostało pieniędzy?
        System.out.println("Po zapłaceniu, klientowi gsm zostało: " + gsm.pobierzPortfel() + " zł");

        // Co zostało w koszyku klienta gsm (za mało pieniędzy miał)
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykGsm);

        gsm.zwroc(Typ.DLUGI, "30GB", 1);	// zwrot (do koszyka) pakietu (na 1 okres) z ostatniej transakcji

        // Ile klientowi gsm zostało pieniędzy?
        System.out.println("Po zwrocie, klientowi gsm zostało: " + gsm.pobierzPortfel() + " zł");

        // Co zostało w koszyku klienta gsm
        System.out.println("Po zwrocie, koszyk klienta " + koszykGsm);

    }
}