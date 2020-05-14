package cn.rubintry.speak;

import android.app.Application;

import java.util.Locale;

/**
 * @author logcat
 */
public class SpeakerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SpeakerConfig config = new SpeakerConfig();
        config.setLanguage(Locale.CHINESE);
        config.setPitch(1.0f);
        config.setSpeechRate(1.0f);
        OkSpeaker.get().init(this , config , 5000);
    }
}
