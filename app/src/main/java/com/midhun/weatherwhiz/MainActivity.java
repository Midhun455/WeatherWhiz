package com.midhun.weatherwhiz;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.midhun.weatherwhiz.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ArrayList<WeatherModel> weatherModelArrayList;
    private CustomAdapter customAdapter;
    private LocationManager locationManager;
    private int PERMISSION_ID = 1;
    private int PERMISSION_ALL = 2;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,};

    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        weatherModelArrayList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, weatherModelArrayList);
        binding.rvWeather.setAdapter(customAdapter);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        binding.loading.setVisibility(View.VISIBLE);
        binding.rvWeather.setVisibility(View.GONE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        cityName = getCityName(location.getLatitude(), location.getLongitude());
        getWeatherData(cityName);

        binding.idIVSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                String city = binding.idEdtCity.getText().toString();

                if (city.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter city name", Toast.LENGTH_SHORT).show();
                } else {
                    binding.cityName.setText(city);
                    getWeatherData(city);
                }
            }
        });

    }

    private String getCityName(double latitude, double longitude) {
        String cityName = "Not Found";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());

        try {
            List<Address> addressList = gcd.getFromLocation(latitude, longitude, 10);

            for (Address address : addressList) {
                if (address != null) {
                    String City = address.getLocality();
                    System.out.println("City Name:1" + City);
                    if (City != null && !City.isEmpty()) {
                        cityName = City;
                        System.out.println("City:::" + City);
                        System.out.println("Address:::" + address);
//                        break;
                    } else {
                        Log.d("TAG", "CITY NOT FOUND");
//                        Toast.makeText(this, "CITY NOT FOUND", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityName;
    }

    private void getWeatherData(String cityName) {
        System.out.println("WEATHER CITY" + cityName);
        String url = "http://api.weatherapi.com/v1/forecast.json?key=05dff21b64734f5384663609241405&q=" + cityName + "&days=1&aqi=no&alerts=no";
        System.out.println(url);
        binding.cityName.setText(cityName);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                binding.loading.setVisibility(View.GONE);
                binding.rvWeather.setVisibility(View.VISIBLE);
                weatherModelArrayList.clear();
                try {
                    float temperatureFloat = (float) response.getJSONObject("current").getDouble("temp_c");
                    int temperatureInt = Math.round(temperatureFloat);
                    binding.idTVTemperature.setText(temperatureInt + "°C");
                    int isDay = response.getJSONObject("current").getInt("is_day");
                    String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                    String humidity = response.getJSONObject("current").getString("humidity");
                    String ConditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
                    Picasso.get().load("http:".concat(ConditionIcon)).into(binding.idIVIcon);
                    binding.idTVCondition.setText(condition);
                    binding.idTVHumidity.setText("Humidity " + humidity + "%");
                    if (isDay == 1) {
                        Picasso.get().load("https://images.unsplash.com/photo-1566228015668-4c45dbc4e2f5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80").into(binding.backIV);
                    } else {
                        Picasso.get().load("https://images.unsplash.com/photo-1532074534361-bb09a38cf917?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80").into(binding.backIV);
                    }

                    JSONObject forecastObj = response.getJSONObject("forecast");
                    JSONObject forecastArray = forecastObj.getJSONArray("forecastday").getJSONObject(0);

                    JSONArray hourArray = forecastArray.getJSONArray("hour");
                    for (int i = 0; i < hourArray.length(); i++) {
                        JSONObject hourObj = hourArray.getJSONObject(i);
                        String time = hourObj.getString("time");
                        String temper = hourObj.getString("temp_c");
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        String windSpeed = hourObj.getString("wind_kph");
                        weatherModelArrayList.add(new WeatherModel(time, windSpeed, temper, img));
                    }

                    customAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    System.out.println("JSON ERROR");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("JSON ERROR 2" + volleyError.toString());

                Toast.makeText(MainActivity.this, "Enter a Valid City Name", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please Provide the Permissions", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}