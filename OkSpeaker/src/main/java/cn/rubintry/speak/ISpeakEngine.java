package cn.rubintry.speak;

import java.io.IOException;

/**
 * @author logcat
 * A interface for Speak
 */
public interface ISpeakEngine {


    /**
     *  start speak
     * @throws IOException  the data exception
     */
    void execute() throws IOException;


    /**
     * release resource
     */
    void release();
}
