package com.AZtech_labs.joali.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AZtech_labs.joali.R;

public class GlobeHolder extends RecyclerView.ViewHolder {
    ImageView article_img,article_icon, article_like;
    TextView article_title, article_desc, article_edition;
    public GlobeHolder(@NonNull View itemView) {
        super(itemView);

        article_img = itemView.findViewById(R.id.article_img);
        article_icon = itemView.findViewById(R.id.article_icon);
        article_like = itemView.findViewById(R.id.article_like);
        article_title = itemView.findViewById(R.id.article_desc);
        article_desc = itemView.findViewById(R.id.article_desc);
        article_edition = itemView.findViewById(R.id.article_edition);
    }
}
