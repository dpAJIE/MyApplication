package com.hrdcdn.user.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ApprovalActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mRef;
    private FirebaseRecyclerAdapter<Model1, ViewHolder> mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        setTitle("Daftar Persetujuan Karyawan Bar");

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView2);
        mRecyclerView.hasFixedSize();

        //Set Layout as Linear Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        //"Model" here will reflect what you have called your database in Firebase.
        mRef = FirebaseDatabase.getInstance().getReference().child("Approval");
        mRef.keepSynced(true);
        Query mQuery = mRef.orderByKey();

        FirebaseRecyclerOptions approvalList = new FirebaseRecyclerOptions.Builder<Model1>().setQuery(mQuery, Model1.class).build();

        mRecyclerViewAdapter = new FirebaseRecyclerAdapter<Model1, ApprovalActivity.ViewHolder>(approvalList) {
            @Override
            protected void onBindViewHolder(ApprovalActivity.ViewHolder holder, final int position, final Model1 model) {
                holder.setCabang(model.getCabang());
                holder.setNama(model.getNama());
                holder.setJob(model.getJob());
                holder.setAbsensi(model.getAbsensi());
            }

            @Override
            public ApprovalActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.approval_list, parent,false);

                return new ApprovalActivity.ViewHolder(view);
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
        public void setCabang(String cabang){
            TextView list_cabang = (TextView)mView.findViewById(R.id.list_cabang);
            list_cabang.setText(cabang);
        }

        public void setNama(String nama){
            TextView list_nama = (TextView)mView.findViewById(R.id.list_nama);
            list_nama.setText(nama);
        }
        public void setJob(String job){
            TextView list_job = (TextView)mView.findViewById(R.id.list_job);
            list_job.setText(job);
        }
        public void setAbsensi(String absensi){
            TextView list_absensi = (TextView)mView.findViewById(R.id.list_absensi);
            list_absensi.setText(absensi);
        }
    }
}
