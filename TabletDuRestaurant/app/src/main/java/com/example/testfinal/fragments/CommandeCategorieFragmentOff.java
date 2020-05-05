package com.example.testfinal.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import com.example.testfinal.activities.CommandeActivityOff;
import com.example.testfinal.adapters.FragmentCommandeMenuAdapter;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommandeCategorieFragmentOff extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private FragmentCommandeMenuAdapter adapter;
    private ImageButton fav;
    private static String TAG="exemple_dialog";
    private static Commande commande;
    private FloatingActionButton valider;


    public CommandeCategorieFragmentOff() {
        // Required empty public constructor

        commande= CommandeActivityOff.commande;
        valider=CommandeActivityOff.valider;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_commande_menu_categorie, container, false);

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
            case "0":  adapter=new FragmentCommandeMenuAdapter(v.getContext(), CommandeActivityOff.entrees);break;
            case "1":  adapter=new FragmentCommandeMenuAdapter(v.getContext(), CommandeActivityOff.plats);break;
            case "2":  adapter=new FragmentCommandeMenuAdapter(v.getContext(), CommandeActivityOff.desserts);break;
            case "3":  adapter=new FragmentCommandeMenuAdapter(v.getContext(), CommandeActivityOff.boissons);break;

        }

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FragmentCommandeMenuAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(int position, ArrayList<Plat> p, ImageButton add) {

                AddToChart.display(getFragmentManager(),p.get(position),commande);
            }

            @Override
            public void onDetailsClick(int position, ArrayList<Plat> p) {

                MenuDetailsFragment.display(getFragmentManager(),p.get(position));


            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 ) {

                    valider.hide();

                } else if (dy < 0) {

                    valider.show();
                }
            }
        });
        return v;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_activity_toolbar,menu);
        MenuItem searchItem=menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView)searchItem.getActionView();
        searchView.setQueryHint("Rechercher...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);


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
        // super.onCreateOptionsMenu(menu,inflater);

    }




}
