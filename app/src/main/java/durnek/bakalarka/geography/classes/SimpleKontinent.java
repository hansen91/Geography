package durnek.bakalarka.geography.classes;

/**
 * Created by Lukas on 12. 3. 2015.
 */
public class SimpleKontinent {
    public int id_kontinentu;
    private StringBuilder nazov;
    // int poc_statov;

    public SimpleKontinent(int id_kontinentu){
        super();
        this.id_kontinentu = id_kontinentu;
        this.nazov = new StringBuilder();

    }

    /**
     * Pridá názov kontinentu
     * @param meno
     */
    public void pridajKontinent(String meno) {
        if (nazov.length() > 0)
            nazov.append(" ");
        nazov.append(meno);
    }

    /**
     * Vráti zoznam mien
     * @return
     */
    public String dajKontinenty() {
        return nazov.toString();
    }
}
