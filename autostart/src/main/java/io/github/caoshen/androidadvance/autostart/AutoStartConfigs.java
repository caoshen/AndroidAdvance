package io.github.caoshen.androidadvance.autostart;

public class AutoStartConfigs {
    public final AutoStartConfig PERSISTENT_CONFIG;
    public final AutoStartConfig DAEMON_ASSISTANT_CONFIG;

    public AutoStartConfigs(AutoStartConfig persistentConfig, AutoStartConfig daemonAssistantConfig) {
        PERSISTENT_CONFIG = persistentConfig;
        DAEMON_ASSISTANT_CONFIG = daemonAssistantConfig;
    }

    public static class AutoStartConfig {
        public final String processName;
        public final String serviceName;

        public AutoStartConfig(String processName, String serviceName) {
            this.processName = processName;
            this.serviceName = serviceName;
        }
    }
}
