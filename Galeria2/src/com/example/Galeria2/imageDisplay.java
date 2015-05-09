package com.example.Galeria2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

        image = (ImageView)findViewById(R.id.full_image);
        Bitmap bitmap = getIntent().getParcelableExtra("BitmapImage");
        image.setImageBitmap(bitmap);

        mainIntent = new Intent();
    }

    public void imageClick(View view) {
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

}
