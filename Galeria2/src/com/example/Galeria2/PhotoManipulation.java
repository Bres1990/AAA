package com.example.Galeria2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asia on 14.05.2015.
 */
public class PhotoManipulation extends Activity{

    File photoFile;
    Intent intent;
    static  final int REQUEST_TAKE_PHOTO=1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photomanipulation);

        intent = getIntent();
        Bitmap photo= (Bitmap) intent.getParcelableExtra("BitmapImage");

        ImageView pictureTaken = (ImageView) findViewById(R.id.photoView);

        pictureTaken.setImageBitmap(photo);

        Button save = (Button) findViewById(R.id.save);
        Button delete = (Button) findViewById(R.id.delete);
    }


    public void onClick(View view) throws IOException {

        if (view.getId() == R.id.save) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }

            if (photoFile != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
            }

            finish();
        }
        if(view.getId() == R.id.delete){
            finish();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat ("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
