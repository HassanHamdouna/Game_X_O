package com.example.game_x_o.AllScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.game_x_o.R;
import com.example.game_x_o.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.light_blue_1));

        animationView =findViewById(R.id.Lottie_Animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.imageViewBack.animate().translationY(-2400).setDuration(1000).setDuration(2000);
                binding.imageViewX.animate().translationX(-1400).setDuration(1000).setDuration(2000);
                binding.imageViewO.animate().translationX(1400).setDuration(1000).setDuration(2000);
                animationView.animate().translationY(1400).setDuration(1000).setDuration(2000);

            }
        },4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
              //  Intent intentService = new Intent(getApplicationContext(),MyService.class);
            //    startService(intentService);
            }
        },5500);




    }
}