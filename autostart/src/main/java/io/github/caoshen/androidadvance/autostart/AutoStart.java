package io.github.caoshen.androidadvance.autostart;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AutoStart {
    private static final String TAG = "AutoStart";

    private final AutoStartConfigs mConfigs;

    private AutoStart(AutoStartConfigs configs) {
        mConfigs = configs;
    }

    public static void init(Context context, AutoStartConfigs configs) {
        AutoStart autoStart = new AutoStart(configs);
        autoStart.initDaemon(context);
    }

    private void initDaemon(Context context) {
        if (mConfigs == null) {
            Log.e(TAG, "init daemon failed, no configurations.");
            return;
        }

        if (context == null) {
            Log.e(TAG, "init daemon failed, no context.");
            return;
        }

        String processName = getProcessName();
        if (processName == null) {
            Log.e(TAG, "init daemon failed, no process name.");
            return;
        }
        String packageName = context.getPackageName();
        Log.d(TAG, "create process=" + processName);
        if (processName.equals(mConfigs.PERSISTENT_CONFIG.processName)) {
            Log.d(TAG, "init persistent: " + processName + ", config:" + mConfigs.PERSISTENT_CONFIG.processName);
            IAutoStartProcess.Fetcher.fetch().onPersistentCreate(context, mConfigs);
        } else if (processName.equals(mConfigs.DAEMON_ASSISTANT_CONFIG.processName)) {
            Log.d(TAG, "init daemon: " + processName + ", config:" + mConfigs.DAEMON_ASSISTANT_CONFIG.processName);
            IAutoStartProcess.Fetcher.fetch().onDaemonAssistantCreate(context, mConfigs);
        } else if (processName.equals(packageName)) {
            Log.d(TAG, "init main process: " + processName + ", packageName:" + packageName);
            IAutoStartProcess.Fetcher.fetch().onInit(context);
        } else {
            Log.e(TAG, "unknown process:" + processName);
        }
    }

    private String getProcessName() {
        File file = new File("/proc/self/cmdline");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.readLine().trim();
        } catch (Exception e) {
            Log.e(TAG, "getProcessName:" + e);
            return null;
        }
    }
}
