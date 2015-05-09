package com.example.Galeria2;

import android.app.Activity;
import android.os.Bundle;

/**
 * Drawing activity is a simple container activity. It works differently when phone orientation changes.
 *<br>
 *     In <b>landscape mode</b> activity contains two fragments - {@link ToolbarFragment} and {@link DrawingFragment}.
 *     Those two fragments communicate through this activity.
 *<br>
 *     In <b>portrait mode</b> activity consists of a single {@link DrawingFragment}.
 *     Upon clicking on specified option in ActionBar or a button (to be decided) an intent starts {@link ToolbarActivity}.
 *     (Optional: swipe)
 * @author Ala
 * @since 08-05-2015
 *
 */
public class DrawingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawingactivity);
    }
}