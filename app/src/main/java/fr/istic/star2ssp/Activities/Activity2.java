package fr.istic.star2ssp.Activities;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.FragmentActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fr.istic.star2ssp.R;
import fr.istic.star2ssp.StarContract;


public class Activity2 extends FragmentActivity {

    ListView listView;
    SearchView searchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        listView = (ListView) findViewById(R.id.listview);
        searchview = (SearchView) findViewById(R.id.simpleSearchView);
        ArrayList <String> arrets = new ArrayList<String>();
        Intent intent = getIntent();
        String line = "";
        String destination = "";

        if (intent != null) {
            if (intent.hasExtra("line")) {
                line = intent.getStringExtra("line");
            }
            if (intent.hasExtra("destination")) {
                destination = intent.getStringExtra("destination");
            }
        }

        Cursor cursor = this.getContentResolver().query(StarContract.Stops.CONTENT_URI, null, null, null, null);

        while(cursor.moveToNext()){
                        arrets.add(cursor.getString(4));
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrets);


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
        searchview.setQueryHint("Votre arrêt :");
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