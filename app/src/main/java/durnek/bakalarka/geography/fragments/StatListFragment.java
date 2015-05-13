package durnek.bakalarka.geography.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.IOException;
import java.util.List;
import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.activities.StatActivity;
import durnek.bakalarka.geography.activities.StatDetailActivity;
import durnek.bakalarka.geography.adapters.StatAdapter;
import durnek.bakalarka.geography.classes.Stat;


public class StatListFragment extends ListFragment {

    DataBaseHelper dbHelper;
    private List<Stat> states;
    private int idKontinentu;


    public StatListFragment() {
    }

    public static StatListFragment newInstance(int idKontinentu){
        StatListFragment f = new StatListFragment();
        Bundle args = new Bundle();
        args.putInt("idKontinentu", idKontinentu);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            idKontinentu = getArguments().getInt("idKontinentu", 1);
        }

        dbHelper = new DataBaseHelper(getActivity());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        states = dbHelper.getAllStates(idKontinentu);

        ArrayAdapter<Stat> adapter = new StatAdapter(getActivity(), R.layout.item_view_stat,states);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick (ListView listView, View view, int position, long id){
        super.onListItemClick(listView, view, position, id);
            Intent intent = new Intent(getActivity().getApplicationContext(), StatDetailActivity.class);
            intent.putExtra("idStatu", states.get(position).get_id());
            startActivity(intent);
    }

}
