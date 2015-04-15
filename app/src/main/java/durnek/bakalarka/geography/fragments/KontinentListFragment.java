package durnek.bakalarka.geography.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.io.IOException;
import java.util.List;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.adapters.TestAdapter;
import durnek.bakalarka.geography.classes.Kontinent;
import durnek.bakalarka.geography.classes.Svet;


/**
 * Created by Lukas on 6. 3. 2015.
 */
public class KontinentListFragment
    extends ListFragment {
    //private static Parcelable mListViewScrollPos = null;
   // private Callbacks mCallbacks = sDummyCallbacks; //prepinanie fragmentov


    DataBaseHelper dbHelper;
   // ListView lvContinents;
    //ListAdapter adapter;
    private List<String> kontinentList;
    private List<Kontinent> listWithStatesNumber;
    private OnKontinentSelectedListener mListener;


    /*public interface Callbacks {
        public void onItemSelected(long id);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(long id) {
        }
    };*/


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

        //List<String> listContinent = dbHelper.getAllContinents();
        kontinentList = dbHelper.getAllContinents();
        listWithStatesNumber = dbHelper.getContinentsWithStatesNumber();

        //if(kontinentList != null) {
            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                   R.layout.item_view, R.id.kontinent, kontinentList);*/

            ArrayAdapter<Kontinent> adapter = new TestAdapter(getActivity(), R.layout.item_view, listWithStatesNumber);

            setListAdapter(adapter);
        //}
    }

  /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kontinent, container,false);

        dbHelper = new DataBaseHelper(this.getActivity());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lvContinents = (ListView)rootView.findViewById(R.id.list);
        //lvContinents.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.fragment_kontinent));

        List<String> listContinent = dbHelper.getAllContinents();

        if (listContinent != null) {
            adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    R.layout.item_view, R.id.kontinent, listContinent);
            lvContinents.setAdapter(adapter);
        }
        return rootView;
    }*/
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
                throw new ClassCastException(activity.toString() + "nie je implementovany listener OnKontinentSelectedListener");
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
        public void onListItemClick (ListView listView, View view, int position, long id){
            super.onListItemClick(listView, view, position, id);
            if (null != mListener) {
                mListener.onKontinentSelected(position+1);
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

        public void onKontinentSelected(int id);

    }

}