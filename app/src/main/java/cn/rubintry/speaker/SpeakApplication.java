package cn.rubintry.speaker;

import android.app.Application;

import java.util.Locale;

import cn.rubintry.speak.OkSpeaker;
import cn.rubintry.speak.SpeakerConfig;

public class SpeakApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SpeakerConfig config = new SpeakerConfig();
        config.setLanguage(Locale.CHINESE);
        config.setPitch(1.0f);
        config.setSpeechRate(1.0f);
        OkSpeaker.get().init(this , config);
    }
}
