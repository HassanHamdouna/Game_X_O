package com.hassan2.game_x_o.AllScreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.hassan2.game_x_o.R;
import com.hassan2.game_x_o.databinding.ActivityMainBinding;


import io.github.muddz.styleabletoast.StyleableToast;
// test
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    AdRequest adRequest;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();

        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.light_blue_1));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-5139982073945832/1122236714");


        AdRequest  adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);



        InterstitialAd.load(getApplicationContext(), "ca-app-pub-5139982073945832/1122236714", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;

                    }
                });





        binding.btStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  getPlayerOneName =binding.etNameOne.getText().toString();
                String  getPlayerTwoName = binding.etNameTow.getText().toString();

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()){
                    new StyleableToast
                            .Builder(getApplicationContext())
                            .text(" Please enter player names ")
                            .textColor(Color.WHITE)
                            .textSize(16)
                            .backgroundColor(Color.RED)
                            .show();

                }else {
                    if (getPlayerOneName.equals(getPlayerTwoName)){
                        new StyleableToast
                                .Builder(getApplicationContext())
                                .text(" The names must not be the same ")
                                .textColor(Color.WHITE)
                                .textSize(16)
                                .backgroundColor(Color.RED)
                                .show();
                    }else {
                        Intent intent = new Intent(MainActivity.this, StartGame.class);
                        intent.putExtra("name_one",getPlayerOneName);
                        intent.putExtra("name_tow",getPlayerTwoName);
                        startActivity(intent);
                        binding.etNameOne.setText("");
                        binding.etNameTow.setText("");
                    }



                }
            }
        });



    }
    public void  showToast(View v) {
        StyleableToast.makeText(MainActivity.this, "Please enter player names", R.style.exampleToast).show();

    }

    public void setAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-5139982073945832/1122236714", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });



    }

}