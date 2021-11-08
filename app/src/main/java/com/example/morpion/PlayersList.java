package com.example.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.morpion.db.AppDatabase;
import com.example.morpion.db.DatabaseClient;
import com.example.morpion.db.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersList extends AppCompatActivity {

    private AppDatabase db;
    private PlayersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);

        db = DatabaseClient.getInstance(this).getAppDatabase();

        ListView playersList = findViewById(R.id.playersList);
        adapter = new PlayersAdapter(this, new ArrayList<Player>());
        playersList.setAdapter(adapter);
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