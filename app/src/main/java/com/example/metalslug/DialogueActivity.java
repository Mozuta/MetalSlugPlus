package com.example.metalslug;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.FadeInTextView;

import java.util.ArrayList;
import java.util.List;

public class DialogueActivity extends AppCompatActivity {
    FadeInTextView fadeInTextView;

    //初始化字符list
    List<String> list = new ArrayList<>();
    int read_index = 0;
    //播放完毕
    boolean isEnd = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialogue);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initUI();

        //////
    }
    @SuppressLint("SetTextI18n")
    public void initUI(){
        list.add("hahahahahahahahaha第一个质数是2");
        list.add("hahahahahahahahaha第二个质数是3");
        list.add("hahahahahahahahaha第三个质数是5");
        list.add("hahahahahahahahaha第四个质数是7");
        list.add("hahahahahahahahaha第五个质数是11");
        fadeInTextView = findViewById(R.id.tv_dialogue);
        fadeInTextView.setTextString(list.get(read_index)).startFadeInAnimation().setTextAnimationListener(new FadeInTextView.TextAnimationListener() {
            @Override
            public void animationFinish() {
            }
        });
        fadeInTextView.setOnClickListener(v -> {
            fadeInTextView.stopFadeInAnimation();
            fadeInTextView.setText(fadeInTextView.getFullText());
            //isEnd = true;
            //判断结尾是->
            if (isEnd&&read_index<list.size()-1){
                read_index++;

                fadeInTextView
                        .setTextString(list.get(read_index))
                        .startFadeInAnimation()
                        .setTextAnimationListener(new FadeInTextView.TextAnimationListener() {
                            @Override
                            public void animationFinish() {
                            }
                        });
                isEnd = false;
            }
            else if (read_index == list.size()-1){
                Toast.makeText(DialogueActivity.this, "跳转到游戏界面", Toast.LENGTH_SHORT).show();
            }
            else {
                isEnd = true;
                Toast.makeText(DialogueActivity.this, fadeInTextView.getFullText().substring(fadeInTextView.getFullText().length()-1), Toast.LENGTH_SHORT).show();

            }

        });

    }
}