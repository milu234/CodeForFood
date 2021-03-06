package com.example.milan.codeforfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class LearnMore extends AppCompatActivity {

    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);


        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

        v_flipper = findViewById(R.id.v_flipper);
//
//        for (int i =0 ; i<images.length ; i++){
//            flipperImages(images[i]);
        for (int image: images){
            flipperImages(image);
        }

    }
     public void flipperImages(int image){
         ImageView imageView = new ImageView(this);
         imageView.setBackgroundResource(image);


         v_flipper.addView(imageView);
         v_flipper.setFlipInterval(4000);//4seconds
         v_flipper.setAutoStart(true);

         //animation
         v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
         v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);

     }
}
