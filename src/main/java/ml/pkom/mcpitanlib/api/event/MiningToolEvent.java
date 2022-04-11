package ml.pkom.mcpitanlib.api.event;

import ml.pkom.mcpitanlib.api.tag.MineableToolTags;

public class MiningToolEvent {
    private int level;
    private MineableToolTags toolTags;

    public MiningToolEvent(MineableToolTags toolTags, int level) {
        this.level = level;
        this.toolTags = toolTags;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setToolTags(MineableToolTags toolTags) {
        this.toolTags = toolTags;
    }

    public int getLevel() {
        return level;
    }

    public MineableToolTags getToolTags() {
        return toolTags;
    }
}
