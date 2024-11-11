package factorio.object.types.item;

import com.google.gson.annotations.SerializedName;
import factorio.object.types.Type;

public enum Logistics implements Type {
    // Storage
    @SerializedName("wooden-chest") WOODEN_CHEST,
    @SerializedName("iron-chest") IRON_CHEST,
    @SerializedName("steel-chest") STEEL_CHEST,
    @SerializedName("storage-tank") STORAGE_TANK,
    // Belts
    @SerializedName("transport-belt") TRANSPORT_BELT,
    @SerializedName("fast-transport-belt") FAST_TRANSPORT_BELT,
    @SerializedName("express-transport-belt") EXPRESS_TRANSPORT_BELT,
    @SerializedName("turbo-transport-belt") TURBO_TRANSPORT_BELT,
    @SerializedName("underground-belt") UNDERGROUND_BELT,
    @SerializedName("fast-underground-belt") FAST_UNDERGROUND_BELT,
    @SerializedName("express-underground-belt") EXPRESS_UNDERGROUND_BELT,
    @SerializedName("turbo-underground-belt") TURBO_UNDERGROUND_BELT,
    @SerializedName("splitter") SPLITTER,
    @SerializedName("fast-splitter") FAST_SPLITTER,
    @SerializedName("express-splitter") EXPRESS_SPLITTER,
    @SerializedName("turbo-splitter") TURBO_SPLITTER,
    // Inserters
    @SerializedName("burner-inserter") BURNER_INSERTER,
    @SerializedName("inserter") INSERTER,
    @SerializedName("long-handed-inserter") LONG_HANDED_INSERTER,
    @SerializedName("fast-inserter") FAST_INSERTER,
    @SerializedName("bulk-inserter") BULK_INSERTER,
    @SerializedName("stack-inserter") STACK_INSERTER,
    // Electric Poles
    @SerializedName("small-electric-pole") SMALL_ELECTRIC_POLE,
    @SerializedName("medium-electric-pole") MEDIUM_ELECTRIC_POLE,
    @SerializedName("big-electric-pole") BIG_ELECTRIC_POLE,
    @SerializedName("substation") SUBSTATION,
    @SerializedName("pipe") PIPE,
    @SerializedName("pipe-to-ground") PIPE_TO_GROUND,
    @SerializedName("pump") PUMP,
    // Train Transport
    @SerializedName("rail") RAIL,
    @SerializedName("rail-ramp") RAIL_RAMP,
    @SerializedName("rail-support") RAIL_SUPPORT,
    @SerializedName("train-stop") TRAIN_STOP,
    @SerializedName("rail-signal") RAIL_SIGNAL,
    @SerializedName("rail-chain-signal") RAIL_CHAIN_SIGNAL,
    @SerializedName("locomotive") LOCOMOTIVE,
    @SerializedName("cargo-wagon") CARGO_WAGON,
    @SerializedName("fluid-wagon") FLUID_WAGON,
    @SerializedName("artillery-wagon") ARTILLERY_WAGON,
    // Transport
    @SerializedName("car") CAR,
    @SerializedName("tank") TANK,
    @SerializedName("spidertron") SPIDERTRON,
    // Logistic Network
    @SerializedName("logistic-robot") LOGISTIC_ROBOT,
    @SerializedName("construction-robot") CONSTRUCTION_ROBOT,
    @SerializedName("active-provider-chest") ACTIVE_PROVIDER_CHEST,
    @SerializedName("passive-provider-chest") PASSIVE_PROVIDER_CHEST,
    @SerializedName("storage-chest") STORAGE_CHEST,
    @SerializedName("buffer-chest") BUFFER_CHEST,
    @SerializedName("requester-chest") REQUESTER_CHEST,
    @SerializedName("roboport") ROBOPORT,
    // Circuit Network
    @SerializedName("small-lamp") SMALL_LAMP,
    @SerializedName("arithmetic-combinator") ARITHMETIC_COMBINATOR,
    @SerializedName("decider-combinator") DECIDER_COMBINATOR,
    @SerializedName("selector-combinator") SELECTOR_COMBINATOR,
    @SerializedName("constant-combinator") CONSTANT_COMBINATOR,
    @SerializedName("power-switch") POWER_SWITCH,
    @SerializedName("programmable-speaker") PROGRAMMABLE_SPEAKER,
    @SerializedName("display-panel") DISPLAY_PANEL,
    // Terrain
    @SerializedName("stone-brick") STONE_BRICK,
    @SerializedName("concrete") CONCRETE,
    @SerializedName("hazard-concrete") HAZARD_CONCRETE,
    @SerializedName("refined-concrete") REFINED_CONCRETE,
    @SerializedName("refined-hazard-concrete") REFINED_HAZARD_CONCRETE,
    @SerializedName("landfill") LANDFILL,
    @SerializedName("artificial-yumako-soil") ARTIFICIAL_YUMAKO_SOIL,
    @SerializedName("overgrowth-yumako-soil") OVERGROWTH_YUMAKO_SOIL,
    @SerializedName("artificial-jellynut-soil") ARTIFICIAL_JELLYNUT_SOIL,
    @SerializedName("overgrowth-jellynut-soil") OVERGROWTH_JELLYNUT_SOIL,
    @SerializedName("ice-platform") ICE_PLATFORM,
    @SerializedName("foundation") FOUNDATION,
    @SerializedName("cliff-explosives") CLIFF_EXPLOSIVES;

    @Override
    public String getType() {
        return null;
    }
}
