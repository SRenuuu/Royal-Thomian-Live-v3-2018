package com.roytho.live.photos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.roytho.live.home.news.OfflineItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class LoadPhotos extends AsyncTask<Void, Void, Void> {

    Context context;
    ArrayList<PhotoItem> feedItems;
    RecyclerView recyclerView;
    boolean isNetwork;
    ProgressDialog pd;

    public LoadPhotos(Context context, RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        this.context = context;
        isNetwork = isNetworkAvailable(context);
        pd = new ProgressDialog(context);
        pd.setMessage("Loading photos");
        feedItems = new ArrayList<PhotoItem>();
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("Network There", String.valueOf(isNetwork));
        if(isNetwork){
            try {
                parseJSON();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void parseJSON() throws JSONException {

        String jsonString = null;
        try {
            jsonString = readJsonFromUrl("https://stcicts.org/roytho/getdata.php?request=photos");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray photos     =  new JSONArray(jsonString);

        for (int i = 0; i < photos.length(); ++i) {

            PhotoItem pi = new PhotoItem();

            JSONObject rec = photos.getJSONObject(i);
            String imgurl = rec.getString("1");
            String imgid = rec.getString("0");

            pi.setUrl(imgurl);
            pi.setID(imgid);

            feedItems.add(pi);

            //Log.e("fuckingtest",imgurl);

        }

    }

    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()&& cm.getActiveNetworkInfo().isAvailable()&& cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void onPreExecute() {
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        PhotoAdapter myAdapter = new PhotoAdapter(context, feedItems, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(myAdapter);
        pd.dismiss();
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }
}
