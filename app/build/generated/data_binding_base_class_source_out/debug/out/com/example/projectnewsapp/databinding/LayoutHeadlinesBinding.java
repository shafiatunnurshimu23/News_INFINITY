// Generated by view binder compiler. Do not edit!
package com.example.projectnewsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.projectnewsapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutHeadlinesBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView detailsImage;

  @NonNull
  public final TextView detailsPublishTime;

  @NonNull
  public final TextView detailsPublishedBy;

  @NonNull
  public final TextView hotnewsHeadline;

  private LayoutHeadlinesBinding(@NonNull CardView rootView, @NonNull ImageView detailsImage,
      @NonNull TextView detailsPublishTime, @NonNull TextView detailsPublishedBy,
      @NonNull TextView hotnewsHeadline) {
    this.rootView = rootView;
    this.detailsImage = detailsImage;
    this.detailsPublishTime = detailsPublishTime;
    this.detailsPublishedBy = detailsPublishedBy;
    this.hotnewsHeadline = hotnewsHeadline;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutHeadlinesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutHeadlinesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_headlines, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutHeadlinesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.detailsImage;
      ImageView detailsImage = ViewBindings.findChildViewById(rootView, id);
      if (detailsImage == null) {
        break missingId;
      }

      id = R.id.detailsPublishTime;
      TextView detailsPublishTime = ViewBindings.findChildViewById(rootView, id);
      if (detailsPublishTime == null) {
        break missingId;
      }

      id = R.id.detailsPublishedBy;
      TextView detailsPublishedBy = ViewBindings.findChildViewById(rootView, id);
      if (detailsPublishedBy == null) {
        break missingId;
      }

      id = R.id.hotnewsHeadline;
      TextView hotnewsHeadline = ViewBindings.findChildViewById(rootView, id);
      if (hotnewsHeadline == null) {
        break missingId;
      }

      return new LayoutHeadlinesBinding((CardView) rootView, detailsImage, detailsPublishTime,
          detailsPublishedBy, hotnewsHeadline);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
