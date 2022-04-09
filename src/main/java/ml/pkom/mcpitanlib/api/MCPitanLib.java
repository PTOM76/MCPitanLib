package ml.pkom.mcpitanlib.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MCPitanLib {
    public static final String MOD_ID = "mcpitanlib";
    public static final String MOD_NAME = "MCPitanLib";

    private static final Logger logger = LogManager.getLogger(MOD_ID);

    public static Logger getLogger() {
        return logger;
    }

    public static void init() {
        getLogger().info("Loaded " + MOD_NAME);
        //System.out.println(MCPitanLibExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
