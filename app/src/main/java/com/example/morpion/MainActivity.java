package com.example.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void creerGrilleAction(View view) {
        EditText gridSizeEdit = findViewById(R.id.gridsize_et);
        EditText player1Edit = findViewById(R.id.player1_name);
        EditText player2Edit = findViewById(R.id.player2_name);

        String player1name;
        String player2name;

        int gridSize = 3;
        if(gridSizeEdit.getText().length() != 0) {
            gridSize = Integer.parseInt(gridSizeEdit.getText().toString());
        }

        player1name = player1Edit.getText().length() != 0
            ? player1name = player1Edit.getText().toString()
            : "Unknown";

        player2name = player2Edit.getText().length() != 0
                ? player2name = player2Edit.getText().toString()
                : "Unknown";


        Intent intent = new Intent(this, MorpionGameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(MorpionGameActivity.GRIDSIZE_KEY, gridSize);
        startActivity(intent);
    }
}