package com.example.morpion;

import static com.example.morpion.Utils.StrToBitMap;
import static com.example.morpion.Utils.setPlayerImage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morpion.db.AppDatabase;
import com.example.morpion.db.DatabaseClient;
import com.example.morpion.db.Player;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_KEY = "RESULT_KEY";
    public static final String WINNER_ID_KEY = "WINNER_ID_KEY";

    private Player player1;
    private Player player2;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        db = DatabaseClient.getInstance(this).getAppDatabase();

        Button playagainBtn = findViewById(R.id.playgain_btn);
        playagainBtn.setOnClickListener(v -> playagainAction());

        Button homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(v -> homeAction());

        player1 = ((MorpionApplication) getApplication()).getPlayer1();
        player2 = ((MorpionApplication) getApplication()).getPlayer2();

        //set players names
        TextView player1name = findViewById(R.id.player1_name);
        player1name.setText(player1.getName());

        TextView player2name = findViewById(R.id.player2_name);
        player2name.setText(player2.getName());

        // set players images
        ImageView player1image = findViewById(R.id.player1_image);
        setPlayerImage(player1image, player1);

        ImageView player2image = findViewById(R.id.player2_image);
        setPlayerImage(player2image, player2);

        // set player result text
        TextView player1res = findViewById(R.id.player1_result);
        TextView player2res = findViewById(R.id.player2_result);
        int result = getIntent().getIntExtra(RESULT_KEY, 0);
        int winId = getIntent().getIntExtra(WINNER_ID_KEY, 0);
        if (result == 2) {
            player1res.setText("Égalité");
            player2res.setText("Égalité");
        }
        else {
            if(player1.getId() == winId) {
                player1res.setText("Victoire");
                player2res.setText("Défaite");
            }
            else {
                player1res.setText("Défaite");
                player2res.setText("Victoire");
            }
        }
    }

    public void playagainAction(){
        Intent intent = new Intent(this, MorpionCreateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void homeAction(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}