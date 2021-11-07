package com.example.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.morpion.db.DatabaseClient;
import com.example.morpion.db.Player;
import com.example.morpion.db.Score;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPlayerActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    private DatabaseClient db;
    private ImageView profilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        db = DatabaseClient.getInstance(getApplicationContext());
        profilepic = findViewById(R.id.profilepic);
    }

    private void savePlayer() {
        // Convert bitmap pic into string
        Bitmap profilePic = ((BitmapDrawable)profilepic.getDrawable()).getBitmap();
        String strPP = BitMapToString(profilePic);
        EditText nameEdit = findViewById(R.id.name);
        String name = nameEdit.getText().toString().trim();

        if (name.isEmpty()) {
            nameEdit.setError("Nom obligatoire");
            nameEdit.requestFocus();
            return;
        }

        class savePlayer extends AsyncTask<Void, Void, Player> {

            @Override
            protected Player doInBackground(Void... voids){

                //creation d'un joueur et de son score
                Player player = new Player(name, strPP);
                Score score = new Score(player.getId(), 0, 0, 0);

                //On ajoute le joueur et son score à la BD
                db.getAppDatabase().playerDao().insert(player);
                db.getAppDatabase().scoreDao().insert(score);
                return player;
            }

            @Override
            protected void onPostExecute(Player player) {
                super.onPostExecute(player);

                // Quand la tache est créée, on arrête l'activité AddPlayerActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

        savePlayer sp = new savePlayer();
        sp.execute();
    }

    public void onClickAddImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profilepic.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Quelque chose s'est mal déroulée...", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Vous n'avez pas choisis d'image",Toast.LENGTH_LONG).show();
        }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}