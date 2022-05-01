package ml.pkom.mcpitanlib.api.event.render;

import net.minecraft.client.util.math.MatrixStack;

public class ScreenRenderEvent {
    MatrixStack matrices;
    float delta;
    int mouseX;
    int mouseY;

    public ScreenRenderEvent(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.matrices = matrices;
        this.delta = delta;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public MatrixStack getMatrices() {
        return matrices;
    }

    public float getDelta() {
        return delta;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
