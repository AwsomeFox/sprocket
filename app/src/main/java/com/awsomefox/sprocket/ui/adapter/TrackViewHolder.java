/*
 * Copyright (C) 2016 Simon Norberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.awsomefox.sprocket.ui.adapter;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.awsomefox.sprocket.R;
import com.awsomefox.sprocket.data.model.Track;

import butterknife.BindString;
import butterknife.BindView;

final class TrackViewHolder extends ClickableViewHolder<Track> {

  @BindView(R.id.track_title) TextView title;
  @BindView(R.id.track_subtitle) TextView subtitle;
  @BindView(R.id.track_duration) TextView duration;
    @BindView(R.id.partial_listen_track)
    ImageView partial;
    @BindView(R.id.full_listen_track)
    ImageView full;
    @BindView(R.id.no_listen_track)
    ImageView none;
    @BindString(R.string.chapter_title)
    String chapter_title;

  TrackViewHolder(View view, ViewHolderListener listener) {
    super(view, listener);
  }

  @Override void bindModel(@NonNull Track track) {
      title.setText(String.format(chapter_title, track.index()));
      subtitle.setText(track.albumTitle());
      if (track.viewOffset() != 0) {
          partial.setVisibility(View.VISIBLE);
          full.setVisibility(View.GONE);
          none.setVisibility(View.GONE);
          String time = DateUtils.formatElapsedTime(track.viewOffset() / 1000)
                  + "/"
                  + DateUtils.formatElapsedTime(track.duration() / 1000);
          duration.setText(time);
      } else if (track.viewCount() != 0) {
          partial.setVisibility(View.GONE);
          full.setVisibility(View.VISIBLE);
          none.setVisibility(View.GONE);
          duration.setText(DateUtils.formatElapsedTime(track.duration() / 1000));
      } else {
          partial.setVisibility(View.GONE);
          full.setVisibility(View.GONE);
          none.setVisibility(View.VISIBLE);
          duration.setText(DateUtils.formatElapsedTime(track.duration() / 1000));
      }
  }
}
