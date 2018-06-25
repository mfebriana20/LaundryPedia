package com.laundrypedia.laundrypedia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.model.Film;

import org.w3c.dom.Text;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private List<Film> filmList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView judul, genre, tahun;

        public MyViewHolder(View itemView) {
            super(itemView);

            judul = (TextView) itemView.findViewById(R.id.txtJudul);
            genre = (TextView) itemView.findViewById(R.id.txtGenre);
            tahun = (TextView) itemView.findViewById(R.id.txtTahun);
        }
    }

    public FilmAdapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public FilmAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmAdapter.MyViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.judul.setText(film.getJudul());
        holder.genre.setText(film.getGenre());
        holder.tahun.setText(film.getTahun());
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }


}
