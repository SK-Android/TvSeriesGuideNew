package com.androidapp.mcs.tvseriesguide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidapp.mcs.tvseriesguide.R;
import com.androidapp.mcs.tvseriesguide.model.Rating;
import com.androidapp.mcs.tvseriesguide.model.Seasons;

import java.util.List;

public class SeasonAdapter extends ArrayAdapter<Seasons> {
    public List<Seasons> mSeasons;
    LayoutInflater mInflater;

    public SeasonAdapter(Context context, List<Seasons> objects) {
        super(context, R.layout.season_list_item, objects);
        mSeasons = objects;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.season_list_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.seasonNo);
        Seasons seasons = mSeasons.get(position);
        textView.setText(seasons.getItemId());
        return convertView;
    }
}
