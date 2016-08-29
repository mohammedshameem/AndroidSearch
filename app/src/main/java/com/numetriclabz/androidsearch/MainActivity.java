package com.numetriclabz.androidsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public SearchView search;
    private List<String> list = new ArrayList<String>();
    Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (SearchView) findViewById( R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        createlist();  // in this method, Create a list of items.
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // call the adapter with argument list of items and context.
        mAdapter = new Adapter(list,this);
        mRecyclerView.setAdapter(mAdapter);
        search.setOnQueryTextListener(listener); // call the QuerytextListner.
    }

    // this method is used to create list of items.
    public void createlist(){

        list.add("Volkswagen Vento");
        list.add("Hyundai Xcent");
        list.add("Maruti Swift");
        list.add("Hyundai i20");
        list.add("Ford Fiesta Classic");
        list.add("Chevrolet Beat");
        list.add("Maruti Alto");
        list.add("Toyota Etios");
        list.add("Toyota Innova");
        list.add("Mahindra Scorpio");
        list.add("Maruti Wagon R");
        list.add("Ford Figo");

    }

    /* this is the Seerach QuerttextListner.
       this method filter the list data with a matching string,
       hence provides user an easy way to find the information he needs.
     */

    SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextChange(String query) {
            query = query.toLowerCase();

            final List<String> filteredList = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {

                final String text = list.get(i).toLowerCase();
                if (text.contains(query)) {

                    filteredList.add(list.get(i));
                }
            }

            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mAdapter = new Adapter(filteredList, MainActivity.this);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();  // data set changed
            return true;

        }
        public boolean onQueryTextSubmit(String query) {
            return false;
        }
    };

}
