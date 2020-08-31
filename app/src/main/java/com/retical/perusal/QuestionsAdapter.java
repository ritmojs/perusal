package com.retical.perusal;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retical.perusal.ItemClickListener;

import java.util.ArrayList;
public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    int position;



    ItemClickListener itemClickListener;
    // ArrayList<Image> images=new ArrayList<>();
    ArrayList<String> items=new ArrayList<>();
    static ArrayList<String> urls=new ArrayList<>();
    public void update(String name,String url)
    {
        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }
    public QuestionsAdapter() {
    }


    public QuestionsAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls,ItemClickListener itemClickListener) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls=urls;
        this.itemClickListener = itemClickListener;
        // this.images=images;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.FileName.setText(items.get(position));
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView FileName;
        ImageView FileImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FileName=itemView.findViewById(R.id.FileName);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());

        }
    }

}
