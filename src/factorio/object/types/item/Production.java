package factorio.object.types.item;

import com.google.gson.annotations.SerializedName;
import factorio.object.types.Type;

public enum Production implements Type {
    // Tools
    @SerializedName("repair-pack") REPAIR_PACK,
    @SerializedName("blueprint") BLUEPRINT,
    @SerializedName("deconstruction-planner") DECONSTRUCTION_PLANNER,
    @SerializedName("upgrade-planner") UPGRADE_PLANNER,
    @SerializedName("blueprint-book") BLUEPRINT_BOOK,
    // Energy
    @SerializedName("boiler") BOILER,
    @SerializedName("steam-engine") STEAM_ENGINE,
    @SerializedName("solar-panel") SOLAR_PANEL,
    @SerializedName("accumulator") ACCUMULATOR,
    @SerializedName("nuclear-reactor") NUCLEAR_REACTOR,
    @SerializedName("heat-exchanger") HEAT_EXCHANGER,
    @SerializedName("steam-turbine") STEAM_TURBINE,
    @SerializedName("fusion-reactor") FUSION_REACTOR,               // This is tied to the Space Age mod from the DLC
    @SerializedName("fusion-generator") FUSION_GENERATOR,           // Not every player will have this
    // Extraction Machines
    @SerializedName("burner-mining-drill") BURNER_MINING_DRILL,
    @SerializedName("electric-mining-drill") ELECTRIC_MINING_DRILL,
    @SerializedName("big-mining-drill") BIG_MINING_DRILL,           // also Space Age
    @SerializedName("offshore-pump") OFFSHORE_PUMP,
    @SerializedName("pumpjack") PUMPJACK,
    // Smelting Machines
    @SerializedName("stone-furnace") STONE_FURNACE,
    @SerializedName("steel-furnace") STEEL_FURNACE,
    @SerializedName("electric-furnace") ELECTRIC_FURNACE,
    @SerializedName("foundry") FOUNDRY,                             // Space Age
    @SerializedName("recycler") RECYCLER,                           // Space Age
    // Agriculture
    @SerializedName("agricultural-tower") AGRICULTURAL_TOWER,
    @SerializedName("biochamber") BIOCHAMBER,
    @SerializedName("captive-biter-spawner") CAPTIVE_BITER_SPAWNER,
    // Production Machines
    @SerializedName("assembling-machine-1") ASSEMBLING_MACHINE_1,
    @SerializedName("assembling-machine-2") ASSEMBLING_MACHINE_2,
    @SerializedName("assembling-machine-3") ASSEMBLING_MACHINE_3,
    @SerializedName("oil-refinery") OIL_REFINERY,
    @SerializedName("chemical-plant") CHEMICAL_PLANT,
    @SerializedName("centrifuge") CENTRIFUGE,
    @SerializedName("electromagnetic-plant") ELECTROMAGNETIC_PLANT, // Space Age
    @SerializedName("cryogenic-plant") CRYOGENIC_PLANT,             // Space Age
    @SerializedName("lab") LAB,
    @SerializedName("biolab") BIOLAB,
    // Environmental Protection
    @SerializedName("lightning-rod") LIGHTNING_ROD,                 // Space Age
    @SerializedName("lightning-collector") LIGHTNING_COLLECTOR,     // Space Age
    @SerializedName("heating-tower") HEATING_TOWER,
    // Modules
    @SerializedName("beacon") BEACON,
    @SerializedName("speed-module") SPEED_MODULE,
    @SerializedName("speed-module-2") SPEED_MODULE_2,
    @SerializedName("speed-module-3") SPEED_MODULE_3,
    @SerializedName("effectivity-module") EFFECTIVITY_MODULE,
    @SerializedName("effectivity-module-2") EFFECTIVITY_MODULE_2,
    @SerializedName("effectivity-module-3") EFFECTIVITY_MODULE_3,
    @SerializedName("productivity-module") PRODUCTIVITY_MODULE,
    @SerializedName("productivity-module-2") PRODUCTIVITY_MODULE_2,
    @SerializedName("productivity-module-3") PRODUCTIVITY_MODULE_3,
    @SerializedName("quality-module") QUALITY_MODULE,       // This is tied to the Quality mod from Space Age DLC
    @SerializedName("quality-module-2") QUALITY_MODULE_2,   // Not every player will have this
    @SerializedName("quality-module-3") QUALITY_MODULE_3;   // TODO: We should probably move this to a separate enum

    @Override
    public String getType() {
        return null;
    }
}
