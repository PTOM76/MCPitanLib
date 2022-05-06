package ml.pkom.mcpitanlib.api.event;

import net.minecraft.entity.EntityType;

public class EntityRegisteredEvent extends AbstractRegisteredEvent {

    private EntityType<?> entityType;

    public EntityRegisteredEvent() {
        super("entity");
    }

    public EntityRegisteredEvent(EntityType<?> entityType) {
        this();
        setEntityType(entityType);
    }

    public boolean hasEntityType() {
        return entityType != null;
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType<?> entityType) {
        this.entityType = entityType;
    }
}
