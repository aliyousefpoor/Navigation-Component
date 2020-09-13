package com.example.bottomnavigation;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaBrowserCompat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class ExoPlayerActivity extends AppCompatActivity {
    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    private String uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        uri = bundle.getString("fileUri");
        setContentView(R.layout.exoplayer_activity);
        playerView = findViewById(R.id.videoPlayer);
        initializePlayer();
    }

    private void initializePlayer() {

        Uri videoUri = Uri.parse(uri);
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        LoadControl loadControl = new DefaultLoadControl();
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this
                , Util.getUserAgent(this, "Exo2"), defaultBandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");
        HlsMediaSource hlsMediaSource = new HlsMediaSource(videoUri,dataSourceFactory,1,null,null);
        playerView.setPlayer(simpleExoPlayer);
        playerView.requestFocus();
        simpleExoPlayer.prepare(hlsMediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        playerView.setKeepScreenOn(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}
