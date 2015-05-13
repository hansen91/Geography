package durnek.bakalarka.geography.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.IOException;
import java.util.List;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.adapters.KontinentAdapter;
import durnek.bakalarka.geography.classes.Kontinent;


public class KontinentListFragment
    extends ListFragment {

    DataBaseHelper dbHelper;
    private List<Kontinent> listWithStatesNumber;
    private OnKontinentSelectedListener mListener;


    public KontinentListFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DataBaseHelper(getActivity());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listWithStatesNumber = dbHelper.getContinentsWithStatesNumber();

            ArrayAdapter<Kontinent> adapter = new KontinentAdapter(getActivity(), R.layout.item_view_kontinent, listWithStatesNumber);

            setListAdapter(adapter);
     }

        @Override
        public void onAttach (Activity activity) {
            super.onAttach(activity);
            try {
                mListener = (OnKontinentSelectedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + "nie je implementovany listener OnKontinentSelectedListener");
            }
        }

        @Override
        public void onDetach () {
            super.onDetach();
            mListener = null;
        }

       @Override
        public void onListItemClick (ListView listView, View view, int position, long id){
            super.onListItemClick(listView, view, position, id);
            if (null != mListener) {
                mListener.onKontinentSelected(position+1);
            }
        }

    public interface OnKontinentSelectedListener {

        public void onKontinentSelected(int id);

    }
}