public  class PaketInfo {
    private Typ typ;
    private String nazwa;
    private int cenaJeden,cenaUnderLimit,cenaOverLimit,limit,cenaAbonament,cenaBezAbonament;


    public PaketInfo(String nazwa,Typ typ, int cenaBezAbonament,int cenaAbonament) {
        this(nazwa, typ);
        this.cenaAbonament=cenaAbonament;
        this.cenaBezAbonament=cenaBezAbonament;
    }

    public PaketInfo(String nazwa,Typ typ) {
        this.nazwa = nazwa;
        this.typ=typ;
    }

    public PaketInfo(String nazwa,Typ typ, int cenaJeden, int cenaUnderLimit, int cenaOverLimit, int limit) {
       this(nazwa, typ);
        this.cenaUnderLimit = cenaUnderLimit;
        this.cenaOverLimit = cenaOverLimit;
        this.limit = limit;
    }

    public PaketInfo(String nazwa,Typ typ, int cenaUnderLimit, int cenaOverLimit, int limit) {
        this(nazwa, typ);
        this.cenaUnderLimit = cenaUnderLimit;
        this.cenaOverLimit = cenaOverLimit;
        this.limit = limit;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Typ getTyp() {
        return typ;
    }

    public int getCenaJeden() {
        return cenaJeden;
    }

    public int getCenaUnderLimit() {
        return cenaUnderLimit;
    }

    public int getCenaOverLimit() {
        return cenaOverLimit;
    }

    public int getLimit() {
        return limit;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getCenaAbonament() {
        return cenaAbonament;
    }

    public int getCenaBezAbonament() {
        return cenaBezAbonament;
    }

    public void setCenaJeden(int cenaJeden) {
        this.cenaJeden = cenaJeden;
    }

    public void setCenaUnderLimit(int cenaUnderLimit) {
        this.cenaUnderLimit = cenaUnderLimit;
    }

    public void setCenaOverLimit(int cenaOverLimit) {
        this.cenaOverLimit = cenaOverLimit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}