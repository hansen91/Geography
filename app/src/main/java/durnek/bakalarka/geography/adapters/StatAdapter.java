package durnek.bakalarka.geography.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.Kontinent;
import durnek.bakalarka.geography.classes.Stat;

/**
 * Adaptér pomocou ktorého sa zobrazujú dáta o štátoch z databázy
 */
public class StatAdapter  extends ArrayAdapter<Stat> {
    public ArrayList<Stat> states;
    public LayoutInflater inflater;

    public StatAdapter(Context context, int resource, List<Stat> objects) {
        super(context, resource, objects);

        this.inflater = LayoutInflater.from(context);
        this.states = (ArrayList) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null){
            itemView = inflater.inflate(R.layout.item_view_stat, parent, false);
        }

        Stat actStat = states.get(position);

        //naplnenie view
        //Nazov
        TextView txtvNazov = (TextView) itemView.findViewById(R.id.item_tvNazov);
        txtvNazov.setText(actStat.getNazov());

        return itemView;
     }
}
