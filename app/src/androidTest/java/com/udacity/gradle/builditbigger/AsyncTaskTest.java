package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Dobrunov on 02.12.2015.
 */
public class AsyncTaskTest extends AndroidTestCase { //extends ActivityUnitTestCase<MainActivity>
    private static final String LOG_TAG = AsyncTaskTest.class.getSimpleName();

    public void testAsync() throws InterruptedException {
        Log.d(LOG_TAG, "testAsync");
        final CountDownLatch signal = new CountDownLatch(1);

        new EndpointsAsyncTask(new ICompleteListener() {
            @Override
            public void onComplete(String result) {
                Log.d(LOG_TAG, "Test=" + result);
                assertNotNull(result);
                signal.countDown();
            }
        }).execute();

        signal.await();
    }
}
