package cn.rubintry.speak;

import android.speech.tts.Voice;

import java.util.Locale;

/**
 * @author logcat
 * The config for Speaker.
 */
public class SpeakerConfig {

    private Locale language;
    private float pitch;
    private float speechRate;
    private Voice voice;
    private String engine;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Voice getVoice() {
        return voice;
    }


    /**
     * Sets the text-to-speech voice.
     * @param voice
     */
    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }


    /**
     * Gets the speech pitch for the TextToSpeech engine.
     * @return
     */
    public float getPitch() {
        return pitch;
    }


    /**
     * Sets the speech pitch for the TextToSpeech engine.
     * @param pitch
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getSpeechRate() {
        return speechRate;
    }

    public void setSpeechRate(float speechRate) {
        this.speechRate = speechRate;
    }
}
