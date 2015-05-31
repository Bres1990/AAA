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

        int imagesToShow[] = {R.drawable.szkic1, R.drawable.szkic2, R.drawable.szkic3, R.drawable.owl};//, R.drawable.owlFont};

        animate(imageBoard, imagesToShow, 0, false);
    }

    private void animate(final ImageView imageView, final int images[], final int imageIndex, boolean when) {


        imageView.setVisibility(View.INVISIBLE);
        imageView.setImageResource(images[imageIndex]);

        //here go all the fading setting
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(500);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(500);

        //here are all animation settings
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(1);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationRepeat(Animation animation) {}

            public void onAnimationStart(Animation animation) {
            }


            public void onAnimationEnd(Animation animation) {
                if (images.length - 1 > imageIndex) {
                    animate(imageView, images, imageIndex + 1, when);
                }
                Intent intent = new Intent(logoAnimation.this, StartScreen.class);
                this.startActivity(intent);
            }
        });
    }
}
