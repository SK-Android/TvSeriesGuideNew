package com.androidapp.mcs.tvseriesguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.mcs.tvseriesguide.adapter.SeasonAdapter;
import com.androidapp.mcs.tvseriesguide.adapter.SeriesAdapter;
import com.androidapp.mcs.tvseriesguide.database.AppDatabase;
import com.androidapp.mcs.tvseriesguide.model.Episode;
import com.androidapp.mcs.tvseriesguide.model.SeasonData;
import com.androidapp.mcs.tvseriesguide.model.Seasons;
import com.androidapp.mcs.tvseriesguide.model.Series;

import java.util.ArrayList;
import java.util.List;

import static com.androidapp.mcs.tvseriesguide.DetailsActivity.MY_GLOBAL_PREFS;
import static com.androidapp.mcs.tvseriesguide.adapter.SeriesAdapter.IMDBID;
import static com.androidapp.mcs.tvseriesguide.adapter.SeriesAdapter.TITLEID;

public class SeasonActivity extends AppCompatActivity {

    private static final String TAG = "season_activity";
    public static int FLAG = 0;
    public static final String SEASONID = "season_key";
    public static final String SERIESID = "series_key";
    private static final int SEASON_KEY = 1001;

    Episode episode;
    String seasonNo = "1";
    String imdbID;

    List<Seasons> seasonList = SeasonData.seasonsList;
    public String seriesTitle;

    private AppDatabase db;  //Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);

        if (FLAG == 0) {
            Intent intent = getIntent();
            if (intent != null) {
                imdbID = intent.getStringExtra(IMDBID);
                seriesTitle = intent.getStringExtra(TITLEID);
                Toast.makeText(this, "" + seriesTitle, Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Didn't receive data", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();  //Always select MODE_PRIVATE
            editor.putString(SeriesAdapter.TITLEID, seriesTitle);                                          // but haven't commited or applied  changes
            editor.apply();
        }

        TextView textView = findViewById(R.id.serieTitle);
        textView.setText(seriesTitle);

//        if(FLAG == 0) {
//            insertDataIntoDatabase(seasonList); //Database call to save data before displaying
//        }

        final SeasonAdapter adapter = new SeasonAdapter(this, seasonList);
        final ListView listView = findViewById(R.id.seasonListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SeasonActivity.this, "You Selected", Toast.LENGTH_SHORT).show();
                //***********************
                FLAG = 1;
                String seasonNo = adapter.getItem(position).getItemId();
                Intent intent1 = new Intent(SeasonActivity.this, EpisodeActivity.class);
                intent1.putExtra(SERIESID, imdbID);
                intent1.putExtra(SEASONID, seasonNo);
                Toast.makeText(SeasonActivity.this, "You selected\t" + "Season: " + seasonNo + "\tof\t" + seriesTitle, Toast.LENGTH_LONG).show();
                SeasonActivity.this.startActivityForResult(intent1, SEASON_KEY);


            }
        });

    }

//    private void insertDataIntoDatabase(List<Seasons> seasonList) {
//        //Initialize Database
//        db = AppDatabase.getInstance(this);
//        int itemCount = db.seriesDao().countItems();
//        if (itemCount == 0) {
//            List<Seasons> seasonList1 = seasonList;
//            db.seasonsDao().insertAllSeasons(seasonList1);
//            Log.i(TAG, "Data Inserted!");
//        } else {
//            Log.i(TAG, "Data already exists ");
//        }
//    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();  //Always select MODE_PRIVATE
        //warning is displayed because I specified .edit()
        editor.putString(DetailsFragment.IMDB_KEY, imdbID);                                          // but haven't commited or applied  changes
        editor.putString(DetailsFragment.SEASON_KEY, seasonNo);
        editor.apply();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

//        @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode == RESULT_OK && requestCode == SEASON_KEY)
//        {
//                imdbID = data.getStringExtra(EpisodeAdapter.ITEM_KEY);
//                seriesTitle = data.getStringExtra(EpisodeAdapter.SERIE_TITLE);
//                Toast.makeText(this, "ID:\t"+imdbID+"\tSeason:\t"+seriesTitle,+ Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
}
