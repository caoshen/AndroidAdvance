package io.github.caoshen.androidadvance.autostart;

import android.content.Context;

public interface IAutoStartProcess {

    void onInit(Context context);

    void onPersistentCreate(Context context, AutoStartConfigs configs);

    void onDaemonAssistantCreate(Context context, AutoStartConfigs configs);

    void onDaemonDead();

    class Fetcher {
        private static volatile IAutoStartProcess sDaemonStrategy;

        static IAutoStartProcess fetch() {
            if (sDaemonStrategy == null) {
                sDaemonStrategy = new AutoStartProcessImpl();
            }
            return sDaemonStrategy;
        }
    }
}
