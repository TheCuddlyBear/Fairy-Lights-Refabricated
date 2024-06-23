package me.paulf.fairylights.server.sound;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import me.paulf.fairylights.FairyLights;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;


public final class FLSounds {
    private FLSounds() {}

    public static final LazyRegistrar<SoundEvent> REG = LazyRegistrar.create(Registries.SOUND_EVENT, FairyLights.ID);

    public static final RegistryObject<SoundEvent> CORD_STRETCH = create("cord.stretch");

    public static final RegistryObject<SoundEvent> CORD_CONNECT = create("cord.connect");

    public static final RegistryObject<SoundEvent> CORD_DISCONNECT = create("cord.disconnect");

    public static final RegistryObject<SoundEvent> CORD_SNAP = create("cord.snap");

    public static final RegistryObject<SoundEvent> JINGLE_BELL = create("jingle_bell");

    public static final RegistryObject<SoundEvent> FEATURE_COLOR_CHANGE = create("feature.color_change");

    public static final RegistryObject<SoundEvent> FEATURE_LIGHT_TURNON = create("feature.light_turnon");

    public static final RegistryObject<SoundEvent> FEATURE_LIGHT_TURNOFF = create("feature.light_turnoff");

    private static RegistryObject<SoundEvent> create(final String name) {
        return REG.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FairyLights.ID, name)));
    }
}
