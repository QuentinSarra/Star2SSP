package fr.istic.star2ssp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.istic.star2ssp.R;

public class Activity4 extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        listView = (ListView) findViewById(R.id.listview3);

        String[] listeCourse = new String[]{
                "1evizu",
                "2zohzov",
                "3zovhz",
                "4",
                "1",
                "2",
                "3",
                "4",
                "1",
                "2",
                "3",
                "4",
                "1",
                "2",
                "3",
                "4"
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeCourse);

        listView.setAdapter(arrayAdapter);



    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
