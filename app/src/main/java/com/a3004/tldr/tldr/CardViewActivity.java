package com.a3004.tldr.tldr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Kristen on 2017-10-18.
 */

public class CardViewActivity extends Activity {

    ImageView articleImage;
    TextView articleCategory;
    TextView articleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.card_view_activity);
        articleImage = (ImageView)findViewById(R.id.imageView);
        articleCategory = (TextView)findViewById(R.id.title);
        articleText = (TextView)findViewById(R.id.content);

        articleImage.setImageResource(R.drawable.pepe1);
        articleCategory.setText("Breaking News!!");
        articleText.setText("Look at this!! It's PEPE!");
    }
}
