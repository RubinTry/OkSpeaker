package cn.rubintry.speaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.rubintry.speak.OkSpeaker;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private EditText edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.btnPlay);
        edtContent = findViewById(R.id.edtContent);
        btnPlay.setOnClickListener((v) -> {
            OkSpeaker.get().speak(edtContent.getText().toString());
        });
    }

//    public void speak(View view) {
//        Speaker.get().speak("为防止误触，系统返回键本页面暂时禁用，请在右上角设置按钮中操作");
//    }

    @Override
    protected void onDestroy() {
        OkSpeaker.get().release();
        super.onDestroy();
    }
}
