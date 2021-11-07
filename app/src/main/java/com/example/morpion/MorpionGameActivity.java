package com.example.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.morpion.Morpion.Game;
import com.example.morpion.db.Player;

public class MorpionGameActivity extends AppCompatActivity {

    public static final String GRIDSIZE_KEY = "GRIDSIZE_KEY";
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion_game);

        int gridSize = getIntent().getIntExtra(GRIDSIZE_KEY, 3);

        Player player1 = ((MorpionApplication) this.getApplication()).getPlayer1();
        Player player2 = ((MorpionApplication) this.getApplication()).getPlayer2();

        game = new Game(player1, player2, gridSize);

        LinearLayout grid = findViewById(R.id.grid_layout);
        for(int i = 0; i < gridSize; i++) {
            LinearLayout row = new LinearLayout(this);
            for(int j = 0; j < gridSize; j++) {
                Button btn = new Button(this);
                btn.setTag(new String("" + i + j));
                btn.setText(btn.getTag().toString());
                btn.setOnClickListener(this::playerAction);
                row.addView(btn);
            }
            grid.addView(row);
        }
    }

    public void playerAction(View v) {
        int[] coords = new int[2];
        int i = 0;
        for (char coord: v.getTag().toString().toCharArray()) {
            coords[i] = Integer.parseInt(""+coord);
            i++;
        }

        int res = game.move(coords[0], coords[1]);

        if(res != -1) {
            ((Button) v).setText(game.getCurrentPlayer().getName());
            if (res == 1) {
                Toast.makeText(this, game.getCurrentPlayer().getName() + " a gagné", Toast.LENGTH_LONG).show();
            }
            if (res == 2) {
                Toast.makeText(
            this,
            "égalité entre " +
                game.getPlayers()[0].getName() + " et " +
                game.getPlayers()[1].getName(), Toast.LENGTH_LONG).show();
            }
            game.nextPlayer();
        }
    }

}