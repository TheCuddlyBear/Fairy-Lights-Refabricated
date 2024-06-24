package me.paulf.fairylights.server.connection;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import me.paulf.fairylights.FairyLights;
import me.paulf.fairylights.server.item.FLItems;

public final class ConnectionTypes {
    private ConnectionTypes() {}

    public static final LazyRegistrar<ConnectionType<?>> REG = LazyRegistrar.create(FairyLights.CONNECTION_TYPE, FairyLights.ID);

    public static final RegistryObject<ConnectionType<HangingLightsConnection>> HANGING_LIGHTS = REG.register("hanging_lights",
        () -> ConnectionType.Builder.create(HangingLightsConnection::new).item(FLItems.HANGING_LIGHTS).build()
    );

    public static final RegistryObject<ConnectionType<GarlandVineConnection>> VINE_GARLAND = REG.register("vine_garland",
        () -> ConnectionType.Builder.create(GarlandVineConnection::new).item(FLItems.GARLAND).build()
    );

    public static final RegistryObject<ConnectionType<GarlandTinselConnection>> TINSEL_GARLAND = REG.register("tinsel_garland",
        () -> ConnectionType.Builder.create(GarlandTinselConnection::new).item(FLItems.TINSEL).build()
    );

    public static final RegistryObject<ConnectionType<PennantBuntingConnection>> PENNANT_BUNTING = REG.register("pennant_bunting",
        () -> ConnectionType.Builder.create(PennantBuntingConnection::new).item(FLItems.PENNANT_BUNTING).build()
    );

    public static final RegistryObject<ConnectionType<LetterBuntingConnection>> LETTER_BUNTING = REG.register("letter_bunting",
        () -> ConnectionType.Builder.create(LetterBuntingConnection::new).item(FLItems.LETTER_BUNTING).build()
    );
}
