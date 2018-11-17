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

import com.androidapp.mcs.tvseriesguide.DetailsActivity;
import com.androidapp.mcs.tvseriesguide.R;
import com.androidapp.mcs.tvseriesguide.model.EpisodeResponse;

import java.util.List;

import static com.androidapp.mcs.tvseriesguide.adapter.SeriesAdapter.FAVBUTTON;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    public static final String ITEM_KEY = "Item Key";
    public static final String SEASON_NUM = "season number";
    public static final String EPISODE_NUM = "episode number";
    public static final String SERIE_TITLE = "serie_title";

    private List<EpisodeResponse> episodeList;
    private Context context;
    private String seasonNo;
    private String imdbId;

    public EpisodeAdapter(List<EpisodeResponse> episodeList, String seasonNo, String imdbId, Context context) {
        this.episodeList = episodeList;
        this.context = context;
        this.seasonNo = seasonNo;
        this.imdbId = imdbId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.episode_list_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final EpisodeResponse mEpisode = episodeList.get(i);
        viewHolder.episode_no.setText(mEpisode.getEpisode());
        viewHolder.episode_released.setText(mEpisode.getReleased());
        viewHolder.imdbRating.setText(mEpisode.getImdbRating());

        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(viewHolder.overflow);
            }
        });

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(ITEM_KEY, imdbId);
                intent.putExtra(SEASON_NUM, seasonNo);
                intent.putExtra(EPISODE_NUM, mEpisode.getEpisode());
                context.startActivity(intent);
            }
        });

    }

    private void showPopupMenu(ImageView overflow) {
        PopupMenu popup = new PopupMenu(context, overflow);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
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

        return episodeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView episode_no;
        TextView episode_released;
        TextView imdbRating;
        ImageView overflow;
        public View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            overflow = itemView.findViewById(R.id.overflow);
            episode_no = itemView.findViewById(R.id.episode_no);
            episode_released = itemView.findViewById(R.id.episode_released);
            imdbRating = itemView.findViewById(R.id.imdbRating);
            mView = itemView;
        }
    }


}
