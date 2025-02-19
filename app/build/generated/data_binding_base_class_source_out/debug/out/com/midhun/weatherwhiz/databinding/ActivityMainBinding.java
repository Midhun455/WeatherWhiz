// Generated by view binder compiler. Do not edit!
package com.midhun.weatherwhiz.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.midhun.weatherwhiz.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout LLEdt;

  @NonNull
  public final TextInputLayout TILCity;

  @NonNull
  public final ImageView backIV;

  @NonNull
  public final TextView cityName;

  @NonNull
  public final TextInputEditText idEdtCity;

  @NonNull
  public final ImageView idIVIcon;

  @NonNull
  public final ImageView idIVSearch;

  @NonNull
  public final TextView idTVCondition;

  @NonNull
  public final TextView idTVHumidity;

  @NonNull
  public final TextView idTVTemperature;

  @NonNull
  public final ProgressBar loading;

  @NonNull
  public final RelativeLayout main;

  @NonNull
  public final RelativeLayout rLayout;

  @NonNull
  public final RecyclerView rvWeather;

  private ActivityMainBinding(@NonNull RelativeLayout rootView, @NonNull LinearLayout LLEdt,
      @NonNull TextInputLayout TILCity, @NonNull ImageView backIV, @NonNull TextView cityName,
      @NonNull TextInputEditText idEdtCity, @NonNull ImageView idIVIcon,
      @NonNull ImageView idIVSearch, @NonNull TextView idTVCondition,
      @NonNull TextView idTVHumidity, @NonNull TextView idTVTemperature,
      @NonNull ProgressBar loading, @NonNull RelativeLayout main, @NonNull RelativeLayout rLayout,
      @NonNull RecyclerView rvWeather) {
    this.rootView = rootView;
    this.LLEdt = LLEdt;
    this.TILCity = TILCity;
    this.backIV = backIV;
    this.cityName = cityName;
    this.idEdtCity = idEdtCity;
    this.idIVIcon = idIVIcon;
    this.idIVSearch = idIVSearch;
    this.idTVCondition = idTVCondition;
    this.idTVHumidity = idTVHumidity;
    this.idTVTemperature = idTVTemperature;
    this.loading = loading;
    this.main = main;
    this.rLayout = rLayout;
    this.rvWeather = rvWeather;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LLEdt;
      LinearLayout LLEdt = ViewBindings.findChildViewById(rootView, id);
      if (LLEdt == null) {
        break missingId;
      }

      id = R.id.TILCity;
      TextInputLayout TILCity = ViewBindings.findChildViewById(rootView, id);
      if (TILCity == null) {
        break missingId;
      }

      id = R.id.backIV;
      ImageView backIV = ViewBindings.findChildViewById(rootView, id);
      if (backIV == null) {
        break missingId;
      }

      id = R.id.cityName;
      TextView cityName = ViewBindings.findChildViewById(rootView, id);
      if (cityName == null) {
        break missingId;
      }

      id = R.id.idEdtCity;
      TextInputEditText idEdtCity = ViewBindings.findChildViewById(rootView, id);
      if (idEdtCity == null) {
        break missingId;
      }

      id = R.id.idIVIcon;
      ImageView idIVIcon = ViewBindings.findChildViewById(rootView, id);
      if (idIVIcon == null) {
        break missingId;
      }

      id = R.id.idIVSearch;
      ImageView idIVSearch = ViewBindings.findChildViewById(rootView, id);
      if (idIVSearch == null) {
        break missingId;
      }

      id = R.id.idTVCondition;
      TextView idTVCondition = ViewBindings.findChildViewById(rootView, id);
      if (idTVCondition == null) {
        break missingId;
      }

      id = R.id.idTVHumidity;
      TextView idTVHumidity = ViewBindings.findChildViewById(rootView, id);
      if (idTVHumidity == null) {
        break missingId;
      }

      id = R.id.idTVTemperature;
      TextView idTVTemperature = ViewBindings.findChildViewById(rootView, id);
      if (idTVTemperature == null) {
        break missingId;
      }

      id = R.id.loading;
      ProgressBar loading = ViewBindings.findChildViewById(rootView, id);
      if (loading == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      id = R.id.rLayout;
      RelativeLayout rLayout = ViewBindings.findChildViewById(rootView, id);
      if (rLayout == null) {
        break missingId;
      }

      id = R.id.rvWeather;
      RecyclerView rvWeather = ViewBindings.findChildViewById(rootView, id);
      if (rvWeather == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, LLEdt, TILCity, backIV, cityName,
          idEdtCity, idIVIcon, idIVSearch, idTVCondition, idTVHumidity, idTVTemperature, loading,
          main, rLayout, rvWeather);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
