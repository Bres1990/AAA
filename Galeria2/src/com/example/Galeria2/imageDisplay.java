package com.example.Galeria2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.polites.android.GestureImageView;

import java.net.URI;

/**
 * Activity handling the display of selected image
 * @author Adam
 * @since 08-05-2015
 * */
public class imageDisplay extends Activity {
    ImageView image;
    Intent mainIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);


        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        GestureImageView view = new GestureImageView(this);

        image = (GestureImageView)findViewById(R.id.full_image);
        Uri imageURI = Uri.parse(getIntent().getStringExtra("ImageURI"));
        image.setImageURI(imageURI);

        view.setLayoutParams(params);

        mainIntent = new Intent();
    }

    public void imageClick(View view) {
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

}
