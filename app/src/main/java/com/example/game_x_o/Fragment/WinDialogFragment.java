package com.example.game_x_o.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.game_x_o.AllScreen.StartGame;
import com.example.game_x_o.R;


public class WinDialogFragment extends Dialog {


    private  final String massage ;
    private  final StartGame startGame;



    public WinDialogFragment(@NonNull Context context, String massage, StartGame startGame) {
        super(context);
        this.massage = massage;
        this.startGame = startGame;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_win_dialog);

        final TextView mas =findViewById(R.id.massage);
        final Button bt_star =findViewById(R.id.bt_star);

        mas.setText(massage);
        bt_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame.restartMatch();
                dismiss();
            }
        });


    }
}