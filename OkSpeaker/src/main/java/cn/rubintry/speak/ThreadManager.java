package cn.rubintry.speak;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author logcat
 */
public class ThreadManager {


    private Integer delayTime;

    private static volatile ThreadManager instance;

    /**
     * the core count
     */
    private int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * Core thread size
     */
    private int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));


    private int maxImumPoolSize = CPU_COUNT * 2 + 1;

    /**
     * create a threadPoolExecutor to manage queue.
     */
    private ThreadPoolExecutor threadPoolExecutor;

    private LinkedBlockingDeque<Runnable> speakQueue = new LinkedBlockingDeque<Runnable>();



    private ThreadManager() {

        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxImumPoolSize, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        return thread;
                    }
                }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });

        threadPoolExecutor.execute(speakRunnable);
    }


    /**
     * get a ThreadManager instance
     * @return
     */
    public static ThreadManager getInstance() {
        if (instance == null) {
            synchronized (ThreadManager.class) {
                if (instance == null) {
                    instance = new ThreadManager();
                }
            }
        }
        return instance;
    }


    /**
     * add a task into queue
     * @param runnable
     */
    public void addTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        try {
            speakQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Runnable speakRunnable = () -> {
        while (true) {
            try {
                Runnable runnable = speakQueue.take();
                threadPoolExecutor.execute(runnable);
                if(delayTime != null){
                    Thread.sleep(delayTime.longValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };


    /**
     * release all resource
     */
    public void release() {
        for (Runnable runnable : speakQueue) {
            if (runnable instanceof SpeakThread) {
                ((SpeakThread) runnable).release();
            }
        }
        speakQueue.clear();
    }

}