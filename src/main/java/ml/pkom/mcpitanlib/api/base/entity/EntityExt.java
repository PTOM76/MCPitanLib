package ml.pkom.mcpitanlib.api.base.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public class EntityExt extends Entity {
    public EntityExt(EntityType<?> type, World world) {
        super(type, world);
    }

    public void initDataTracker() {
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {

    }

    public Packet<?> createSpawnPacket() {
        return null;
    }
}
