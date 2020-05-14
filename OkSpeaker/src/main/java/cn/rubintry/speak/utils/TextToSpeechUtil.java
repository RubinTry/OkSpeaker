package cn.rubintry.speak.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.util.Log;

import java.util.Locale;

import cn.rubintry.speak.SpeakerConfig;

/**
 * @author logcat
 */
public class TextToSpeechUtil {
    private static volatile TextToSpeechUtil instance;

    private TextToSpeech textToSpeech;
    private SpeakerConfig config;
    private String TAG = this.getClass().getSimpleName();




    public static TextToSpeechUtil getInstance() {
        if (instance == null) {
            synchronized (TextToSpeechUtil.class) {
                if (instance == null) {
                    instance = new TextToSpeechUtil();
                }
            }
        }
        return instance;
    }



    /**
     * Initialize this util
     * @param context
     */
    public void init(Context context) {
        init(context, null);
    }



    /**
     * Initialize this util
     * @param context
     * @param config
     */
    public void init(Context context, final SpeakerConfig config) {
        this.config = config;
        if (textToSpeech == null) {
            if(config != null && config.getEngine() != null){
                textToSpeech = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        initTTS(status);
                    }
                }  , config.getEngine());
            }else{
                textToSpeech = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        initTTS(status);
                    }
                } );
            }

        }

    }

    /**
     * Initialize the TTS
     * @param status
     */
    private void initTTS(int status) {
        if (status == TextToSpeech.SUCCESS) {
            if (config != null) {
                textToSpeech.setLanguage(config.getLanguage());
                textToSpeech.setPitch(config.getPitch());
                textToSpeech.setSpeechRate(config.getSpeechRate());
                if (config.getVoice() != null) {
                    textToSpeech.setVoice(config.getVoice());
                }

            } else {
                textToSpeech.setLanguage(Locale.getDefault());
                textToSpeech.setPitch(1.0f);
                textToSpeech.setSpeechRate(1.0f);
            }
        }
    }



    /**
     *  start play
     * @param content
     */
    public void speak(String content) {
        if(isLanguageNotAvailable()){
            throw new IllegalArgumentException("Language is not support!!!");
        }
        int ret = textToSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null, "cn.rubintry.speak");
        Log.d(TAG, "ret: " + ret);
        if(ret == TextToSpeech.ERROR){
            throw new IllegalStateException("TTS be error!!!");
        }
    }



    /**
     *  Is the language available?
     * @return  true: available false:unavailable
     */
    public boolean isLanguageAvailable() {
        if (textToSpeech == null) {
            return false;
        }
        if (config != null && textToSpeech.isLanguageAvailable(config.getLanguage()) == TextToSpeech.LANG_AVAILABLE) {
            return true;
        }
        return false;
    }



    /**
     *  Is the language unavailable?
     * @return  true: unavailable false:available
     */
    public boolean isLanguageNotAvailable() {
        return !isLanguageAvailable();
    }


    /**
     * release these resource
     */
    public void release() {
        textToSpeech.stop();
        textToSpeech.shutdown();
        config = null;
    }
}
