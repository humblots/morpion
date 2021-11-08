package com.example.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.morpion.db.AppDatabase;
import com.example.morpion.db.DatabaseClient;
import com.example.morpion.db.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectActivity extends AppCompatActivity {

    private AppDatabase db;
    private PlayersAdapter adapter;
    public static final String PLAYER_SELECT_KEY = "PLAYER_SELECT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);

        db = DatabaseClient.getInstance(this).getAppDatabase();
        int playerSelect = getIntent().getIntExtra(PLAYER_SELECT_KEY, 1);

        ListView playersList = findViewById(R.id.playersList);
        adapter = new PlayersAdapter(this, new ArrayList<Player>());
        playersList.setAdapter(adapter);

        playersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                // Récupération de la tâche cliquée à l'aide de l'adapter
                Player player = adapter.getItem(pos);

                //Set global variable
                if (playerSelect == 1) {
                    ((MorpionApplication) getApplication()).setPlayer1(player);
                }
                else if (playerSelect == 2){
                    ((MorpionApplication) getApplication()).setPlayer2(player);
                }

                Intent intent = new Intent(PlayerSelectActivity.this, MorpionCreateActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void getPlayers() {
        ///////////////////////
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class GetPlayers extends AsyncTask<Void, Void, List<Player>> {

            @Override
            protected List<Player> doInBackground(Void... voids) {
                List<Player> playersList = db
                        .playerDao()
                        .getAll();
                return playersList;
            }

            @Override
            protected void onPostExecute(List<Player> playersList) {
                super.onPostExecute(playersList);

                // Mettre à jour l'adapter avec la liste de taches
                adapter.clear();
                adapter.addAll(playersList);

                // Now, notify the adapter of the change in source
                adapter.notifyDataSetChanged();
            }
        }

        GetPlayers gp = new GetPlayers();
        gp.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPlayers();
    }
}