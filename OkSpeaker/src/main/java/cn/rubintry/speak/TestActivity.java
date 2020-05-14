package cn.rubintry.speak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private TextView tvSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSpeak = findViewById(R.id.tvSpeak);
        tvSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 0; i < 10; i++) {
                    OkSpeaker.get().speak("错误：失败：非当前车次，not current bus");
//                }
            }
        });

    }
}
