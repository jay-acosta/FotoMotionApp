package com.example.fotomotionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class PaintActivity extends AppCompatActivity {

    // instance variables
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint); // sets the current layout to activity_paint

        // save paintView as an instance variable
        paintView = findViewById(R.id.paint_paintView);

        buildSaveButton();
    }

    // sets the properties of the save button on the paint screen
    private void buildSaveButton() {

        // get the button with the id 'paint_saveButton'
        Button saveButton = findViewById(R.id.paint_saveButton);

        // set the listener to call save() whenever the button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                save();
            }
        });
    }


    // added to save whenever the user presses backspace
    // prevents slip ups
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        save();
    }

    // pre: none
    // post: returns a saved instance of the current canvas
    private void save() {

        Toast.makeText(this, "Bruh moment, save not implemented", Toast.LENGTH_SHORT).show();
        // TODO implement save, add parameters as necessary
    }
}
