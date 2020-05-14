package cn.rubintry.speak;

/**
 * @author logcat
 * A thread for speak task.
 */
public class SpeakThread implements Runnable  {


    private ISpeakEngine iSpeakEngine;



    public SpeakThread(ISpeakEngine iSpeakEngine) {
        this.iSpeakEngine = iSpeakEngine;
    }

    @Override
    public void run() {
        try {
            iSpeakEngine.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * release resource
     */
    public void release(){
        iSpeakEngine.release();
    }


}
