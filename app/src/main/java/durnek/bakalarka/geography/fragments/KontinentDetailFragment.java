package durnek.bakalarka.geography.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.activities.StatActivity;
import durnek.bakalarka.geography.classes.Kontinent;


public class KontinentDetailFragment extends Fragment {

    private int idKontinentu;
    private Kontinent kontinent;

    public static KontinentDetailFragment newInstance(int id){
        KontinentDetailFragment f = new KontinentDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;
    }

    public KontinentDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            idKontinentu = getArguments().getInt("id", 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kontinent_detail, container, false);

        //nacitanie z databazy
        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHelper.getWritableDatabase();

        kontinent = dbHelper.dajKontinent(idKontinentu);


        //tlacidlo Prehlad statov
        Button btnPrehlad = (Button) v.findViewById(R.id.btnPrehlad);
        btnPrehlad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Tu sa zobrazia štáty", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), StatActivity.class);
                intent.putExtra("idKontinentu", idKontinentu);
                startActivity(intent);

            }
        });


        ((TextView) v.findViewById(R.id.nazov)).setText("   " + kontinent.getNazov());
        ((TextView) v.findViewById(R.id.pocStatov)).setText("Počet štátov:   " + kontinent.getPocetStatov());
        ((TextView) v.findViewById(R.id.pocUzemi)).setText("Počet závislých území:   " + (kontinent.getPocetUzemi() == 0 ? "nemá územia" : kontinent.getPocetUzemi()));
        ((TextView) v.findViewById(R.id.rozloha)).setText("Rozloha:    " + kontinent.getRozloha() + "  km2");
        ((TextView) v.findViewById(R.id.populacia)).setText("Populácia:   " + kontinent.getPopulacia() + " obyv.");

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
