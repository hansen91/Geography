package durnek.bakalarka.geography.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.Kontinent;


public class KontinentDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Kontinent kontinent;

    public static KontinentDetailFragment newInstance(Kontinent kontinent){
        KontinentDetailFragment f = new KontinentDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("kontinent", (android.os.Parcelable)kontinent);
        f.setArguments(args);
        return f;
    }


    public KontinentDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(getArguments() != null){
            kontinent = (Kontinent) getArguments().getParcelable("kontinent");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kontinent_detail, container, false);
        ((TextView) v.findViewById(R.id.nazov)).setText(kontinent.getNazov());
        ((TextView) v.findViewById(R.id.pocStatov)).setText(kontinent.getPocetStatov());
        ((TextView) v.findViewById(R.id.pocUzemi)).setText(kontinent.getPocetUzemi());
        ((TextView) v.findViewById(R.id.rozloha)).setText((int) kontinent.getRozloha());
        ((TextView) v.findViewById(R.id.populacia)).setText((int) kontinent.getPopulacia());

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
