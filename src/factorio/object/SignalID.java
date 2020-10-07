package factorio.object;

public final class SignalID {
    public String name;
    public String type;

    public SignalID(int pos) {
        if (pos < getVirtuals().length) {
            name = getVirtuals()[pos];
            type = "virtual";
        }
        pos -= getVirtuals().length;
        if (pos < getItems().length) {
            name = getItems()[pos];
            type = "item";
        }
        pos -= getItems().length;
        if (pos < getFluids().length) {
            name = getFluids()[pos];
            type = "fluid";
        }
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

    private static String[] getFluids() {
        return new String[]{
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
    }

    private static String[] getVirtuals() {
        return new String[]{
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
    }

    private static String[] getItems() {
        return new String[]{
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
                "burner-generator",
                "burner-inserter",
                "burner-mining-drill",
                "centrifuge",
                "chemical-plant",
                "coal",
                "coin",
                "computer",
                "concrete",
                "constant-combinator",
                "construction-robot",
                "copper-cable",
                "copper-ore",
                "copper-plate",
                "crash-site-assembling-machine-1-broken",
                "crash-site-assembling-machine-1-repaired",
                "crash-site-assembling-machine-2-broken",
                "crash-site-assembling-machine-2-repaired",
                "crash-site-chest-1",
                "crash-site-chest-2",
                "crash-site-electric-pole",
                "crash-site-generator",
                "crash-site-lab-broken",
                "crash-site-lab-repaired",
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
                "item-unknown",
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
                "small-plane",
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
}
