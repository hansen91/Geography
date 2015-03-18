package durnek.bakalarka.geography.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.adapters.KontinentListAdapter;
import durnek.bakalarka.geography.classes.Kontinent;
import durnek.bakalarka.geography.classes.Vyucba;


/**
 * Created by Lukas on 6. 3. 2015.
 */
public class KontinentListFragment
    extends ListFragment {
    //private static Parcelable mListViewScrollPos = null;
    //private Callbacks mCallbacks = sDummyCallbacks; //prepinanie fragmentov

    private List<Kontinent> list;
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

        DataBaseHelper db = new DataBaseHelper(getActivity().getApplicationContext(),"db.sqlite");
        Cursor cursor = db.getKontintent();
        String[] from = {"nazov"};
        int[] to = {R.id.kontinent};
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(getActivity().getApplicationContext(),R.layout.list_item,cursor,from,to);
        setListAdapter(listAdapter);

    }
        /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, null);
        return view;lkn
    }
    */
    /*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnKontinentSelectedListener) activity;
        } catch (ClassCastException e) {
            Toast.makeText(getActivity().getApplicationContext(), "ERROR " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
        /*
        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException(
                    "Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
        */

       // setListAdapter(aAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        /*
        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;*/
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        if (null != mListener){
            mListener.onKontinentSelected(list.get(position));
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
    public interface OnKontinentSelectedListener{

        public void onKontinentSelected(Kontinent kontinent);
    }



}
