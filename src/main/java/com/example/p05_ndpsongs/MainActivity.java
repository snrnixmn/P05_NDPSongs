package com.example.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle, etSingers, etYear;
    Button btnInsert, btnShowList;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = (EditText) findViewById(R.id.etSongTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnShowList = (Button) findViewById(R.id.btnShowList);
        rg = (RadioGroup) findViewById(R.id.radioGroupStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbh = new DBHelper(MainActivity.this);

                String title = etSongTitle.getText().toString();
                String singers = etSongTitle.getText().toString();
                int year = Integer.valueOf(etSongTitle.getText().toString());
                int stars = getStars();
                dbh.insertNote(title, singers, year, stars);
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
        }
    });

        btnShowList.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View arg0){
        Intent i = new Intent(MainActivity.this, ShowSongActivity.class);
        startActivity(i);
    }
    });
}

    private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                stars = 1;
                break;
            case R.id.radio2:
                stars = 2;
                break;
            case R.id.radio3:
                stars = 3;
                break;
            case R.id.radio4:
                stars = 4;
                break;
            case R.id.radio5:
                stars = 5;
                break;
        }
        return stars;
    }
}
