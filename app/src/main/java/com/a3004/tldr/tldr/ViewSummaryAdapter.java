package com.a3004.tldr.tldr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewSummaryAdapter extends ArrayAdapter<Summary> {
    Context mContext;
    private ArrayList<Summary> summaries;
    Firebase mFirebase = new Firebase(mContext);
    private Summary currSummary;
    ViewHolder viewHolder;
    private static class ViewHolder {
        ImageButton upvote;
        ImageButton downvote;
        TextView summary;
        TextView username;
    }

    public ViewSummaryAdapter(ArrayList<Summary> summaries, Context context){
        super (context, R.layout.summary_item, summaries);
        this.summaries = summaries;
        this.mContext = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        currSummary = summaries.get(position);

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.summary_item, parent, false);
            viewHolder.upvote = convertView.findViewById(R.id.upvote_button);
            viewHolder.downvote = convertView.findViewById(R.id.downvote_button);
            viewHolder.summary = convertView.findViewById(R.id.summary);
            viewHolder.username = convertView.findViewById(R.id.username);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.summary.setText(currSummary.getContent());
        viewHolder.upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.child("summaries").
                                child(currSummary.getUrl())
                                .child(currSummary.getUID())
                                .child("summary")
                                .child("upvotes")
                                .hasChild(mFirebase.getFirebaseAuth().getCurrentUser().getUid())){

                            mFirebase.getDatabaseReference()
                                    .child("summaries")
                                    .child(currSummary.getUrl())
                                    .child(currSummary.getUID())
                                    .child("summary")
                                    .child("upvotes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid()).
                                    setValue(1);

                            mFirebase.getDatabaseReference()
                                    .child("summaries")
                                    .child(currSummary.getUrl())
                                    .child(currSummary.getUID())
                                    .child("summary")
                                    .child("downvotes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .removeValue();

                            viewHolder.upvote.setImageResource(R.drawable.ic_thumb_up_green_24dp);
                            viewHolder.downvote.setImageResource(R.drawable.ic_thumb_down_black_24dp);
                        } else {
                            mFirebase.getDatabaseReference()
                                    .child("summaries")
                                    .child(currSummary.getUrl())
                                    .child(currSummary.getUID())
                                    .child("summary")
                                    .child("upvotes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .removeValue();
                            viewHolder.upvote.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                        }

                        if(!dataSnapshot.child("summaries").
                                child(currSummary.getUrl())
                                .child(currSummary.getUID())
                                .child("summary")
                                .child("downvotes")
                                .hasChild(currSummary.getUID())) {

                            mFirebase.getDatabaseReference()
                                    .child("summaries")
                                    .child(currSummary.getUrl())
                                    .child(currSummary.getUID())
                                    .child("summary")
                                    .child("downvotes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        viewHolder.downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.child("summaries").
                                child(currSummary.getUrl())
                                .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                .child("summary")
                                .child("downvotes")
                                .hasChild(mFirebase.getFirebaseAuth().getCurrentUser().getUid())){

                            mFirebase.getDatabaseReference()
                                    .child("summaries")
                                    .child(currSummary.getUrl())
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .child("summary")
                                    .child("downvotes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid()).
                                    setValue(1);

                            mFirebase.getDatabaseReference()
                                    .child("summaries")
                                    .child(currSummary.getUrl())
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .child("summary")
                                    .child("upvotes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .removeValue();

                            viewHolder.downvote.setImageResource(R.drawable.ic_thumb_down_red_900_24dp);
                            viewHolder.upvote.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                        } else {
                            mFirebase.getDatabaseReference()
                                    .child("summaries")
                                    .child(currSummary.getUrl())
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .child("summary")
                                    .child("downvotes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .removeValue();
                            viewHolder.downvote.setImageResource(R.drawable.ic_thumb_down_black_24dp);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        mFirebase.getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                viewHolder.username.setText((String) dataSnapshot.child("users").child(currSummary.getUID()).child("username").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mFirebase.getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("summaries").
                        child(currSummary.getUrl())
                        .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                        .child("summary")
                        .child("upvotes")
                        .hasChild(mFirebase.getFirebaseAuth().getCurrentUser().getUid())){
                    viewHolder.upvote.setImageResource(R.drawable.ic_thumb_up_green_24dp);
                }
                if(dataSnapshot.child("summaries").
                        child(currSummary.getUrl())
                        .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                        .child("summary")
                        .child("downvotes")
                        .hasChild(mFirebase.getFirebaseAuth().getCurrentUser().getUid())){
                    viewHolder.downvote.setImageResource(R.drawable.ic_thumb_down_red_900_24dp);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return convertView;
    }
}
