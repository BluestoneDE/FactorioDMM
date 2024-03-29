package factorio.object;

import java.util.Arrays;

public final class SignalID {
    public String name;
    public String type;

    public SignalID() {}

    public SignalID(String name) {
        if (Arrays.asList(virtuals).contains(name) || Arrays.asList(extraVirtuals).contains(name)) {
            this.name = name;
            this.type = "virtual";
        } else if (Arrays.asList(items).contains(name)) {
            this.name = name;
            this.type = "item";
        } else if (Arrays.asList(fluids).contains(name)) {
            this.name = name;
            this.type = "fluid";
        }
    }

    public SignalID(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static boolean hasID(int pos) {
        return pos < (virtuals.length + items.length + fluids.length);
    }

    public static SignalID getID(int pos) {
        if (hasID(pos)) {
            if (pos < virtuals.length) return new SignalID(virtuals[pos], "virtual");
            pos -= virtuals.length;
            if (pos < items.length) return new SignalID(items[pos], "item");
            pos -= items.length;
            if (pos < fluids.length) return new SignalID(fluids[pos], "fluid");
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static final String[] fluids = new String[]{
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

    private static final String[] extraVirtuals = new String[]{
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

    private static final String[] virtuals = new String[]{
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

    private static final String[] items = new String[]{
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
