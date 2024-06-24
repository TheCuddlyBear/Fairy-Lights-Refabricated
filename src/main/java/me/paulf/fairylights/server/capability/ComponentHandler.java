package me.paulf.fairylights.server.capability;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import me.paulf.fairylights.FairyLights;
import me.paulf.fairylights.server.fastener.Fastener;
import me.paulf.fairylights.server.fastener.FastenerCCA;
import net.minecraft.resources.ResourceLocation;
public final class ComponentHandler {
    private ComponentHandler() {}

    public static final ResourceLocation FASTENER_ID = new ResourceLocation(FairyLights.ID, "fastener");

    public static final ComponentKey<FastenerCCA> FASTENER_CAP = ComponentRegistry.getOrCreate(FASTENER_ID, FastenerCCA.class);

    //public static final Capability<Fastener<?>> FASTENER_CAP = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register() {
    }
}
