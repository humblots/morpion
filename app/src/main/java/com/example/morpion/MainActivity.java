package com.example.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playBtn = findViewById(R.id.play_btn);
        playBtn.setOnClickListener(v -> playAction());

        Button addPlayerBtn = findViewById(R.id.addPlayer_btn);
        addPlayerBtn.setOnClickListener(v -> addPlayerAction());

        Button scoreListBtn = findViewById(R.id.scoreList_btn);
        scoreListBtn.setOnClickListener(v -> playersListAction());
    }

    private void playAction() {
        Intent intent = new Intent(MainActivity.this, MorpionCreateActivity.class);
        startActivity(intent);
    }

    private void addPlayerAction() {
        Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
        startActivity(intent);
    }

    private void playersListAction() {
        Intent intent = new Intent(MainActivity.this, PlayersList.class);
        startActivity(intent);
    }

}