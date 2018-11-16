package com.androidapp.mcs.tvseriesguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidapp.mcs.tvseriesguide.adapter.EpisodeAdapter;
import com.androidapp.mcs.tvseriesguide.database.AppDatabase;
import com.androidapp.mcs.tvseriesguide.model.Episode;
import com.androidapp.mcs.tvseriesguide.model.EpisodeResponse;
import com.androidapp.mcs.tvseriesguide.service.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidapp.mcs.tvseriesguide.DetailsActivity.MY_GLOBAL_PREFS;


public class EpisodeActivity extends AppCompatActivity {

    private static final String TAG = "episode_activity";
    public static int FLAG = 0;
    List<EpisodeResponse> episodeList;
    Episode episode;
    EpisodeResponse episodeResponse;
    RecyclerView recyclerView;
    public String seasonNo;
    public String imdbID;

    private AppDatabase db;  //Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        if (FLAG == 0) {
            Intent intent = getIntent();
            if (intent != null) {

                imdbID = getIntent().getExtras().getString(SeasonActivity.SERIESID);
                seasonNo = getIntent().getExtras().getString(SeasonActivity.SEASONID);
                // Toast.makeText(this, "Received Data\t"+imdbID+"\t"+seasonNo, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Didn't receive data", Toast.LENGTH_SHORT).show();
            }
        } else {
            SharedPreferences preferences = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE);
            imdbID = preferences.getString(DetailsFragment.IMDB_KEY, "tt0944947");   //second parameter indicated the default value that will be passed if the Key and its corresponding value isnt found
            seasonNo = preferences.getString(DetailsFragment.SEASON_KEY, "1");
        }

        recyclerView = findViewById(R.id.episode_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        WebService webService = WebService.retrofit.create(WebService.class);
        Call<Episode> call = webService.getEpisodes(imdbID, seasonNo);
        getData(call);


    }

    private void getData(Call<Episode> call) {
        call.enqueue(new Callback<Episode>() {
            @Override
            public void onResponse(Call<Episode> call, Response<Episode> response) {
                if (response.isSuccessful()) {

                    episode = response.body();
                    episodeList = episode.getEpisodes();
                    // Toast.makeText(EpisodeActivity.this, "Received\t"+episode.getEpisodes().size()+"\tepisodes from service", Toast.LENGTH_SHORT).show();
//                    if(FLAG == 0)
//                    {
//                        insertDataIntoDatabase(episodeList);
//                    }
                    displayData();

                } else {
                    Toast.makeText(EpisodeActivity.this, "Json response unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Episode> call, Throwable t) {
                Log.i("onFailure: ", t.getMessage());
            }
        });
    }

    private void displayData() {
        if (episodeList != null) {
            FLAG = 1;
            EpisodeAdapter episodeAdapter = new EpisodeAdapter(episodeList, seasonNo, imdbID, this);
            recyclerView.setAdapter(episodeAdapter);
        }
    }

//    private void insertDataIntoDatabase(List<EpisodeResponse> episodeList) {
//
//        //Initialize Database
//        db = AppDatabase.getInstance(this);
//        int itemCount = db.seriesDao().countItems();
//        if (itemCount == 0) {
//            List<EpisodeResponse> episodeList1 = episodeList;
//            db.episodeResponseDao().insertAllEpisodeResponses(episodeList1);
//            Log.i(TAG, "Data Inserted!");
//        } else {
//            Log.i(TAG, "Data already exists ");
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: Saving persistent state");
        SharedPreferences.Editor editor = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();  //Always select MODE_PRIVATE
        //warning is displayed because I specified .edit()
        editor.putString(DetailsFragment.IMDB_KEY, imdbID);                                          // but haven't commited or applied  changes
        editor.putString(DetailsFragment.SEASON_KEY, seasonNo);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();  //Always select MODE_PRIVATE
        //warning is displayed because I specified .edit()
        editor.putString(DetailsFragment.IMDB_KEY, imdbID);                                          // but haven't commited or applied  changes
        editor.putString(DetailsFragment.SEASON_KEY, seasonNo);
        editor.apply();
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

}
