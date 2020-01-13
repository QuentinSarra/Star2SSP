package fr.istic.star2ssp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fr.istic.star2ssp.R;
import fr.istic.star2ssp.StarContract;

public class Activity4 extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        listView = (ListView) findViewById(R.id.listview3);
        ArrayList<String> arrets = new ArrayList<String>();

        Cursor cursor = this.getContentResolver().query(StarContract.Stops.CONTENT_URI, null, null, null, null);

        while(cursor.moveToNext()){
            arrets.add(cursor.getString(4));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrets);

        listView.setAdapter(arrayAdapter);



    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
