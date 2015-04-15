package durnek.bakalarka.geography.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lukas on 12. 3. 2015.
 */
public class Stat implements Parcelable {
    private int _id;
    private String nazov;
    private String hlMesto;
    private long rozloha;
    private long populacia;
    private String[] mesta;
    private String[] jazyky;
    private String[] mena;

    public Stat(String nazov,String hlMesto,long rozloha,long populacia,String[] mesta,
                String[] jazyky, String[] mena){
        super();
        this.nazov = nazov;
        this.hlMesto = hlMesto;
        this.rozloha = rozloha;
        this.populacia = populacia;
        this.mesta = mesta;
        this.jazyky = jazyky;
        this.mena = mena;
    }

    public Stat(String nazov){
        this.nazov = nazov;
    }

    public Stat(Parcel in){
        nazov = in.readString();
        hlMesto = in.readString();
        rozloha = in.readLong();
        populacia = in.readLong();
      /*  mesta = new ArrayList<String>();
        in.readList(mesta, null);
        jazyky = new ArrayList<String>();
        in.readList(jazyky, null);
        mena = new ArrayList<String>();
        in.readList(mena, null);*/

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
