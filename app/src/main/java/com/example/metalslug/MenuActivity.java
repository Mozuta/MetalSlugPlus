package com.example.metalslug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fifteen.PuzzleActivity;

import java.io.IOException;

public class MenuActivity extends AppCompatActivity {
    private Button btn_play;
    private MediaPlayer player;

    //播放音乐



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_menu);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initUI();
    }
    public void initUI(){
        player = MediaPlayer.create(this, R.raw.blackbirds);
        player.setLooping(true);

        player.start();

        btn_play = findViewById(R.id.btn_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂停
                /*if (player!=null&&player.isPlaying()){
                    player.pause();
                    btn_play.setText("继续");
                }
                else {
                    player.start();
                    btn_play.setText("暂停");
                }*/
                //销毁播放器
                player.stop();
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                MenuActivity.this.startActivity(intent);
                MenuActivity.this.finish();
            }
        });
        //onClick(btn_play);
        }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}