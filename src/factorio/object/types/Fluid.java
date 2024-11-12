package factorio.object.types;

import com.google.gson.annotations.SerializedName;

public enum Fluid implements TypeInterface {
    // Vanilla
    @SerializedName("water") WATER,
    @SerializedName("steam") STEAM,
    @SerializedName("crude-oil") CRUDE_OIL,
    @SerializedName("petroleum-gas") PETROLEUM_GAS,
    @SerializedName("light-oil") LIGHT_OIL,
    @SerializedName("heavy-oil") HEAVY_OIL,
    @SerializedName("lubricant") LUBRICANT,
    @SerializedName("sulfuric-acid") SULFURIC_ACID,
    // Space Age: Space
    @SerializedName("thruster-fuel") THRUSTER_FUEL,
    @SerializedName("thruster-oxidizer") THRUSTER_OXIDIZER,
    // Space Age: Vulcanus
    @SerializedName("lava") LAVA,
    @SerializedName("molten-iron") MOLTEN_IRON,
    @SerializedName("molten-copper") MOLTEN_COPPER,
    // Space Age: Fulgora
    @SerializedName("holmium-solution") HOLMIUM_SOLUTION,
    @SerializedName("electrolyte") ELECTROLYTE,
    // Space Age: Aquilo
    @SerializedName("ammoniacal-solution") AMMONIACAL_SOLUTION,
    @SerializedName("ammonia") AMMONIA,
    @SerializedName("fluorine") FLUORINE,
    @SerializedName("fluoroketone-hot") FLUOROKETONE_HOT,
    @SerializedName("fluoroketone-cold") FLUOROKETONE_COLD,
    @SerializedName("lithium-brine") LITHIUM_BRINE,
    // Space Age: Fusion Reactor
    @SerializedName("fusion-plasma") FUSION_PLASMA,
    // Parameters
    @SerializedName("parameter-0") PARAMETER_0,
    @SerializedName("parameter-1") PARAMETER_1,
    @SerializedName("parameter-2") PARAMETER_2,
    @SerializedName("parameter-3") PARAMETER_3,
    @SerializedName("parameter-4") PARAMETER_4,
    @SerializedName("parameter-5") PARAMETER_5,
    @SerializedName("parameter-6") PARAMETER_6,
    @SerializedName("parameter-7") PARAMETER_7,
    @SerializedName("parameter-8") PARAMETER_8,
    @SerializedName("parameter-9") PARAMETER_9,
    // Unknown
    @SerializedName("unknown") UNKNOWN;

    @Override
    public Type getType() {
        return Type.FLUID;
    }
}
