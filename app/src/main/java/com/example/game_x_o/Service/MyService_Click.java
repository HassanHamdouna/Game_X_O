package com.example.game_x_o.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.game_x_o.R;


public class MyService_Click extends Service {
    MediaPlayer mp;

    public MyService_Click() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.song_click_enter);
        // ليقاف المسيقى بعد ما تخلص
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSelf();
            }
        });


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mp.isPlaying())
            mp.start();
       // stopSelf();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}