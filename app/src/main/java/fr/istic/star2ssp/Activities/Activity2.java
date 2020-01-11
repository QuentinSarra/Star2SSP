package fr.istic.star2ssp.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import fr.istic.star2ssp.Fragments.Fragment2;
import fr.istic.star2ssp.Fragments.Fragment3;
import fr.istic.star2ssp.R;

public class Activity2 extends FragmentActivity {

    ListView listView;
    SearchView searchview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_2);

        listView = (ListView) findViewById(R.id.listview);
        searchview = (SearchView) findViewById(R.id.simpleSearchView);
        String[] listeCourse = new String[]{
                "Oranges",
                "Tomates",
                "Raisin",
                "Pain",
                "Banane",
                "Kiwi",
                "Pates",
                "Raviolis",
                "Fraises",
                "Glace",
                "Pizza",
                "Yaourts",
                "Riz",
                "Haricots"
        };

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeCourse);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent intent = new Intent(Activity2.this, Activity3.class);
               startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        searchview.setActivated(true);
        searchview.setQueryHint("Type your keyword here");
        searchview.onActionViewExpanded();
        searchview.setIconified(false);
        searchview.clearFocus();

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}