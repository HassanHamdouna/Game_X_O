package com.example.game_x_o.AllScreen;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.game_x_o.Fragment.BlankFragment;
import com.example.game_x_o.R;
import com.example.game_x_o.Service.MyService;
import com.example.game_x_o.Service.MyService_Click;
import com.example.game_x_o.databinding.ActivityStartGameBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class StartGame extends AppCompatActivity {
    static ActivityStartGameBinding binding;
    private final List<int[]> combinationsList = new ArrayList<>();
    private static int [] boxPositions ={0,0,0,0,0,0,0,0,0};
    ImageView imageView;
    int numper1;
    int numper2;
    int rest_X_O  ;

    private InterstitialAd mInterstitialAd;


    MediaPlayer My_Song_Click,My_Song_Dialog ,My_Song_Dialog_Level;

    private static int playerTurn = 1;
    private static int totalSelectedBoxes =1 ;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityStartGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.light_blue_1));


        My_Song_Click =MediaPlayer.create(getBaseContext(), R.raw.song_click_enter);
        My_Song_Dialog =MediaPlayer.create(getBaseContext(),R.raw.song_show_dialog);
        My_Song_Dialog_Level =MediaPlayer.create(getBaseContext(),R.raw.song_show_dialog_level);
      //  My_song_activity =MediaPlayer.create(getBaseContext(),R.raw.activity_krampus_workshop);
        Intent intent = getIntent();
        binding.tvPlayerOne.setText(intent.getStringExtra("name_one"));
        binding.tvPlayerTow.setText(intent.getStringExtra("name_tow"));

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});
        binding.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(0)){
                    performAction((ImageView) view,0);
                    /*

                    if(My_Song_Click.isPlaying()){
                        My_Song_Click.stop();
                        My_Song_Click.release();
                    }
                    My_Song_Click.start();

                     */

                }

            }
        });
        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(1)){
                    performAction((ImageView) view,1);

                }

            }
        });
        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(2)){
                    performAction((ImageView) view,2);

                }

            }
        });

        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(3)){
                    performAction((ImageView) view,3);



                }

            }
        });
        binding.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(4)){
                    performAction((ImageView) view,4);


                }

            }
        });

        binding.imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(5)){
                    performAction((ImageView) view,5);
                    /*
                    if(My_Song_Click.isPlaying()){
                        My_Song_Click.stop();
                        My_Song_Click.release();
                    }
                    My_Song_Click.start();

                     */
                }

            }
        });

        binding.imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)){
                    performAction((ImageView) view,6);

                }
            }
        });

        binding.imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(7)){
                    performAction((ImageView) view,7);


                }


            }
        });

        binding.imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (isBoxSelectable(8)){
                   performAction((ImageView) view,8);



               }

            }
        });



    }

    private void performAction(ImageView imageView ,int selectedBoxesPosition){
        //؟
        boxPositions[selectedBoxesPosition] = playerTurn;

        if (playerTurn == 1){
            imageView.setImageResource(R.drawable.group_x);

            if(checkPlayerWin()){
                numper1++;

                binding.numberPlayerOne.setText(""+numper1);

                BlankFragment
                        dialog = BlankFragment.newInstance(binding.tvPlayerOne.getText().toString()+"  has win the math");
                dialog.show(getSupportFragmentManager(), null);

                Intent intentStopService = new Intent(getApplicationContext(), MyService.class);
                stopService(intentStopService);
                My_Song_Dialog_Level.start();

            }
            // ازا مليان
            else if (totalSelectedBoxes == 9){

                rest_X_O++;
                setAds();

                if(rest_X_O%2 ==0 ){
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(this);
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdImpression() {
                                super.onAdImpression();

                            }
                        });
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");

                    }
                }
                BlankFragment
                        dialog = BlankFragment.newInstance(" It is a draw !");
                dialog.show(getSupportFragmentManager(), null);
                restartMatch();
                Intent intentStopService = new Intent(getApplicationContext(),MyService.class);
                stopService(intentStopService);
                My_Song_Dialog.start();

            }
            else {
                changePlayerTurn(2);
                totalSelectedBoxes++;

            }
        }
        else {
            imageView.setImageResource(R.drawable.group_o);

                if(checkPlayerWin()){
                    numper2++;

                    BlankFragment
                            dialog = BlankFragment.newInstance(binding.tvPlayerTow.getText().toString()+"  has win the math");
                    dialog.show(getSupportFragmentManager(), null);
                    binding.numberPlayerTow.setText(""+numper2);
                    Intent intentStopService = new Intent(getApplicationContext(),MyService.class);
                    stopService(intentStopService);
                    My_Song_Dialog_Level.start();
                    binding.linearLX.setBackgroundResource(R.drawable.user_player);
                    binding.linearLO.setBackgroundResource(R.drawable.round_back_dark_blue);


                }
                else if (totalSelectedBoxes == 9){
                    setAds();
                    if(rest_X_O % 2==0){
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(this);
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdImpression() {
                                    super.onAdImpression();

                                }
                            });
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");

                        }                    }
                    BlankFragment
                            dialog = BlankFragment.newInstance(" It is a draw !");
                    dialog.show(getSupportFragmentManager(), null);
                    restartMatch();
                    Intent intentStopService = new Intent(getApplicationContext(),MyService.class);
                    stopService(intentStopService);
                    My_Song_Dialog.start();



                }
                else {
                    changePlayerTurn(1);
                    totalSelectedBoxes++;

                }


        }


    }
    private void changePlayerTurn(int currentPlayerTurn){
          playerTurn = currentPlayerTurn;
          if (playerTurn == 1){
              binding.linearLX.setBackgroundResource(R.drawable.user_player);
              binding.linearLO.setBackgroundResource(R.drawable.round_back_dark_blue);

          }else {
              binding.linearLX.setBackgroundResource(R.drawable.round_back_dark_blue);
              binding.linearLO.setBackgroundResource(R.drawable.user_player);

          }
    }
    //؟؟
    private boolean checkPlayerWin(){
        boolean response =false;
        for (int i =0;i<combinationsList.size();i++){
            final int [] combination = combinationsList.get(i);
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn
                    && boxPositions[combination[2]] == playerTurn  ){
              response =true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition){
        boolean response =false;
        if(boxPositions[boxPosition] == 0){
            response =true;

        }
        return response;
    }

    public static void  restartMatch(){
        boxPositions =new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes =1 ;
        binding.imageView1.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView2.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView3.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView4.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView5.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView6.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView7.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView8.setImageResource(R.drawable.round_back_dark_blue);
        binding.imageView9.setImageResource(R.drawable.round_back_dark_blue);

    }

    private void stopPlayer_My_Song_Click() {
        if (My_Song_Click != null && My_Song_Click.isPlaying()) {
            My_Song_Click.stop();
            My_Song_Click.release();
            My_Song_Click = null;
        }
    }
    public void pause(View v) {
        if (My_Song_Click != null) {
            My_Song_Click.pause();
        }
    }

    private void startPlayer_My_Song_Click() {
        Intent intentService = new Intent(getApplicationContext(), MyService_Click.class);
        startService(intentService);
    /*    if (My_Song_Click == null && !My_Song_Click.isPlaying()) {
            My_Song_Click =MediaPlayer.create(getBaseContext(),R.raw.song_click_enter);
            My_Song_Click.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer_My_Song_Click();
                }
            });

        }
        My_Song_Click.start();

     */
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer_My_Song_Click();
    }

    public void setAds() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
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