package durnek.bakalarka.geography.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.Stat;

/**
 * Created by Lukas on 15. 4. 2015.
 */
public class StatDetailFragment extends Fragment {
    private int idStatu;
    private Stat stat;

    public static StatDetailFragment newInstance(int idStatu){
        StatDetailFragment f = new StatDetailFragment();
        Bundle args = new Bundle();
        args.putInt("idStatu", idStatu);
        f.setArguments(args);
        return f;
    }

    public StatDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            idStatu = getArguments().getInt("idStatu", 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stat_detail, container, false);

        //nacitanie z databazy
        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHelper.getWritableDatabase();

        stat = dbHelper.dajStat(idStatu);


        ((TextView) v.findViewById(R.id.tvNazov)).setText("   " + stat.getNazov());
        ((TextView) v.findViewById(R.id.tvHlMesto)).setText("Hlavné mesto:   " + stat.getHlMesto());
        ((TextView) v.findViewById(R.id.tvRozloha)).setText("Rozloha:    " + stat.getRozloha() + "  km2");
        ((TextView) v.findViewById(R.id.tvPopulacia)).setText("Populácia:   " + stat.getPopulacia() + " obyv.");
        ((TextView) v.findViewById(R.id.tvZnameMesta)).setText("Najznámejšie mestá:   " + (stat.getMesta() == null ? "bez známych miest" : stat.getMesta()));
        ((TextView) v.findViewById(R.id.tvJazyk)).setText("Úradný jazyk:   " + (stat.getJazyky() == null ? "angličtina" : stat.getJazyky()));
        ((TextView) v.findViewById(R.id.tvMena)).setText("Mena:   " + stat.getMena());

      /*  try {
            // get input stream
            InputStream ims = getActivity().getAssets().open(recept.detailPic);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ((ImageView) v.findViewById(R.id.obrazok)).setImageDrawable(d);
        } catch (IOException ex) {
            Log.e("Detail fragment", "Error nacitanie obrazku");
        }*/




        return v;
    }

}
