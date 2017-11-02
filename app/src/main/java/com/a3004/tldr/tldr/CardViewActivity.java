package com.a3004.tldr.tldr;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CardViewActivity extends BaseAdapter {

    private Context context;
    private ArrayList<Article> articles;

    public CardViewActivity(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }


    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.item2, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        ImageView image = (ImageView) convertView.findViewById(R.id.image_view);

        if(position == 0) {
            image.setImageResource(R.drawable.obama);
        } else if(position == 1) {
            image.setImageResource(R.drawable.trump);
        } else if(position == 3) {
            image.setImageResource(R.drawable.trump_putin);
        } else if(position == 4) {
            image.setImageResource(R.drawable.pepe1);
        } else if(position == 2) {
            image.setImageResource(R.drawable.pepe2);
        }
        Article article = articles.get(position);

        title.setText(article.getArticleTitle());
        content.setText(article.getArticleDescription());


        return convertView;
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
    }*/
}
