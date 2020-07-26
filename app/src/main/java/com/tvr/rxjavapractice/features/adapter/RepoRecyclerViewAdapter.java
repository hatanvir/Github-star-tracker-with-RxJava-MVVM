package com.tvr.rxjavapractice.features.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tvr.rxjavapractice.R;
import com.tvr.rxjavapractice.features.model.response.GithubRepo;

import java.util.List;

public class RepoRecyclerViewAdapter extends RecyclerView.Adapter<RepoRecyclerViewAdapter.Holder> {
    List<GithubRepo> list;
    Context context;

    public RepoRecyclerViewAdapter(List<GithubRepo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.recycler_view_shape,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.repoName.setText(list.get(position).getHtmlUrl()+" "+list.get(position).getowner().getAvaterUrl());
        Picasso.with(context).load(list.get(position).getowner().getAvaterUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,repoName;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView2);
            repoName = itemView.findViewById(R.id.textView);
        }
    }
}