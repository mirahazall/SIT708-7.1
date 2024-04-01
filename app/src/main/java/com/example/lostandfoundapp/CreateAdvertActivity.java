package com.example.lostandfoundapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {
    Button buttonSave;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        buttonSave = findViewById(R.id.buttonSave);
        dbHelper = new DatabaseHelper(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDatabase();
            }
        });
    }

    private void saveDataToDatabase() {
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextDescription = findViewById(R.id.editTextDescription);
        EditText editTextDate = findViewById(R.id.editTextDate);
        EditText editTextLocation = findViewById(R.id.editTextLocation);
        RadioGroup radioGroupPostType = findViewById(R.id.radioGroupPostType);

        // Get the ID of the selected radio button
        int selectedRadioButtonId = radioGroupPostType.getCheckedRadioButtonId();

        // Initialize a string to hold the selected post type
        String type = "";

        // Check if any radio button is selected
        if (selectedRadioButtonId != -1) {
            // Get the selected radio button
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            // Get the text of the selected radio button
            type = selectedRadioButton.getText().toString();
        }


        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String description = editTextDescription.getText().toString();
        String date = editTextDate.getText().toString();
        String location =  editTextLocation.getText().toString();

        long newRowId = dbHelper.insertData(type, name, phone, description, date, location);
        if (newRowId != -1) {
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        }
    }
}
