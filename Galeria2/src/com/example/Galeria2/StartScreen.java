package com.example.Galeria2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.Galeria2.PhotoManipulation;

import java.io.File;
import java.util.Hashtable;


public class StartScreen extends Activity {

    private static final int CAMERA_REQUEST = 2345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introscreen);
        String TAG = "Typefaces";
        Typeface menuFont = null;
        //powinno ustawiac czcionke

        final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

        synchronized (cache) {
            if (!cache.containsKey("fonts/cambria.ttf")) {
                try {
                    menuFont = Typeface.createFromAsset(this.getAssets(),
                            "fonts/cambria.ttf");
                    cache.put("fonts/cambria.ttf", menuFont);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get typeface '" + "fonts/cambria.ttf"
                            + "' because " + e.getMessage());
                }
            }
        }

        //Typeface menuFont=Typeface.createFromAsset(getAssets(), "fonts/cambria.ttf");
    if(menuFont!=null) {
        Button gallery = (Button) findViewById(R.id.gallery);
        gallery.setTypeface(menuFont);

        Button draw = (Button) findViewById(R.id.draw);
        gallery.setTypeface(menuFont);

        Button takePhoto = (Button) findViewById(R.id.photo);
        gallery.setTypeface(menuFont);
    }
    }


    public void onClick(View view) {

        /*
        opens the gallery
         */
        if (view.getId() == R.id.gallery) {
                Intent selectedIntent = new Intent(StartScreen.this, MainActivity.class);
                startActivityForResult(selectedIntent,2222);


        }

        /*
        opens drawing tool
         */
        if  (view.getId() == R.id.draw){
                Intent selectedIntent = new Intent(StartScreen.this, DrawingActivity.class);
                startActivityForResult(selectedIntent,3333);

        }

        /*
        opens camera
         */
        if (view.getId() == R.id.photo){
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //osobna activity na wyswietlanie zdjecia?
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Intent selectedIntent = new Intent(StartScreen.this, PhotoManipulation.class);
            selectedIntent.putExtra("BitmapImage", photo);
            StartScreen.this.startActivity(selectedIntent);
        }
    }
}
