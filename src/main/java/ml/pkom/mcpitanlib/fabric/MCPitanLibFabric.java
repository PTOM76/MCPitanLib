package ml.pkom.mcpitanlib.fabric;

import ml.pkom.mcpitanlib.api.MCPitanLib;
import ml.pkom.mcpitanlib.debug.SampleMod;
import net.fabricmc.api.ModInitializer;

public class MCPitanLibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MCPitanLib.init();
        SampleMod.init();
    }
}
