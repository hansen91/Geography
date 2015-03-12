package durnek.bakalarka.geography.classes;

/**
 * Created by Lukas on 12. 3. 2015.
 */
public class KontinentInfo {
    public String nazov;
    public String zlozenie;
    public long rozloha;
    public long populacia;

    public KontinentInfo(String nazov, String zlozenie, long rozloha, long populacia){
        super();
        this.nazov = nazov;
        this.zlozenie = zlozenie;
        this.rozloha = rozloha;
        this.populacia = populacia;
    }
}
