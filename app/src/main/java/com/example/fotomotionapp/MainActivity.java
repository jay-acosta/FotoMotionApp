package com.example.fotomotionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.File;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddPanelDialogue.ExampleDialogListener {

    private int KEY_CODE = 1;
//    TableLayout myLayout;
//    TableRow currentRow;
//    int counter;

    private List<AnimationProject> projectNames;
    private RecyclerViewAdapter adapter;
    public static File directoryName;

    //Big Boy make me happy
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askPermission();

        makeDirectory();


        if (projectNames == null) {
            initializeProjectNames(); // sets the current project names to the items presented
            initializeAdapter();
        }

        Button addButton = findViewById(R.id.addPanelsButton);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openDialog();
                // addPanel();
            }
        });

        // Main Activity - here

//        final Button switchScreenButton = findViewById(R.id.);
//        switchScreenButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent switchScreenIntent = new Intent(getApplicationContext(), PaintActivity.class);
//                switchScreenIntent.putExtra("com.example.fotomotionapp.buttonText", switchScreenButton.getText().toString());
//                startActivity(switchScreenIntent);
//            }
//        });
//
//        myLayout = findViewById(R.id.layout);
//
//        Button addPanelsButton = findViewById(R.id.addPanelsButton);
//        addPanelsButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Button additionalButton = new Button(MainActivity.this);
//
//                if(counter % 3 == 0) {
//                    currentRow = new TableRow(MainActivity.this);
//                    currentRow.setLayoutParams(new TableRow.LayoutParams(
//                            TableLayout.LayoutParams.MATCH_PARENT,
//                            TableLayout.LayoutParams.MATCH_PARENT
//
//                    ));
//                    myLayout.addView(currentRow);
//                }
//
//                additionalButton.setText("Project: " + ++counter);
//
//                currentRow.addView(additionalButton);
//
//            }
//        });
//
//        // get the button by finding the elements with the id of "straightButton"
//        Button straightButton = findViewById(R.id.fileButton);
//
//        // set the action to be performed on click to an iClicker
//        straightButton.setOnClickListener(new iClicker());


    }

    @Override
    public void applyTexts(String projectName, String username) {

        File newProje = new File(directoryName.getPath() + "/" + projectName + ".jag");

        if(!newProje.exists()) {
            newProje.mkdir();
            int newSize = projectNames.size() + 1;
            projectNames.add(new AnimationProject(projectName, username));
            adapter.notifyItemInserted(newSize);

        } else {
            Toast.makeText(this, "you still non-hetero lmao, choose new projé name", Toast.LENGTH_SHORT).show();
        }
    }


    //Asks the user for permission to access all their data and social security
    private void askPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this, "I love you for accepting permissions babe", Toast.LENGTH_SHORT).show();
        } else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA}, KEY_CODE);
        }
    }


    //Puts the directory that all the users projects will be saved to in a special place that we don't even know where it is
    private void makeDirectory(){
        String pathName = getExternalFilesDir(null).getPath() + getString(R.string.DirectoryName);
        File myDir = new File(pathName);
        if(!myDir.exists()){
             myDir.mkdir();
        }
        directoryName = myDir;
    }

    //Initializes the projects, who would of thought
    private void initializeProjectNames() {
        projectNames = new ArrayList<>();

        //Gets all existing projects that already exist in the directory
        for(File innerFile: directoryName.listFiles()) {
            projectNames.add(new AnimationProject(innerFile.getName().substring(0,innerFile.getName().indexOf(".")), "User Dumbass"));
        }
    }

    //Initializes the UI scrolling thing, "view panel", so user can scroll or some shitaki mushrooms
    private void initializeAdapter() {
        RecyclerView recycler = findViewById(R.id.projectPanel);
        adapter = new RecyclerViewAdapter(this, projectNames);
        // adapter.setFragmentManager(getSupportFragmentManager());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void openDialog() {
        AddPanelDialogue addPanelDialogue = new AddPanelDialogue();
        addPanelDialogue.show(getSupportFragmentManager(), "example");
    }


    //Helper method to help find directories on files
    //Also give me the kids Karen I need tHTMEMMEMMMMMM, i'm sorry i just get mad sometimes, plz call back
    private void viewInnerFiles(File directory) {
        Log.e("jay says", directory.getPath());
        if(directory != null && directory.isDirectory()) {
            for(File innerFile: directory.listFiles()) {
                Log.e("jay says", innerFile.getPath());
               // viewInnerFiles(innerFile);
            }
        } else {
            // Log.e("jay says", "not a directory");
        }
    }
}