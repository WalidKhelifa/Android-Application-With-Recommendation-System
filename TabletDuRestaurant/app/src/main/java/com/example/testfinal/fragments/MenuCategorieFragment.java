package com.example.testfinal.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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
import com.example.testfinal.activities.MenuActivity;
import com.example.testfinal.adapters.FragmentMenuAdapter;
import com.example.testfinal.background.BackgroundMenuData;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCategorieFragment extends Fragment implements BackgroundMenuData.AsyncResponse{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private FragmentMenuAdapter adapter;
    private ImageButton fav;
    private Plat plat;




    public MenuCategorieFragment() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        final View v=inflater.inflate(R.layout.fragment_menu_categorie, container,false);

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
            case "0":  adapter=new FragmentMenuAdapter(v.getContext(), MenuActivity.entrees);break;
            case "1":  adapter=new FragmentMenuAdapter(v.getContext(), MenuActivity.plats);break;
            case "2":  adapter=new FragmentMenuAdapter(v.getContext(), MenuActivity.desserts);break;
            case "3":  adapter=new FragmentMenuAdapter(v.getContext(), MenuActivity.boissons);break;

        }

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FragmentMenuAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(int position, ArrayList<Plat> p, ImageButton favorite) {


                plat = p.get(position);

                BackgroundMenuData background = new BackgroundMenuData(MenuCategorieFragment.this, getContext());
                SharedPreferences preferences;
                preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                background.execute("favoris", preferences.getString("id_client", "no value"), plat.getId_plat());
                fav = favorite;

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


    @Override
    public void processFinish(Object result,String val) {


        String value = (String)result;
        if (value.equals("deleted"))
        {
            fav.setImageResource(R.drawable.ic_favorite_border);
            plat.setFavoris("false");


        }
        if (value.equals("inserted"))
        {
            fav.setImageResource(R.drawable.ic_favorite);
            plat.setFavoris("true");
        }

    }

}



