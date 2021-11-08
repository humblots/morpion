package com.example.morpion;

import static com.example.morpion.Utils.StrToBitMap;
import static com.example.morpion.Utils.setPlayerImage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morpion.db.Player;

public class MorpionCreateActivity extends AppCompatActivity {

    private Player player1;
    private Player player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion_create);

        Button changep1Btn = findViewById(R.id.p1change_btn);
        changep1Btn.setOnClickListener(v -> {changePlayerAction(v);});

        Button changep2Btn = findViewById(R.id.p2change_btn);
        changep2Btn.setOnClickListener(v -> {changePlayerAction(v);});

        Button playBtn = findViewById(R.id.play_btn);
        playBtn.setOnClickListener(v -> playAction());

        setPlayersDisplay();
    }

    private void setPlayersDisplay() {
        player1 = ((MorpionApplication) getApplication()).getPlayer1();
        player2 = ((MorpionApplication) getApplication()).getPlayer2();
        if (player1 != null) {
            ImageView p1image = findViewById(R.id.player1_image);
            setPlayerImage(p1image, player1);

            TextView p1name = findViewById(R.id.player1_name);
            p1name.setText(player1.getName());
        }
        if (player2 != null) {
            ImageView p2image = findViewById(R.id.player2_image);
            setPlayerImage(p2image, player2);
            TextView p2name = findViewById(R.id.player2_name);
            p2name.setText(player2.getName());
        }
    }

    private void changePlayerAction(View v) {
        int playerSelect;
        if (v.getId() == R.id.p1change_btn) {
            playerSelect = 1;
        }
        else {
            playerSelect = 2;
        }
        Intent intent = new Intent(MorpionCreateActivity.this, PlayerSelectActivity.class);
        intent.putExtra(PlayerSelectActivity.PLAYER_SELECT_KEY, playerSelect);
        startActivity(intent);
    }

    private void playAction(){
        if (player1 != null && player2 != null) {
            if(player1.getId() != player2.getId()) {
                Intent intent = new Intent(MorpionCreateActivity.this, MorpionGameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                EditText gridSizeInput = findViewById(R.id.gridsize);
                if (!gridSizeInput.getText().toString().isEmpty()) {
                    intent.putExtra(
                            MorpionGameActivity.GRIDSIZE_KEY,
                            Integer.parseInt(gridSizeInput.getText().toString())
                    );
                }

                startActivity(intent);
            } else {
                Toast.makeText(MorpionCreateActivity.this,
                        "Choisissez deux joueurs différents...",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            Toast.makeText(MorpionCreateActivity.this,
                    "Choisissez 2 joueurs",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
}