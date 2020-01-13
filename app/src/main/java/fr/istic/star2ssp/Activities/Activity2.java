package fr.istic.star2ssp.Activities;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.FragmentActivity;

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
        if (intent != null) {
            String line = "";
            if (intent.hasExtra("line")) {
                line = intent.getStringExtra("line");
            }
        }

        Cursor cursor = this.getContentResolver().query(StarContract.Stops.CONTENT_URI, null, null, null, null);

        while(cursor.moveToNext()){
            arrets.add(cursor.getString(4));
        }

        Cursor cursor1 = this.getContentResolver().query(StarContract.StopTimes.CONTENT_URI, null, null, null, null);
        Cursor cursor2 = this.getContentResolver().query(StarContract.Trips.CONTENT_URI, null, null, null, null);
        Cursor cursor3 = this.getContentResolver().query(StarContract.BusRoutes.CONTENT_URI, null, null, null, null);
        String query = "SELECT "+cursor.getString(4)+"From busroute where"+cursor.getString(0)+"="+cursor1.getString(4)+" AND "+cursor1.getString(1)+" = ";
        query += cursor2.getString(0)+" AND " +cursor2.getString(2)+" = " +cursor3.getString(0)+"";
        Cursor curseurDir = db.
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