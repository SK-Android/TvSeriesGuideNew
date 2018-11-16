package com.androidapp.mcs.tvseriesguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.mcs.tvseriesguide.adapter.EpisodeAdapter;
import com.androidapp.mcs.tvseriesguide.model.Details;
import com.androidapp.mcs.tvseriesguide.model.Episode;
import com.androidapp.mcs.tvseriesguide.model.EpisodeResponse;
import com.androidapp.mcs.tvseriesguide.model.Rating;
import com.androidapp.mcs.tvseriesguide.service.WebService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity implements DetailsFragment.FragmentListener {
    public static final String TAG = "DetailsActivity";
    public static final String MY_GLOBAL_PREFS = "my_global_prefs";
    Details details;
    Rating rating;
    ConstraintLayout constraintLayout;
    public static final String FRAGMENT_TAG = "fragment_tag";
    public String imdbId;      //IMDB Id
    public String seasonNumber;  //Season number
    public String episodeNumber;//  Episode number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        if (intent != null) {
            imdbId = intent.getStringExtra(EpisodeAdapter.ITEM_KEY);
            seasonNumber = intent.getStringExtra(EpisodeAdapter.SEASON_NUM);
            episodeNumber = intent.getStringExtra(EpisodeAdapter.EPISODE_NUM);

            Toast.makeText(this, "Received Data", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Didn't receive data", Toast.LENGTH_SHORT).show();

        Bundle arguments = new Bundle();
        arguments.putString(DetailsFragment.IMDB_KEY, imdbId);
        arguments.putString(DetailsFragment.SEASON_KEY, seasonNumber);
        arguments.putString(DetailsFragment.EPISODE_KEY, episodeNumber);

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(arguments);    //Accepts a bundle object of key value pairs
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, detailsFragment)       //pass resource id of container and fragment object
                .commit();

        constraintLayout = findViewById(R.id.details_const);

    }


    @Override
    public void onFragmentFinish(String imdbId, String seasonNo, String episodeNo) { //Get data from Details Fragment

        if (imdbId != null && seasonNo != null && episodeNo != null) {
//            Toast.makeText(this, "ID:\t"+imdbId+"\tSeason:\t"+seasonNo,+ Toast.LENGTH_SHORT).show();

            Log.i(TAG, "onFragmentFinished:" + "Id" + imdbId + "Season Number:" + seasonNo);

            SharedPreferences.Editor editor = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();  //Always select MODE_PRIVATE
            //warning is displayed because I specified .edit()
            editor.putString(DetailsFragment.IMDB_KEY, imdbId);                                          // but haven't commited or applied  changes
            editor.putString(DetailsFragment.SEASON_KEY, seasonNo);
            editor.putString(DetailsFragment.EPISODE_KEY, episodeNo);
            editor.apply();
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

}
