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
    private static final Uri Stops = StarContract.BusRoutes.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_2);
        ArrayList <String> arrets = new ArrayList<String>();


        Cursor cursor = this.getContentResolver().query(StarContract.BusRoutes.CONTENT_URI, null, null, null, null);

        while(cursor.moveToNext()){
            arrets.add(cursor.getString(1));
            arrets.add("ee");
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