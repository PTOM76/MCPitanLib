package ml.pkom.mcpitanlib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class MCPitanUtils {

    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    public static boolean isServer() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }


}
