package com.joe.owo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.white.progressview.HorizontalProgressView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import models.Project;

public class projectAdapter extends RecyclerView.Adapter<projectAdapter.myViewholder> {

    ArrayList<Project> mdata;
    Context context;

    public projectAdapter(ArrayList<Project> mdata,Context mContext) {
        this.mdata = mdata;
        this.context = mContext;

    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.project_list_item, parent, false);

        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {

        holder.project_title_tv.setText(mdata.get(position).getTitle());
        holder.contributeAmount_tv.setText(mdata.get(position).getCurrentBal().toString());
        holder.totalAmount_tv.setText(mdata.get(position).getTotalAmount().toString());
        holder.projectOwner_tv.setText(mdata.get(position).getOwner());
        double total = Double.parseDouble(mdata.get(position).getTotalAmount());
        double contributed = Double.parseDouble(mdata.get(position).getCurrentBal());
        double percentage = (contributed / total) * 100;
        holder.progressView.setProgress((int) percentage);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    static class myViewholder extends RecyclerView.ViewHolder {

        CircleImageView feautredImage;
        MaterialTextView project_title_tv, projectOwner_tv, contributeAmount_tv, totalAmount_tv;
        HorizontalProgressView progressView;

        public myViewholder(@NonNull View itemView) {
            super(itemView);
            feautredImage = itemView.findViewById(R.id.project_avatar);
            project_title_tv = itemView.findViewById(R.id.project_title_tv);
            projectOwner_tv = itemView.findViewById(R.id.project_ownername_tv);
            contributeAmount_tv = itemView.findViewById(R.id.project_contibute_amount);
            totalAmount_tv = itemView.findViewById(R.id.project_total_tv);
            progressView = itemView.findViewById(R.id.project_progress);

        }
    }
}
