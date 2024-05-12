package factorio.factory;

import factorio.object.Icon;
import factorio.object.Signal;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class SignalLibrary {

    public static boolean has(int pos) {
        return Signal.values().length > pos + 12;
    }

    public static boolean has(String name) {
        return Arrays.stream(Signal.values()).anyMatch(signal -> signal.name.equals(name));
    }

    public static Signal get(int pos) {
        if (has(pos)) {
            return Signal.values()[pos + 12];
        }
        return null;
    }

    public static Signal get(String name) {
        return Arrays.stream(Signal.values()).filter(signal -> signal.name.equals(name)).findFirst().orElse(null);
    }

    public static Icon[] getIcons(int... positions) {
        return IntStream.range(0, positions.length).filter(i -> has(positions[i] + 12)).mapToObj(i -> new Icon(i + 1, get(positions[i] + 12))).toArray(Icon[]::new);
    }

    public static Icon[] getIcons(String... names) {
        return IntStream.range(0, names.length).filter(i -> has(names[i])).mapToObj(i -> new Icon(i + 1, get(names[i]))).toArray(Icon[]::new);
    }

    public static Icon[] getIcons(Signal... signals) {
        return IntStream.range(0, signals.length).filter(i -> signals[i] != null).mapToObj(i -> new Icon(i + 1, signals[i])).toArray(Icon[]::new);
    }
}
