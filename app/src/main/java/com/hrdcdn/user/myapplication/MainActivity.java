package com.hrdcdn.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseRecyclerAdapter<Model, ViewHolder> mRecyclerViewAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_hire:
                    startActivity(new Intent(MainActivity.this, RegisterEmployeActivity.class));
                    return true;
                case R.id.navigation_mpp:
                    startActivity(new Intent(MainActivity.this, BerkasActivity.class));
                    return true;
                case R.id.navigation_approval:
                    startActivity(new Intent(MainActivity.this, ApprovalActivity.class));
                    return true;
                case R.id.navigation_info:
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Capella Go!");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.hasFixedSize();

        //Set layout as Linear Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);


        //"Model" here will reflect what you have called your database in Firebase.
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("News");
        mFirebaseDatabase.keepSynced(true);
        Query mQuery = mFirebaseDatabase.orderByKey();

        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<Model>().setQuery(mQuery, Model.class).build();

        mRecyclerViewAdapter = new FirebaseRecyclerAdapter<Model, MainActivity.ViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(MainActivity.ViewHolder holder, final int position, final Model model) {
                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                holder.setImage(getBaseContext(), model.getImage());

            }

            @Override
            public MainActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_row, parent, false);

                return new MainActivity.ViewHolder(view);
            }
        };

        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRecyclerViewAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mRecyclerViewAdapter.stopListening();


    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public ViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context baseContext, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_image);
        }
    }

}
