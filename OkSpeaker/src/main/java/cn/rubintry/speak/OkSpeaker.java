package cn.rubintry.speak;

import android.content.Context;

import cn.rubintry.speak.utils.TextToSpeechUtil;
import cn.rubintry.speak.utils.Utils;

/**
 * @author logcat
 * A library for speak
 */
public class OkSpeaker {

    private static volatile OkSpeaker instance;

    public static OkSpeaker get() {
        if (instance == null) {
            synchronized (OkSpeaker.class) {
                if (instance == null) {
                    instance = new OkSpeaker();
                }
            }
        }
        return instance;
    }


    /**
     * Initialize the library.
     * @param context
     */
    public void init(Context context) {
        init(context, null);
    }


    /**
     * Initialize the library.
     * @param context
     * @param config the config of TTS.
     */
    public void init(Context context, SpeakerConfig config) {
        init(context, config, null);
    }


    /**
     * Initialize the library.
     * @param context
     * @param config the config of TTS.
     * @param delayTime  the delayTime of a few words.
     */
    public void init(Context context, SpeakerConfig config, Integer delayTime) {
        Utils.getInstance().init(context);
        TextToSpeechUtil.getInstance().init(context, config);
        ThreadManager.getInstance().setDelayTime(delayTime);
    }


    /**
     * Start a speak command
     * @param content The content which need to speak
     */
    public void speak(String content) {
        ISpeakEngine speakRequest = new SpeakEngine(content);
        ThreadManager.getInstance().addTask(new SpeakThread(speakRequest));
    }


    /**
     * Release the resource
     */
    public void release() {
        ThreadManager.getInstance().release();
    }


}
