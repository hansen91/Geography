package durnek.bakalarka.geography.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.Kontinent;

/**
 * Adaptér pomocou ktorého sa zobrazujú dáta o kontinentoch z databázy
 */
public class KontinentAdapter
    extends ArrayAdapter<Kontinent> {

    public ArrayList<Kontinent> kontinents;
    public LayoutInflater inflater;

    public KontinentAdapter(Context context, int resource, List<Kontinent> objects) {
        super(context, resource, objects);

        this.inflater = LayoutInflater.from(context);
        this.kontinents = (ArrayList) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null){
            itemView = inflater.inflate(R.layout.item_view_kontinent, parent, false);
        }

        Kontinent actKontintent = kontinents.get(position);

        //naplnenie view
        //Nazov
        TextView txtvNazov = (TextView) itemView.findViewById(R.id.item_tvNazov);
        txtvNazov.setText(actKontintent.getNazov());

        //pocet statov
        TextView txtvPocetStatov = (TextView) itemView.findViewById(R.id.item_tvPocStatov);
        txtvPocetStatov.setText("Počet štátov: " + actKontintent.getPocetStatov());

        return itemView;
    }
}
