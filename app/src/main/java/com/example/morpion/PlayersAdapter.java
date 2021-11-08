package com.example.morpion;

import static com.example.morpion.Utils.setPlayerImage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morpion.db.Player;

import java.util.List;

public class PlayersAdapter extends ArrayAdapter<Player> {

    public PlayersAdapter(Context ctx, List<Player> playersList) {
        super(ctx, R.layout.template_player, playersList);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        // Récupération du joueur
        final Player player = getItem(pos);

        // Charge le template_player
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_player, parent, false);

        // Récupération des éléments graphiques dans le template
        TextView nameView = rowView.findViewById(R.id.player_name);
        nameView.setText(player.getName());

        TextView winsView = rowView.findViewById(R.id.player_wins);
        winsView.setText("" + player.getWins());

        TextView drawsView = rowView.findViewById(R.id.player_draws);
        drawsView.setText("" + player.getDraws());

        TextView defeatsView = rowView.findViewById(R.id.player_def);
        defeatsView.setText("" + player.getDefeats());

        ImageView pictureView = rowView.findViewById(R.id.player_image);
        setPlayerImage(pictureView, player);

        return rowView;
    }

}
