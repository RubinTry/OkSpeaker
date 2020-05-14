package cn.rubintry.speak;

import java.io.IOException;

import cn.rubintry.speak.utils.TextToSpeechUtil;

/**
 * @author logcat
 * A engine of TTS
 */
public class SpeakEngine implements ISpeakEngine {
    private String content;
    private String TAG = this.getClass().getSimpleName();


    /**
     * create a {@link #SpeakEngine(String)} object
     * @param content  The content words which need to speak.
     */
    public SpeakEngine(String content) {
        this.content = content;
    }

    /**
     * start speak
     */
    @Override
    public void execute() throws IOException {

        if (content == null) {
            return;
        }

        TextToSpeechUtil.getInstance().speak(content);

    }


    /**
     * release the resource
     */
    @Override
    public void release() {
        TextToSpeechUtil.getInstance().release();
    }


}
