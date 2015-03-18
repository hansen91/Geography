package durnek.bakalarka.geography.classes;

/**
 * Created by Lukas on 12. 3. 2015.
 */
public class Kontinent {
   // private int _id;
    private String nazov;
    private String zlozenie;
    private long rozloha;
    private long populacia;

    public Kontinent(String nazov, String zlozenie, long rozloha, long populacia){
        super();
        this.nazov = nazov;
        this.zlozenie = zlozenie;
        this.rozloha = rozloha;
        this.populacia = populacia;
    }

    public String getNazov() {
        return nazov;
    }

    public String getZlozenie() {
        return zlozenie;
    }

    public long getRozloha() {
        return rozloha;
    }

    public long getPopulacia() {
        return populacia;
    }

   /* public int getId() {
        return _id;
    }*/


}
