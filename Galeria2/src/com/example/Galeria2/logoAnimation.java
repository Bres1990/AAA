package com.example.Galeria2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
@author Joanna
 **/

public class logoAnimation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_main);

        ImageView imageBoard = (ImageView) findViewById(R.id.animationView);

        //obrazki dorzuce po polnocy
        int imagesToShow[] = {R.drawable.sketch1, R.drawable.sketch2, R.drawable.sketch3, R.drawable.owl, R.drawable.owlFont};

        animate(imageBoard, imagesToShow, 0);
    }


    private void animate(final ImageView imageView, final int images[], final int imageIndex) {

        int fadeInDuration = 1000, fadeOutDuration = 1000;
        int timeBetween = 1000;

        imageView.setVisibility(View.INVISIBLE);
        imageView.setImageResource(images[imageIndex]);

        //here go all the fading setting
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(fadeInDuration);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(fadeInDuration + timeBetween);
        fadeOut.setDuration(fadeOutDuration);

        //here are all animation settings
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(1);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {

                //show all images, and after let us go to the menu activity
                while (images.length - 1 > imageIndex) {
                    animate(imageView, images, imageIndex + 1);
                }
                startActivity(new Intent(logoAnimation.this, StartScreen.class));}

            public void onAnimationRepeat(Animation animation) {}

            public void onAnimationStart(Animation animation) {}
        });
    }
}
