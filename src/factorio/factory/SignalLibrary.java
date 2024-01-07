package factorio.factory;

import factorio.object.Icon;
import factorio.object.Signal;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class SignalLibrary {

    public static boolean has(int pos) {
        return pos < (virtual.length + item.length + fluid.length);
    }

    public static boolean has(String name) {
        return Arrays.asList(virtual).contains(name) || Arrays.asList(item).contains(name) || Arrays.asList(fluid).contains(name);
    }

    public static Signal get(int pos) {
        if (has(pos)) {
            if (pos < virtual.length) return new Signal(virtual[pos], "virtual");
            pos -= virtual.length;
            if (pos < item.length) return new Signal(item[pos], "item");
            pos -= item.length;
            if (pos < fluid.length) return new Signal(fluid[pos], "fluid");
            if (pos < fluids.values().length) return new Signal(fluids.values()[pos].name, "fluid");
        }
        return null;
    }

    public static Signal get(String name) {
        return new Signal(name, findType(name));
    }

    public static Icon[] getIcons(int ... positions) {
        return IntStream.range(0, positions.length).filter(i -> has(positions[i])).mapToObj(i -> new Icon(i + 1, get(positions[i]))).toArray(Icon[]::new);
    }

    public static Icon[] getIcons(String ... names) {
        return IntStream.range(0, names.length).filter(i -> has(names[i])).mapToObj(i -> new Icon(i + 1, get(names[i]))).toArray(Icon[]::new);
    }

    private static String findType(String name) {
        if (Arrays.asList(virtual).contains(name) || Arrays.asList(extraVirtual).contains(name)) return "virtual";
        else if (Arrays.asList(item).contains(name)) return "item";
        else if (Arrays.asList(fluid).contains(name)) return "fluid";
        return null;
    }

    public enum fluids {
        CRUDE_OIL("crude-oil"),
        FLUID_UNKNOWN("fluid-unknown"),
        HEAVY_OIL("heavy-oil"),
        LIGHT_OIL("light-oil"),
        LUBRICANT("lubricant"),
        PETROLEUM_GAS("petroleum-gas"),
        STEAM("steam"),
        SULFURIC_ACID("sulfuric-acid"),
        WATER("water");
        public final String name;
        fluids(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static final String[] fluid = new String[]{
            "crude-oil",
            "fluid-unknown",
            "heavy-oil",
            "light-oil",
            "lubricant",
            "petroleum-gas",
            "steam",
            "sulfuric-acid",
            "water",
    };

    private static final String[] extraVirtual = new String[]{
            "signal-anything",
            "signal-black",
            "signal-blue",
            "signal-cyan",
            "signal-each",
            "signal-everything",
            "signal-green",
            "signal-grey",
            "signal-pink",
            "signal-red",
            "signal-white",
            "signal-yellow",
    };

    private static final String[] virtual = new String[]{
            "signal-0",
            "signal-1",
            "signal-2",
            "signal-3",
            "signal-4",
            "signal-5",
            "signal-6",
            "signal-7",
            "signal-8",
            "signal-9",
            "signal-A",
            "signal-B",
            "signal-C",
            "signal-D",
            "signal-E",
            "signal-F",
            "signal-G",
            "signal-H",
            "signal-I",
            "signal-J",
            "signal-K",
            "signal-L",
            "signal-M",
            "signal-N",
            "signal-O",
            "signal-P",
            "signal-Q",
            "signal-R",
            "signal-S",
            "signal-T",
            "signal-U",
            "signal-V",
            "signal-W",
            "signal-X",
            "signal-Y",
            "signal-Z",
            "signal-check",
            "signal-dot",
            "signal-info",
    };

    private static final String[] item = new String[]{
            "accumulator",
            "advanced-circuit",
            "arithmetic-combinator",
            "artillery-turret",
            "assembling-machine-1",
            "assembling-machine-2",
            "assembling-machine-3",
            "battery",
            "battery-equipment",
            "battery-mk2-equipment",
            "beacon",
            "belt-immunity-equipment",
            "big-electric-pole",
            "boiler",
            "burner-inserter",
            "burner-mining-drill",
            "centrifuge",
            "chemical-plant",
            "coal",
            "coin",
            "concrete",
            "constant-combinator",
            "construction-robot",
            "copper-cable",
            "copper-ore",
            "copper-plate",
            "crude-oil-barrel",
            "decider-combinator",
            "discharge-defense-equipment",
            "electric-energy-interface",
            "electric-engine-unit",
            "electric-furnace",
            "electric-mining-drill",
            "electronic-circuit",
            "empty-barrel",
            "energy-shield-equipment",
            "energy-shield-mk2-equipment",
            "engine-unit",
            "exoskeleton-equipment",
            "explosives",
            "express-loader",
            "express-splitter",
            "express-transport-belt",
            "express-underground-belt",
            "fast-inserter",
            "fast-loader",
            "fast-splitter",
            "fast-transport-belt",
            "fast-underground-belt",
            "filter-inserter",
            "flamethrower-turret",
            "flying-robot-frame",
            "fusion-reactor-equipment",
            "gate",
            "green-wire",
            "gun-turret",
            "hazard-concrete",
            "heat-exchanger",
            "heat-interface",
            "heat-pipe",
            "heavy-oil-barrel",
            "infinity-chest",
            "infinity-pipe",
            "inserter",
            "iron-chest",
            "iron-gear-wheel",
            "iron-ore",
            "iron-plate",
            "iron-stick",
            "lab",
            "land-mine",
            "landfill",
            "laser-turret",
            "light-oil-barrel",
            "loader",
            "logistic-chest-active-provider",
            "logistic-chest-buffer",
            "logistic-chest-passive-provider",
            "logistic-chest-requester",
            "logistic-chest-storage",
            "logistic-robot",
            "long-handed-inserter",
            "low-density-structure",
            "lubricant-barrel",
            "medium-electric-pole",
            "night-vision-equipment",
            "nuclear-fuel",
            "nuclear-reactor",
            "offshore-pump",
            "oil-refinery",
            "personal-laser-defense-equipment",
            "personal-roboport-equipment",
            "personal-roboport-mk2-equipment",
            "petroleum-gas-barrel",
            "pipe",
            "pipe-to-ground",
            "plastic-bar",
            "player-port",
            "power-switch",
            "processing-unit",
            "programmable-speaker",
            "pump",
            "pumpjack",
            "radar",
            "rail-chain-signal",
            "rail-signal",
            "red-wire",
            "refined-concrete",
            "refined-hazard-concrete",
            "roboport",
            "rocket-control-unit",
            "rocket-fuel",
            "rocket-part",
            "rocket-silo",
            "satellite",
            "simple-entity-with-force",
            "simple-entity-with-owner",
            "small-electric-pole",
            "small-lamp",
            "solar-panel",
            "solar-panel-equipment",
            "solid-fuel",
            "splitter",
            "stack-filter-inserter",
            "stack-inserter",
            "steam-engine",
            "steam-turbine",
            "steel-chest",
            "steel-furnace",
            "steel-plate",
            "stone",
            "stone-brick",
            "stone-furnace",
            "stone-wall",
            "storage-tank",
            "substation",
            "sulfur",
            "sulfuric-acid-barrel",
            "train-stop",
            "transport-belt",
            "underground-belt",
            "uranium-235",
            "uranium-238",
            "uranium-fuel-cell",
            "uranium-ore",
            "used-up-uranium-fuel-cell",
            "water-barrel",
            "wood",
            "wooden-chest",
    };
}
