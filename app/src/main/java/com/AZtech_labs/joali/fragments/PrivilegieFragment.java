package com.AZtech_labs.joali.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.AZtech_labs.joali.BDD.Article;
import com.AZtech_labs.joali.BDD.RealmController;
import com.AZtech_labs.joali.R;
import com.AZtech_labs.joali.adapters.PrivilegieAdapter;

import io.realm.Realm;
import io.realm.RealmResults;

public class PrivilegieFragment extends Fragment {
    Realm realm;
    RecyclerView list_priv;
    PrivilegieAdapter adapter;
    public static PrivilegieFragment newInstance() {
        PrivilegieFragment fragment = new PrivilegieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_privilegie, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        this.realm = RealmController.with(this).getRealm();
        list_priv = view.findViewById(R.id.list_priv);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        list_priv.setLayoutManager(layoutManager);

        RealmResults<Article> data = realm.where(Article.class).findAll();


       adapter = new PrivilegieAdapter(data, getActivity());
       list_priv.setAdapter(adapter);
       list_priv.hasFixedSize();


    }
}
