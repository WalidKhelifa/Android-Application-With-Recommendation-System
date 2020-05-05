package com.example.testfinal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.testfinal.R;
import com.example.testfinal.activities.SelectionRepasActivity;
import com.example.testfinal.adapters.FragmentSelectionRepasAdapter;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectionRepasFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private FragmentSelectionRepasAdapter adapter;
    private Plat plat;
    private static String TAG="exemple_dialog";
    public static Commande commande;

    public SelectionRepasFragment() {

        commande= SelectionRepasActivity.commande;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_selection_repas, container, false);


        setHasOptionsMenu(true);
        //GETTING WIDGETS
        recyclerView=v.findViewById(R.id.recycler);

        //SETTING THE RECYCler
        recyclerView.setHasFixedSize(true);

        //SETTING THE NUMBER OF COLUMNS OF THE GRID

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL, false));
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(),5);
        recyclerView.setLayoutManager(layoutManager);



        switch (getArguments().getString("message"))
        {
            case "0":  adapter=new FragmentSelectionRepasAdapter(v.getContext(), SelectionRepasActivity.entrees);break;
            case "1":  adapter=new FragmentSelectionRepasAdapter(v.getContext(), SelectionRepasActivity.plats);break;
            case "2":  adapter=new FragmentSelectionRepasAdapter(v.getContext(), SelectionRepasActivity.desserts);break;
            case "3":  adapter=new FragmentSelectionRepasAdapter(v.getContext(), SelectionRepasActivity.boissons);break;

        }

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FragmentSelectionRepasAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(int position, ArrayList<Plat> p, ImageButton add) {


                plat=p.get(position);

                AddToChart.display(getFragmentManager(),p.get(position),commande);
            }

            @Override
            public void onDetailsClick(int position, ArrayList<Plat> p) {

                MenuDetailsFragment.display(getFragmentManager(),p.get(position));


            }
        });



        return v;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu,inflater);

        inflater.inflate(R.menu.menu_activity_toolbar,menu);
        MenuItem searchItem=menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView)searchItem.getActionView();
        searchView.setQueryHint("Rechercher...");
        searchView.setMaxWidth(Integer.SIZE);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setGravity(Gravity.LEFT);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


    }


}
