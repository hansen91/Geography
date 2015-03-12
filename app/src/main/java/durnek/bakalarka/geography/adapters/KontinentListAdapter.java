package durnek.bakalarka.geography.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.SimpleKontinent;
import durnek.bakalarka.geography.classes.Vyucba;


/**
 * Created by Lukas on 6. 3. 2015.
 */
public class KontinentListAdapter extends BaseAdapter {
    private Vyucba vyucba;
    private List<Item> items;

    public static class Item {
        public Object obj;

        public Item(Object obj) {
            this.obj = obj;
        }

        public SimpleKontinent dajAkoKontinent() {
            return (SimpleKontinent) obj;
        }

    }

    public KontinentListAdapter(Vyucba v){
        this.vyucba = v;
        this.items = new ArrayList<KontinentListAdapter.Item>();
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public KontinentListAdapter.Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).dajAkoKontinent().id_kontinentu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            // if (getItemViewType(position) == 0)
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            //else
            //convertView = inflater.inflate(R.layout.)
            holder = new ViewHolder();

            holder.nazov = (TextView) convertView.findViewById(R.id.kontinent);
            convertView.setTag(holder);

            holder.nazov.setText(getItem(position).dajAkoKontinent().dajKontinenty());

        }
        return convertView;
    }

    static class ViewHolder{
        TextView nazov;
    }





    /**
     * 2 typy viewov, view poctu statov a hlavička nazvu
     */
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * Vráti typ viewu, 1 ak je hlavicka, a 0 ak je den
     */
       /* public int getItemViewType(int position) {
            return (getItem(position).isHeader()) ? 1 : 0;
        }*/

}