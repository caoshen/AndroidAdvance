package io.github.caoshen.androidadvance.autostart;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.io.File;
import java.io.IOException;

class AutoStartProcessImpl implements IAutoStartProcess {

    private static final String TAG = "AutoStartProcessImpl";
    private static final String INDICATOR_DIR_NAME = "indicators";
    private static final String INDICATOR_PERSISTENT_FILENAME = "indicator_p";
    private static final String INDICATOR_DAEMON_ASSISTANT_FILENAME = "indicator_d";
    private static final String OBSERVER_PERSISTENT_FILENAME = "observer_p";
    private static final String OBSERVER_DAEMON_ASSISTANT_FILENAME = "observer_d";

    private String mIndicatorPersistentPath;
    private String mIndicatorDaemonPath;
    private String mObserverPersistentPath;
    private String mObserverDaemonPath;

    private Context mContext;

    @Override
    public void onInit(Context context) {
        initIndicatorFiles(context);
    }

    private boolean initIndicatorFiles(Context context) {
        mContext = context;
        File dir = context.getDir(INDICATOR_DIR_NAME, Context.MODE_PRIVATE);
        mIndicatorPersistentPath = new File(dir, INDICATOR_PERSISTENT_FILENAME).getAbsolutePath();
        mIndicatorDaemonPath = new File(dir, INDICATOR_DAEMON_ASSISTANT_FILENAME).getAbsolutePath();
        mObserverPersistentPath = new File(dir, OBSERVER_PERSISTENT_FILENAME).getAbsolutePath();
        mObserverDaemonPath = new File(dir, OBSERVER_DAEMON_ASSISTANT_FILENAME).getAbsolutePath();
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs) {
                return false;
            }
        }
        try {
            createNewFile(dir, INDICATOR_PERSISTENT_FILENAME);
            createNewFile(dir, INDICATOR_DAEMON_ASSISTANT_FILENAME);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "create new file:" + e);
            return false;
        }
    }

    private void createNewFile(File dirFile, String fileName) throws IOException {
        File file = new File(dirFile, fileName);
        if (!file.exists()) {
            boolean isCreated = file.createNewFile();
            Log.d(TAG, "create file:" + isCreated);
        }
    }

    @Override
    public void onPersistentCreate(Context context, AutoStartConfigs configs) {
        WorkThreadHolder.post(() -> {
            initIndicatorFiles(context);
            Log.d(TAG, "onPersistentCreate:\nself indicator:" + mIndicatorPersistentPath
                    + "\ndaemon indicator:" + mIndicatorDaemonPath
                    + "\nself observer:" + mObserverPersistentPath
                    + "\ndaemon observer:" + mObserverDaemonPath);
            new NativeAutoStart().doDaemon(mIndicatorPersistentPath, mIndicatorDaemonPath,
                    mObserverPersistentPath, mObserverDaemonPath);
        });
    }

    @Override
    public void onDaemonAssistantCreate(Context context, AutoStartConfigs configs) {
        WorkThreadHolder.post(() -> {
            initIndicatorFiles(context);
            Log.d(TAG, "onDaemonAssistantCreate:\nself indicator:" + mIndicatorDaemonPath
                    + "\ndaemon indicator:" + mIndicatorPersistentPath
                    + "\nself observer:" + mObserverDaemonPath
                    + "\ndaemon observer:" + mObserverPersistentPath);
            new NativeAutoStart().doDaemon(mIndicatorDaemonPath, mIndicatorPersistentPath,
                    mObserverDaemonPath, mObserverPersistentPath);
        });
    }

    @Override
    public void onDaemonDead() {
        Log.i(TAG, "on daemon dead!");
    }

    private static class WorkThreadHolder {
        private static final Handler HANDLER = getHandler();

        private static Handler getHandler() {
            HandlerThread thread = new HandlerThread("daemon-thread");
            thread.start();
            return new Handler(thread.getLooper());
        }

        public static void post(Runnable task) {
            getHandler().post(task);
        }
    }
}
