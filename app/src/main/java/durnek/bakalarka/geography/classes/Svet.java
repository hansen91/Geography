package durnek.bakalarka.geography.classes;



import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Lukas on 13. 3. 2015.
 */
public class Svet {
    private ArrayList<Kontinent> kontinenty;

    public Svet(ArrayList<Kontinent> kontinenty){
        kontinenty = new ArrayList<Kontinent>();
    }

    public ArrayList<Kontinent> getKontinenty() {
        return kontinenty;
    }
}
