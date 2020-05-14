package cn.rubintry.speak.utils;

import android.content.Context;

/**
 * @author logcat
 */
public class Utils {
    private static volatile Utils instance;
    private Context context;

    public static Utils getInstance(){
        if(instance == null){
            synchronized (Utils.class){
                if(instance == null){
                    instance = new Utils();
                }
            }
        }
        return instance;
    }


    public void init(Context context){
        this.context = context.getApplicationContext();
    }

    public Context getContext(){
        if(context == null){
            throw new IllegalArgumentException("Util is not init!!!");
        }
        return context;
    }
}
