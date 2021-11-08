package com.example.morpion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.example.morpion.db.Player;

import java.io.ByteArrayOutputStream;

public class Utils {

    public static void setPlayerImage(ImageView v, Player p) {
        if(!p.getPicture().isEmpty()){
            Bitmap bm = StrToBitMap(p.getPicture());
            v.setImageBitmap(bm);
        } else {
            v.setImageResource(R.drawable.playerdefault);
            BitmapDrawable drawable = (BitmapDrawable) v.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            v.setImageBitmap(bitmap);
        }
    }

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap StrToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
