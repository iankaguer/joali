package com.AZtech_labs.joali.services;

import android.Manifest;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.AZtech_labs.joali.BDD.Article;
import com.AZtech_labs.joali.MainActivity;
import com.AZtech_labs.joali.R;
import com.AZtech_labs.joali.adapters.PrivilegieAdapter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BackgroundHome {
    OkHttpClient client = new OkHttpClient();

    Activity activity;

    public BackgroundHome(Activity act){
        this.activity = act;
    }

    public static void run( Activity activity) throws IOException {
        String url = "https://api.androidhive.info/json/shimmer/menu.php";


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Article> listData = new ArrayList<>();
                /*final String myResponse = response.body().string();
                Log.e("HTTP joali", myResponse);*/


                String jsonData = response.body().string();
                JSONArray Jobject = null;
                try {
                    Jobject = new JSONArray(jsonData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray Jarray = null;
               /* try {
                    Jarray = Jobject.toJSONArray();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                Realm realm = Realm.getDefaultInstance();

                RealmResults<Article> arts = realm.where(Article.class).findAll();
                realm.beginTransaction();
                arts.deleteAllFromRealm();

                realm.commitTransaction();

                for (int i = 0; i < Jobject.length(); i++){
                    try {
                        int nbr = realm.where(Article.class).findAll().size();

                        realm.beginTransaction();

                        Article article = new Article();
                        article.setId(nbr+1);
                        article.setTitle(Jobject.getJSONObject(i).getString("name"));
                        article.setDesc(Jobject.getJSONObject(i).getString("description"));
                        article.setEdition(Jobject.getJSONObject(i).getString("timestamp"));
                        article.setIcon(Jobject.getJSONObject(i).getString("chef"));
                        article.setImg(Jobject.getJSONObject(i).getString("thumbnail"));
                        article.setLike(0);
                        realm.copyToRealm(article);
                        realm.commitTransaction();
                          //Log.e("Joalireg", Jobject.getJSONObject(i).getString("name")+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
               // try {
                    //Jarray = Jobject.getJSONArray("data");
                   // Log.e("Joalireg", Jobject+"");

               /* } catch (JSONException e) {
                    e.printStackTrace();
                }*/

               /* for (int i = 0; i < Jarray.length(); i++) {
                    try {
                        JSONObject object     = Jarray.getJSONObject(i);
                        Article article = new Article(/*
                                object.getString("avatar"),
                                object.getString("avatar"),
                                object.getString("avatar"),
                                object.getString("first_name")+" blabla "+object.getString("last_name"),
                                object.getString("email"),
                                object.getString("email")*/
                             //   );

                     /*   article.setArticle_img(object.getString("avatar"));
                        article.setArticle_icon(object.getString("avatar"));
                        article.setArticle_like(object.getString("avatar"));
                        article.setArticle_title(object.getString("first_name")+" blabla "+object.getString("last_name"));
                        article.setArticle_desc(object.getString("email"));
                        article.setArticle_edition(object.getString("email"));
                        listData.add(article);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }*/

               /* activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView rv=(RecyclerView)activity.findViewById(R.id.list_priv);
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new LinearLayoutManager(activity.getBaseContext()));

                        PrivilegieAdapter adapter = new PrivilegieAdapter(listData, activity);
                        rv.setAdapter(adapter);
                    }
                });*/

            }
        });
    }

    public static void grantPermissions( Activity activity){
        Dexter.withActivity(activity).withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE
        ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }

        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
                Toast.makeText(activity.getBaseContext(), "Error occurred! " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }).onSameThread().check();
    }
}
