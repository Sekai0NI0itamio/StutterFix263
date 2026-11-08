package itamio.stutterfix;

import itamio.stutterfix.config.ThreadConfig;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unofficial port of StutterFix by wisecase2 (MIT).
 * See NOTICE for credit and licensing, and README for port status:
 * GUI removed (JSON config only), main-worker-executor overwrite
 * dropped because Util.getMainWorkerExecutor was removed in 26.3
 * (replaced by backgroundExecutor()).
 */
public class StutterFix implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("stutterfix263");

    public static boolean isClientInitialized = false;
    public static ThreadConfig threadconfig = new ThreadConfig();
    public static boolean isConfigInitialized = false;
    public static ExecutorService saveThread = Executors.newSingleThreadExecutor(r -> {
        Thread thread = new Thread(r, "stutterfix-config-save");
        thread.setDaemon(true);
        return thread;
    });

    public static Thread renderThread;
    public static Thread serverThread;
    public static boolean isInitializedRenderThread = false;
    public static boolean isInitializedServerThread = false;

    public static void loadRenderThread(Thread thread) {
        renderThread = thread;
        isInitializedRenderThread = true;
    }

    public static void loadServerThread(Thread thread) {
        serverThread = thread;
        isInitializedServerThread = true;
    }

    public static void configPriorityRenderThread() {
        if (isConfigInitialized && isInitializedRenderThread) {
            renderThread.setPriority(threadconfig.renderThreadPriority);
        }
    }

    public static void configPriorityServerThread() {
        if (isConfigInitialized && isInitializedServerThread) {
            serverThread.setPriority(threadconfig.serverThreadPriority);
        }
    }

    @Override
    public void onInitialize() {
        threadconfig = ThreadConfig.readConfig("stutterfix-config.json");
        saveThread.execute(() -> threadconfig.saveConfig());
        isConfigInitialized = true;
        configPriorityRenderThread();
        configPriorityServerThread();
    }
}
