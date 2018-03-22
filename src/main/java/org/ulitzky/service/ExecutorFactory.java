package org.ulitzky.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by lulitzky on 22.03.18.
 */
public class ExecutorFactory {
    public final static int DEFAULT_MAX_NUMBER_OF_THREADS = 1000;

    private ExecutorFactory() {
    }

    public static ThreadPoolExecutor createExecutor(final Integer maxNumberOfThreads) {
        ThreadPoolExecutor executor;
        if (maxNumberOfThreads != null) {
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxNumberOfThreads);
        } else {
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(DEFAULT_MAX_NUMBER_OF_THREADS);
        }
        return executor;
    }
}
