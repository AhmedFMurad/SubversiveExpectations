package com.a3004.tldr.tldr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends Activity {

    ImageView articleImage;
    TextView articleCategory;
    TextView articleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item);
        articleImage = (ImageView)findViewById(R.id.image_view);
        articleCategory = (TextView)findViewById(R.id.title);
        articleText = (TextView)findViewById(R.id.content);

        articleImage.setImageResource(R.drawable.pepe1);
        articleCategory.setText("-- Default Category --");
        articleText.setText("-- Default Text --");
    }
}
