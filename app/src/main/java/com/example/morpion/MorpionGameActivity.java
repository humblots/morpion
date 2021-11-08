package com.example.morpion;

import static com.example.morpion.Utils.StrToBitMap;
import static com.example.morpion.Utils.setPlayerImage;
import static com.example.morpion.Utils.setPlayerImage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morpion.Morpion.Game;
import com.example.morpion.db.AppDatabase;
import com.example.morpion.db.DatabaseClient;
import com.example.morpion.db.Player;

import java.util.ArrayList;
import java.util.List;

public class MorpionGameActivity extends AppCompatActivity {

    public static final String GRIDSIZE_KEY = "GRIDSIZE_KEY";
    private Game game;
    private AppDatabase db;
    private TextView turnView;
    private LinearLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion_game);
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
        int gridSize = getIntent().getIntExtra(GRIDSIZE_KEY, 3);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int gridCellWidth = (int)((displayMetrics.widthPixels - 50) / gridSize);

        Player player1 = ((MorpionApplication) this.getApplication()).getPlayer1();
        Player player2 = ((MorpionApplication) this.getApplication()).getPlayer2();

        game = new Game(player1, player2, gridSize);

        turnView = findViewById(R.id.turn_text);
        turnView.setText("Au tour de " + game.getCurrentPlayer().getName());

        grid = findViewById(R.id.grid_layout);
        for (int i = 0; i < gridSize; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setTag("" + i);

            for (int j = 0; j < gridSize; j++) {
                ImageView img = new ImageView(this);

                // set a border
                img.setBackgroundResource(R.drawable.iv_background);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(gridCellWidth, gridCellWidth);
                img.setLayoutParams(layoutParams);
                img.setTag("" + i + j);
                img.setOnClickListener(this::playerAction);
                row.addView(img);
            }
            grid.addView(row);
        }

        if(game.getCurrentPlayer().getId() == 1) {
            botAction();
        }
    }

    public void playerAction(View v) {
        int[] coords = new int[2];
        int i = 0;
        for (char coord : v.getTag().toString().toCharArray()) {
            coords[i] = Integer.parseInt("" + coord);
            i++;
        }

        int res = game.move(coords[0], coords[1]);
        if (res != -1) {
            setPlayerImage((ImageView) v, game.getCurrentPlayer());
            if (res != 0) {
                resultAction(res);
                return;
            }
            else {
                game.nextPlayer();
                turnView.setText("Au tour de " + game.getCurrentPlayer().getName());
                if(game.getCurrentPlayer().getId() == 1) {
                    botAction();
                }
            }
        }
    }

    private void botAction() {
        ArrayList<Integer> coord = game.botMoveCoord();
        LinearLayout row = grid.findViewWithTag("" + coord.get(0));
        ImageView iv = row.findViewWithTag("" + coord.get(0) + coord.get(1));
        int res = game.move(coord.get(0), coord.get(1));
        if(res != 0) {
            resultAction(res);
            return;
        }
        else {
            setPlayerImage(iv, game.getCurrentPlayer());
            game.nextPlayer();
            turnView.setText("Au tour de " + game.getCurrentPlayer().getName());
        }
    }

    private void resultAction(int resCode) {
        super.finish();
        updateScores(resCode);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.RESULT_KEY, resCode);
        intent.putExtra(ResultActivity.WINNER_ID_KEY, game.getCurrentPlayer().getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void updateScores(int resCode) {
        class updateScores extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                if (resCode == 1) {
                    db.playerDao().updateWins(game.getCurrentPlayer().getId());
                    db.playerDao().updateDefeats(game.getSecondPlayer().getId());
                } else {
                    db.playerDao().updateDraws(game.getCurrentPlayer().getId());
                    db.playerDao().updateDraws(game.getSecondPlayer().getId());
                }
                return null;
            }
        }
        updateScores us = new updateScores();
        us.execute();
    }
}