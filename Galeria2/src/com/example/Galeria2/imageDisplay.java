package com.example.Galeria2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.polites.android.GestureImageView;

import java.util.Date;


/**
 * Activity handling the display of selected image
 *
 * @author Adam
 * @since 08-05-2015
 */
public class imageDisplay extends Activity implements ActionBar.OnNavigationListener {
    ImageView image;
    Intent mainIntent;
    Dialog ratingDialog;
    RatingBar ratingBar;
    String name;
    Float userRankValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);


        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        GestureImageView view = new GestureImageView(this);

        image = (GestureImageView) findViewById(R.id.full_image);
        Uri imageURI = Uri.parse(getIntent().getStringExtra("ImageURI"));
        image.setImageURI(imageURI);

        view.setLayoutParams(params);

        mainIntent = new Intent();

        ActionBar imageActionBar = getActionBar();
        imageActionBar.setTitle(getIntent().getStringExtra("fileName")); //Title should be the file name TODO

    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

    public void imageClick(View view) {
        showDetails();
    }

    public void showDetails() {
        long modifiedData = getIntent().getLongExtra("last_modified", 0);
        Date modifiedDate = new Date(modifiedData);
        String modifiedDateString = String.valueOf(modifiedDate);
        Toast.makeText(getApplicationContext(), modifiedDateString, Toast.LENGTH_LONG).show();
    }

    /**
     * This method is called whenever a navigation item in your action bar
     * is selected.
     *
     * @param itemPosition Position of the item clicked.
     * @param itemId       ID of the item clicked.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.rating_item:
                ratingDialog = new Dialog(this);
                ratingDialog.setContentView(R.layout.ratingdialog);
                ratingDialog.setCancelable(true);
                ratingBar = (RatingBar)ratingDialog.findViewById(R.id.dialog_ratingbar);

                TextView text = (TextView)ratingDialog.findViewById(R.id.rank_dialog_text1);
                name = "Image rating";
                text.setText(name);

                Button updateButton = (Button)ratingDialog.findViewById(R.id.rank_dialog_button);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userRankValue = ratingBar.getRating();
                        ratingDialog.dismiss();
                    }
                });

                if (userRankValue != null) {
                    ratingBar.setRating(userRankValue);
                }

                //now that the dialog is set up, it's time to show it
                ratingDialog.show();

                return true;
            case R.id.locating_item:
                Toast.makeText(getApplicationContext(), "Localization stub", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
