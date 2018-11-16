package com.androidapp.mcs.tvseriesguide;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.mcs.tvseriesguide.database.AppDatabase;
import com.androidapp.mcs.tvseriesguide.model.Details;
import com.androidapp.mcs.tvseriesguide.model.Episode;
import com.androidapp.mcs.tvseriesguide.model.Rating;
import com.androidapp.mcs.tvseriesguide.model.Series;
import com.androidapp.mcs.tvseriesguide.service.WebService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public static final String IMDB_KEY = "imdb_key";
    public static final String SEASON_KEY = "season_key";
    public static final String EPISODE_KEY = "episode_key";
    private static final String TAG = "details_fragment";
    String id;
    String seasonNo;
    String episodeNo;
    Details details;

    TextView title_details;
    TextView episode_details;
    TextView released_dt;
    ImageView episode_image;
    TextView rated;
    TextView season_dt;
    TextView genre;
    TextView director;
    TextView writer;
    TextView actors;
    TextView plot;
    TextView language;
    TextView country;
    TextView awards;
    TextView imdbRating;
    TextView imdbVotes;
    TextView runtime;

    private AppDatabase db;  //Database

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        Bundle arguments = getArguments();

        if (arguments != null) {
            id = getArguments().getString(IMDB_KEY);
            seasonNo = getArguments().getString(SEASON_KEY);
            episodeNo = getArguments().getString(EPISODE_KEY);
        }


        title_details = view.findViewById(R.id.title_dt);
        episode_details = view.findViewById(R.id.episode_dt);
        released_dt = view.findViewById(R.id.released_dt);
        episode_image = view.findViewById(R.id.poster);
        rated = view.findViewById(R.id.rated);
        season_dt = view.findViewById(R.id.season_dt);
        genre = view.findViewById(R.id.genre);
        director = view.findViewById(R.id.director);
        writer = view.findViewById(R.id.writer);
        actors = view.findViewById(R.id.actors);
        plot = view.findViewById(R.id.plot);
        language = view.findViewById(R.id.language);
        country = view.findViewById(R.id.country);
        awards = view.findViewById(R.id.awards);
        imdbRating = view.findViewById(R.id.imdbRating);
        imdbVotes = view.findViewById(R.id.imdbVotes);
        runtime = view.findViewById(R.id.runtime);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebService webService = WebService.retrofit.create(WebService.class);
        Call<Details> call = webService.getDetails(id, seasonNo, episodeNo);

        call.enqueue(new Callback<Details>() {

            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                details = response.body();

                if (response != null) {

//                    insertDataIntoDatabase(details);

                    title_details.setText(details.getTitle());
                    episode_details.setText(details.getEpisode());
                    released_dt.setText(details.getReleased());
                    Picasso.get()
                            .load(details.getPoster())
                            .into(episode_image);
                    rated.setText(details.getRated());
                    season_dt.setText(details.getSeason());
                    genre.setText(details.getGenre());
                    director.setText(details.getDirector());
                    writer.setText(details.getWriter());
                    actors.setText(details.getActors());
                    plot.setText(details.getPlot());
                    language.setText(details.getLanguage());
                    country.setText(details.getCountry());
                    awards.setText(details.getAwards());
                    imdbRating.setText(details.getImdbRating());
                    imdbVotes.setText(details.getImdbVotes());
                    runtime.setText(details.getRuntime());
                }
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                Log.i("onFailure: ", t.getMessage());
            }
        });

    }

//    private void insertDataIntoDatabase(Details details) {
//
//        //Initialize Database
//        db = AppDatabase.getInstance(getContext());
//        int itemCount = db.seriesDao().countItems();
//        if (itemCount == 0) {
//            List<Rating> detailsRatings = details.getRatings();
//            db.ratingDao().insertAllRatings(detailsRatings);
//            Log.i(TAG, "Data Inserted!");
//        } else {
//            Log.i(TAG, "Data already exists ");
//        }
//    }

    public interface FragmentListener {
        void onFragmentFinish(String id, String seasonNo, String episodeNo);
    }


    @Override
    public void onDestroy() {

        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
