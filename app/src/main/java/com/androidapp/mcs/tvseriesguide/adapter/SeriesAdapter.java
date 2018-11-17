package com.androidapp.mcs.tvseriesguide.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.mcs.tvseriesguide.R;
import com.androidapp.mcs.tvseriesguide.SeasonActivity;
import com.androidapp.mcs.tvseriesguide.model.Series;
import com.squareup.picasso.Picasso;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    public static final String IMDBID = "imdb key";
    public static final String TITLEID = "title key";
    public static final String FAVBUTTON = "fav_button";
    public Series series;
    public Context context;


    public SeriesAdapter(Series series, Context context) {
        this.series = series;
        this.context = context;
    }

    @NonNull
    @Override
    public SeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.main_list_item, viewGroup, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final SeriesAdapter.ViewHolder viewHolder, int i) {

        final Series series1 = series.getSearch().get(i);
        viewHolder.seriesTitle.setText(series1.getTitle());
        Picasso.get().load(series1.getPoster()).fit().placeholder(R.drawable.ic_launcher_background)
                .into((ImageView) viewHolder.seriesThumbnail.findViewById(R.id.series_thumbnail));

        viewHolder.seriesThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SeasonActivity.class);
                intent.putExtra(IMDBID, series1.getImdbID());
                intent.putExtra(TITLEID, series1.getTitle());
                Toast.makeText(context, series1.getTitle() + "\tselected!...\t", Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });


        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(viewHolder.overflow);
            }
        });
    }


    private void showPopupMenu(ImageView overflow) {
        PopupMenu popup = new PopupMenu(context, overflow); //Context and anchor-> to this button menu will be anchored
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu()); //Inflate the xml file and add all items to popup.getMenu()-> (This belongs to popup menu object)
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(FAVBUTTON, item.toString());
                editor.apply();


                return false;
            }
        });
        popup.show();
    }

    @Override
    public int getItemCount() {
        return series.getSearch().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView seriesThumbnail;
        TextView seriesTitle;
        ImageView overflow;
        // public View mView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            seriesThumbnail = itemView.findViewById(R.id.series_thumbnail);
            seriesTitle = itemView.findViewById(R.id.seriestitle);
            overflow = itemView.findViewById(R.id.overflow);

        }
    }


}


