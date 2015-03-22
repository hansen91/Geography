package durnek.bakalarka.geography.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.adapters.KontinentListAdapter;
import durnek.bakalarka.geography.application.App;
import durnek.bakalarka.geography.classes.Kontinent;
import durnek.bakalarka.geography.classes.Vyucba;


/**
 * Created by Lukas on 6. 3. 2015.
 */
public class KontinentListFragment
    extends ListFragment {
    //private static Parcelable mListViewScrollPos = null;
    //private Callbacks mCallbacks = sDummyCallbacks; //prepinanie fragmentov
    DataBaseHelper dbHelper;
    ListView lvContinents;
    ListAdapter adapter;
    List<Kontinent> kontinentList;

    private OnKontinentSelectedListener mListener;

    /*
    public interface Callbacks {
        public void onItemSelected(long id);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(long id) {
        }
    };
    */

    /**
     * Povinny bezparametricky konstruktor
     */
    public KontinentListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context c = getActivity();
        setListAdapter(new ArrayAdapter<String>(c, R.layout.fragment_kontinent));

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kontinent, container,false);
        dbHelper = new DataBaseHelper(this.getActivity());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lvContinents = (ListView)rootView.findViewById(R.id.kontinent_view);
        List<String> listContinent = dbHelper.getAllContinents();

        if (listContinent != null) {
            adapter = new ArrayAdapter<String>(this.getActivity(),
                    R.layout.item_view, R.id.kontinent, listContinent);
            lvContinents.setAdapter(adapter);
        }
        return rootView;
    }
    /*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    }*/

        @Override
        public void onAttach (Activity activity) {
            super.onAttach(activity);
            try {
                mListener = (OnKontinentSelectedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + "nie je implementovany listener");
            }
        }

        // Activities containing this fragment must implement its callbacks.
     /*   if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException(
                    "Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
        */

            // setListAdapter(aAdapter);

        @Override
        public void onDetach () {
            super.onDetach();
            mListener = null;
        /*
        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;*/
        }

       @Override
        public void onListItemClick (ListView listView, View view,int position, long id){
            super.onListItemClick(listView, view, position, id);
            if (null != mListener) {
                mListener.onKontinentSelected(kontinentList.get(position));
            }
        }
    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the ListView position
        mListViewScrollPos = getListView().onSaveInstanceState();
    }

    public void setActivateOnItemClick(boolean b) {
        if (b)
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        else
            getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);

    }
    */
        public interface OnKontinentSelectedListener {

            public void onKontinentSelected(Kontinent kontinent);
        }


    }