/*
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.a3004.tldr.tldr.Article;
import com.a3004.tldr.tldr.R;

import java.util.List;

package com.a3004.tldr.tldr;

public class CardViewActivity extends ArrayAdapter<String> {
    private Activity context;
    private List<Article> articles;

    public CardViewActivity(Activity context, List<Article> articles){
        super(context, R.layout.item);
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View cardListView = inflater.inflate(R.layout.item, null, true);
        TextView textViewTitle = (TextView) cardListView.findViewById(R.id.title1);
        TextView textViewContent = (TextView) cardListView.findViewById(R.id.content1);

        Article article = articles.get(position);

        textViewTitle.setText(article.getArticleTitle());
        textViewContent.setText(article.getArticleDescription());

        return cardListView;
    }

    /*ImageView articleImage;
    TextView articleCategory;
    TextView articleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item);
        articleImage = (ImageView)findViewById(R.id.image_view1);
        articleCategory = (TextView)findViewById(R.id.title);
        articleText = (TextView)findViewById(R.id.content1);

        articleImage.setImageResource(R.drawable.pepe1);
        articleCategory.setText("-- Default Category --");
        articleText.setText("-- Default Text --");
    }
}*/


