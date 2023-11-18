package com.example.moviedb4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GenreAdapter extends ArrayAdapter<Genre> {

    public GenreAdapter(@NonNull Context context, List<Genre> genres) {
        super(context, 0, genres);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_genre, parent, false);
        }

        Genre currentGenre = getItem(position);

        // Find the TextView in the genre_list_item.xml layout
        TextView genreTextView = listItemView.findViewById(R.id.textViewGenreName);

        Log.d("VCustom genre name : ", currentGenre.getName());

        // Set the genre name on the TextView
        genreTextView.setText(currentGenre.getName());

        return listItemView;
    }
}