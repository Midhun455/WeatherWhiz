package com.midhun.weatherwhiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midhun.weatherwhiz.databinding.WeatherRvBinding;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherModel> weatherModelArrayList;
    private WeatherRvBinding rvBinding;

    public CustomAdapter(Context context, ArrayList<WeatherModel> weatherModelArrayList) {
        this.context = context;
        this.weatherModelArrayList = weatherModelArrayList;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rvBinding = WeatherRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(rvBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        WeatherModel model = weatherModelArrayList.get(position);
        rvBinding.idTVTemp.setText(model.getTemperature() + "°C");
        rvBinding.TVWindSpeed.setText(model.getWindSpeed() + "Km/h");

        Picasso.get().load("http:".concat(model.getIcon())).into(rvBinding.IVCondition);

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");

        try {
            Date t = input.parse(model.getTime());
            rvBinding.idTVTime.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
