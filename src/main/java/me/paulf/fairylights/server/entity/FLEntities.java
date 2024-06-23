package me.paulf.fairylights.server.entity;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import me.paulf.fairylights.FairyLights;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class FLEntities {
    private FLEntities() {}
    // TODO: Not working yet, fix the entity registring.
    public static final LazyRegistrar<EntityType<?>> REG = LazyRegistrar.create(Registries.ENTITY_TYPE, FairyLights.ID);

    public static final RegistryObject<EntityType<FenceFastenerEntity>> FASTENER = REG.register("fastener", () ->
        EntityType.Builder.<FenceFastenerEntity>of(FenceFastenerEntity::new, MobCategory.MISC)
            .sized(1.15F, 2.8F)
            .setTrackingRange(10)
            .setUpdateInterval(Integer.MAX_VALUE)
            .setShouldReceiveVelocityUpdates(false)
            .setCustomClientFactory((message, world) -> new FenceFastenerEntity(world))
            .build(FairyLights.ID + ":fastener")
    );
}
