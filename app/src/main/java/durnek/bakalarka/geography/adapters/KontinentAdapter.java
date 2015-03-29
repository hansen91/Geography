package durnek.bakalarka.geography.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.Kontinent;


/**
 * Created by Lukas on 6. 3. 2015.
 */
public class KontinentAdapter extends ArrayAdapter<Kontinent> {

    private class ViewHolder{
        ImageView obr;
        TextView nazov;
        TextView pocStatov;
        TextView pocUzemi;
        TextView rozloha;
        TextView populacia;
    }

    List<Kontinent> listCont;
    Context appContext;

    public KontinentAdapter(Context context, int resource, List<Kontinent> objects) {
        super(context, resource, objects);
        listCont = objects;
        this.appContext = context;
    }



            @Override
            public int getCount() {
                return listCont.size();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                ViewHolder holder;
                if (v == null) {
                    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                    v = inflater.inflate(R.layout.item_view, null);
                    holder = new ViewHolder();

                    holder.nazov = (TextView) v.findViewById(R.id.kontinent);
                    holder.pocStatov = (TextView) v.findViewById(R.id.pocStatov);
                    holder.pocUzemi = (TextView) v.findViewById(R.id.pocUzemi);
                    holder.rozloha = (TextView) v.findViewById(R.id.rozloha);
                    holder.populacia = (TextView) v.findViewById(R.id.populacia);
                    //holder.img = (ImageView) v.findViewById(R.id.obrazok);
                    v.setTag(holder);
                } else {
                    holder = (ViewHolder) v.getTag();
                }
                    holder.nazov.setText(listCont.get(position).getNazov());
                    holder.pocStatov.setText(listCont.get(position).getPocetStatov());
                    holder.pocUzemi.setText(listCont.get(position).getPocetUzemi());
                    holder.rozloha.setText((int) listCont.get(position).getRozloha());
                    holder.populacia.setText((int) listCont.get(position).getPopulacia());

           /*     try {
                    // get input stream
                    InputStream ims = appContext.getAssets().open(listCont.get(position).previewPic);
                    // load image as Drawable
                    Drawable d = Drawable.createFromStream(ims, null);
                    // set image to ImageView
                    holder.img.setImageDrawable(d);
                } catch (IOException ex) {
                    Log.e("Parse", "Error nacitania obrazku");
                }*/



                return v;
            }






}