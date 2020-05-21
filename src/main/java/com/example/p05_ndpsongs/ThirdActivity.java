package com.example.p05_ndpsongs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    TextView tvID;
    EditText editTextSongTitle, editTextSingers, editTextYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    RadioGroup rg;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //initialize the variables with UI here
        tvID = (TextView) findViewById(R.id.tvID);
        editTextSongTitle = (EditText) findViewById(R.id.editTextSongTitle);
        editTextSingers = (EditText) findViewById(R.id.editTextSingers);
        editTextYear = (EditText) findViewById(R.id.editTextYear);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        radio1 = (RadioButton) findViewById(R.id.Modifyradio1);
        radio2 = (RadioButton) findViewById(R.id.Modifyradio2);
        radio3 = (RadioButton) findViewById(R.id.Modifyradio3);
        radio4 = (RadioButton) findViewById(R.id.Modifyradio4);
        radio5 = (RadioButton) findViewById(R.id.Modifyradio5);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        editTextSongTitle.setText(data.getTitle());
        editTextSingers.setText(data.getSingers());
        editTextYear.setText(Integer.toString(data.getYear()));

        final int stars = data.getStars();
        if (stars == 1) {
            radio1.setChecked(true);
        } else if (stars == 2) {
            radio2.setChecked(true);
        } else if (stars == 3) {
            radio3.setChecked(true);
        } else if (stars == 4) {
            radio4.setChecked(true);
        } else {
            radio5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get RadioGroup object
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupStars2);

                // get id of selected radio button in radiogroup
                int selectedButtonId = rg.getCheckedRadioButtonId();

                // get radio button object from id we had gotten above
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                int selected = Integer.valueOf(rb.getText().toString());

                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(editTextSongTitle.getText().toString());
                data.setSingers(editTextSingers.getText().toString());
                data.setYear(Integer.valueOf(String.valueOf(editTextYear.getText())));
                data.setStars(selected);
                dbh.updateSong(data);
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                finish();
            }
        });

    }
}
