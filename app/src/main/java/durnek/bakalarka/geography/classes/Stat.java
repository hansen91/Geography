package durnek.bakalarka.geography.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 12. 3. 2015.
 */
public class Stat
        //implements Parcelable
      {
    private int _id;
    private String nazov;
    private String hlMesto;
    private long rozloha;
    private long populacia;
    /*private List<String> mesta;
    private List<String> jazyky;
    private List<String> mena;*/
    private String mesta;
    private String jazyky;
    private String mena;

    public Stat(String nazov,String hlMesto,long rozloha,long populacia,String mesta,
                String jazyky, String mena){
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

    public Stat(int _id, String nazov) {
        this._id = _id;
        this.nazov = nazov;
    }

    public Stat(){

    }

/*
    public Stat(Parcel in){
        nazov = in.readString();
        hlMesto = in.readString();
        rozloha = in.readLong();
        populacia = in.readLong();
        mesta = new ArrayList<String>();
        in.readList(mesta, null);
        jazyky = new ArrayList<String>();
        in.readList(jazyky, null);
        mena = new ArrayList<String>();
        in.readList(mena, null);

    }*/

    public int get_id() {
        return _id;
    }

    public String getNazov() {
        return nazov;
    }

    public String getHlMesto() {
        return hlMesto;
    }

    public long getRozloha() {
        return rozloha;
    }

    public long getPopulacia() {
        return populacia;
    }


    public String getMesta() {
        return mesta;
    }

    public String getJazyky() {
        return jazyky;
    }

    public String getMena() {
        return mena;
    }
    /*
    @Override
    public int describeContents() {
        return 0;
    }*/

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
/*
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nazov);
        dest.writeString(hlMesto);
        dest.writeLong(rozloha);
        dest.writeLong(populacia);
        dest.writeList(mesta);
        dest.writeList(jazyky);
        dest.writeList(mena);

    }

    public static final Parcelable.Creator<Stat> CREATOR
        = new Parcelable.Creator<Stat>(){
        public Stat createFromParcel(Parcel in){    return new Stat(in); }

        public Stat[] newArray(int size) { return new Stat[size]; }
    };*/
}
