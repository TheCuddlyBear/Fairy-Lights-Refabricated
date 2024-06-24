package me.paulf.fairylights.server.fastener.accessor;

import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import me.paulf.fairylights.server.fastener.Fastener;
import me.paulf.fairylights.server.fastener.FastenerType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

public interface FastenerAccessor {
    default LazyOptional<Fastener<?>> get(final Level world) {
        return this.get(world, true);
    }

    LazyOptional<Fastener<?>> get(final Level world, final boolean load);

    boolean isGone(final Level world);

    FastenerType getType();

    CompoundTag serialize();

    void deserialize(CompoundTag compound);
}
