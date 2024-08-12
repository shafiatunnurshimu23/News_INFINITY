// Generated by view binder compiler. Do not edit!
package com.example.projectnewsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.projectnewsapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddNewsToFireStoreBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button cancelBtn;

  @NonNull
  public final EditText category;

  @NonNull
  public final EditText details;

  @NonNull
  public final EditText image;

  @NonNull
  public final EditText source;

  @NonNull
  public final Button submitBtn;

  @NonNull
  public final EditText time;

  @NonNull
  public final EditText title;

  private ActivityAddNewsToFireStoreBinding(@NonNull LinearLayout rootView,
      @NonNull Button cancelBtn, @NonNull EditText category, @NonNull EditText details,
      @NonNull EditText image, @NonNull EditText source, @NonNull Button submitBtn,
      @NonNull EditText time, @NonNull EditText title) {
    this.rootView = rootView;
    this.cancelBtn = cancelBtn;
    this.category = category;
    this.details = details;
    this.image = image;
    this.source = source;
    this.submitBtn = submitBtn;
    this.time = time;
    this.title = title;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddNewsToFireStoreBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddNewsToFireStoreBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_news_to_fire_store, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddNewsToFireStoreBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cancel_btn;
      Button cancelBtn = ViewBindings.findChildViewById(rootView, id);
      if (cancelBtn == null) {
        break missingId;
      }

      id = R.id.category;
      EditText category = ViewBindings.findChildViewById(rootView, id);
      if (category == null) {
        break missingId;
      }

      id = R.id.details;
      EditText details = ViewBindings.findChildViewById(rootView, id);
      if (details == null) {
        break missingId;
      }

      id = R.id.image;
      EditText image = ViewBindings.findChildViewById(rootView, id);
      if (image == null) {
        break missingId;
      }

      id = R.id.source;
      EditText source = ViewBindings.findChildViewById(rootView, id);
      if (source == null) {
        break missingId;
      }

      id = R.id.submit_btn;
      Button submitBtn = ViewBindings.findChildViewById(rootView, id);
      if (submitBtn == null) {
        break missingId;
      }

      id = R.id.time;
      EditText time = ViewBindings.findChildViewById(rootView, id);
      if (time == null) {
        break missingId;
      }

      id = R.id.title;
      EditText title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new ActivityAddNewsToFireStoreBinding((LinearLayout) rootView, cancelBtn, category,
          details, image, source, submitBtn, time, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
