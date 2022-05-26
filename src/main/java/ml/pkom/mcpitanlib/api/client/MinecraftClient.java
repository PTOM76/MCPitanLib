package ml.pkom.mcpitanlib.api.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.integrated.IntegratedServer;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MinecraftClient {
    private static net.minecraft.client.MinecraftClient MCClient;
    private static net.minecraft.client.MinecraftClient instance = MCClient.getInstance();

    @Nullable
    public ClientWorld world = instance.world;

    @Nullable
    public ClientPlayerEntity player = instance.player;

    @Nullable
    public IntegratedServer server = instance.getServer();

    @Nullable
    public ServerInfo currentServerEntry = instance.getCurrentServerEntry();

    @Nullable
    public ClientConnection integratedServerConnection = Objects.requireNonNull(instance.getNetworkHandler()).getConnection();

    @Nullable
    public Screen currentScreen = instance.currentScreen;

    public @Nullable ClientWorld getWorld() {
        return world;
    }

    public @Nullable ClientPlayerEntity getPlayer() {
        return player;
    }

    public @Nullable Screen getCurrentScreen() {
        return currentScreen;
    }
}
