package com.AZtech_labs.joali.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AZtech_labs.joali.BDD.Article;
import com.AZtech_labs.joali.R;
import com.AZtech_labs.joali.fragments.PrivilegieFragment;
import com.AZtech_labs.joali.services.DownloadImageTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class PrivilegieAdapter extends RecyclerView.Adapter<PrivilegieHolder> {
    private RealmResults<Article> listData;
    Activity activity;

    public PrivilegieAdapter(RealmResults<Article> data, Activity act) {
        this.activity = act;
        this.listData = data;

    }

    @NonNull
    @Override
    public PrivilegieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        return new PrivilegieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrivilegieHolder holder, int position) {
        Article item = listData.get(position);
        Log.e("Joalireg", item.getTitle()+"");
        holder.article_title.setText(item.getTitle());
        holder.article_desc.setText(item.getDesc());
        holder.article_edition.setText(item.getEdition());
        Picasso.get()
                .load(item.getImg())
                .placeholder(R.drawable.applicable)
                .error(R.drawable.applicable)
                .fit()
                .into(holder.article_img);


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
