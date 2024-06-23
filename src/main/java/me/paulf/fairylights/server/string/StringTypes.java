package me.paulf.fairylights.server.string;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import me.paulf.fairylights.FairyLights;

public final class StringTypes {
    private StringTypes() {}

    public static final LazyRegistrar<StringType> REG = LazyRegistrar.create(FairyLights.STRING_TYPE, FairyLights.ID);

    public static final RegistryObject<StringType> BLACK_STRING = REG.register("black_string", () -> new StringType(0x323232));

    public static final RegistryObject<StringType> WHITE_STRING = REG.register("white_string", () -> new StringType(0xF0F0F0));
}
