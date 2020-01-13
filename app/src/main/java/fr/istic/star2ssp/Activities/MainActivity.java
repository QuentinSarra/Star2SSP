package fr.istic.star2ssp.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import fr.istic.star2ssp.CustomAdapter;
import fr.istic.star2ssp.Fragments.MainFragment;
import fr.istic.star2ssp.R;
import fr.istic.star2ssp.StarContract;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    String line,direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor = this.getContentResolver().query(StarContract.BusRoutes.CONTENT_URI, null, null, null, null);

        DatePicker datePicker = findViewById(R.id.datePicker);
        TimePicker timePicker = findViewById(R.id.timePicker);
        String[] backcolor = new String[cursor.getCount()];
        final String[] lineData = new String[cursor.getCount()];
        String[] txtcolor = new String[cursor.getCount()];
        int position;

        cursor.moveToNext();
        while (!cursor.isAfterLast()){
            position = cursor.getInt(0)-1;
            lineData[position] = cursor.getString(1);
            backcolor[position] = cursor.getString(5);
            txtcolor[position] = cursor.getString(6);
            cursor.moveToNext();
        }

        final Spinner spin = findViewById(R.id.line);
        CustomAdapter adapter = new CustomAdapter(this,lineData,backcolor,txtcolor);
        spin.setAdapter(adapter);

        final Spinner spinDir = findViewById(R.id.direction);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if(lineData[0]!=null) {
                    spinDir.setAdapter(getDirection(spin.getSelectedItem().toString()));
                    direction = spinDir.getSelectedItem().toString();
                }
                line = spin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onButtonClicked(View view) {
        Intent intent =new Intent(this, Activity2.class);
        startActivity(intent);
        intent.putExtra("line", line);
        intent.putExtra("direction", direction);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public SpinnerAdapter getDirection (String line){
        ArrayAdapter<CharSequence> adapterDir = new ArrayAdapter <CharSequence> (getApplicationContext(), android.R.layout.simple_spinner_item);
        adapterDir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String lineDataDir = new String();
        String [] projection = {StarContract.BusRoutes.BusRouteColumns.DESCRIPTION};
        String where = StarContract.BusRoutes.BusRouteColumns.SHORT_NAME + "=?";
        String [] whereParam = {line};
        Cursor cursorDir = this.getContentResolver().query(StarContract.BusRoutes.CONTENT_URI, null, where, whereParam, null);
        cursorDir.moveToNext();
        if( cursorDir != null && cursorDir.moveToFirst() ) {
            lineDataDir = cursorDir.getString(2);
        }
        String[] dirs = lineDataDir.split("<>");
        for(String e : dirs) {
            adapterDir.add(e);
        }
        return adapterDir;
    }
}
