package factorio.object.types;

import com.google.gson.annotations.SerializedName;

public enum VirtualSignal implements Type {
    // Numbers
    @SerializedName("signal-0") SIGNAL_0,
    @SerializedName("signal-1") SIGNAL_1,
    @SerializedName("signal-2") SIGNAL_2,
    @SerializedName("signal-3") SIGNAL_3,
    @SerializedName("signal-4") SIGNAL_4,
    @SerializedName("signal-5") SIGNAL_5,
    @SerializedName("signal-6") SIGNAL_6,
    @SerializedName("signal-7") SIGNAL_7,
    @SerializedName("signal-8") SIGNAL_8,
    @SerializedName("signal-9") SIGNAL_9,
    // Letters
    @SerializedName("signal-A") SIGNAL_A,
    @SerializedName("signal-B") SIGNAL_B,
    @SerializedName("signal-C") SIGNAL_C,
    @SerializedName("signal-D") SIGNAL_D,
    @SerializedName("signal-E") SIGNAL_E,
    @SerializedName("signal-F") SIGNAL_F,
    @SerializedName("signal-G") SIGNAL_G,
    @SerializedName("signal-H") SIGNAL_H,
    @SerializedName("signal-I") SIGNAL_I,
    @SerializedName("signal-J") SIGNAL_J,
    @SerializedName("signal-K") SIGNAL_K,
    @SerializedName("signal-L") SIGNAL_L,
    @SerializedName("signal-M") SIGNAL_M,
    @SerializedName("signal-N") SIGNAL_N,
    @SerializedName("signal-O") SIGNAL_O,
    @SerializedName("signal-P") SIGNAL_P,
    @SerializedName("signal-Q") SIGNAL_Q,
    @SerializedName("signal-R") SIGNAL_R,
    @SerializedName("signal-S") SIGNAL_S,
    @SerializedName("signal-T") SIGNAL_T,
    @SerializedName("signal-U") SIGNAL_U,
    @SerializedName("signal-V") SIGNAL_V,
    @SerializedName("signal-W") SIGNAL_W,
    @SerializedName("signal-X") SIGNAL_X,
    @SerializedName("signal-Y") SIGNAL_Y,
    @SerializedName("signal-Z") SIGNAL_Z,
    // Colors
    @SerializedName("signal-red") SIGNAL_RED,
    @SerializedName("signal-green") SIGNAL_GREEN,
    @SerializedName("signal-blue") SIGNAL_BLUE,
    @SerializedName("signal-yellow") SIGNAL_YELLOW,
    @SerializedName("signal-pink") SIGNAL_PINK,
    @SerializedName("signal-cyan") SIGNAL_CYAN,
    @SerializedName("signal-white") SIGNAL_WHITE,
    @SerializedName("signal-grey") SIGNAL_GREY,
    @SerializedName("signal-black") SIGNAL_BLACK,
    // Miscellaneous
    @SerializedName("signal-check") SIGNAL_CHECK,
    @SerializedName("signal-deny") SIGNAL_DENY,
    @SerializedName("signal-info") SIGNAL_INFO,
    @SerializedName("signal-dot") SIGNAL_DOT,
    // Shapes
    @SerializedName("shape-vertical") SHAPE_VERTICAL,
    @SerializedName("shape-horizontal") SHAPE_HORIZONTAL,
    @SerializedName("shape-diagonal") SHAPE_DIAGONAL,
    @SerializedName("shape-curve") SHAPE_CURVE,
    @SerializedName("shape-cross") SHAPE_CROSS,
    @SerializedName("shape-diagonal-cross") SHAPE_DIAGONAL_CROSS,
    @SerializedName("shape-corner") SHAPE_CORNER,
    @SerializedName("shape-t") SHAPE_T,
    @SerializedName("shape-circle") SHAPE_CIRCLE,
    // Arrows
    @SerializedName("up-arrow") UP_ARROW,
    @SerializedName("up-right-arrow") UP_RIGHT_ARROW,
    @SerializedName("right-arrow") RIGHT_ARROW,
    @SerializedName("down-right-arrow") DOWN_RIGHT_ARROW,
    @SerializedName("down-arrow") DOWN_ARROW,
    @SerializedName("down-left-arrow") DOWN_LEFT_ARROW,
    @SerializedName("left-arrow") LEFT_ARROW,
    @SerializedName("up-left-arrow") UP_LEFT_ARROW,
    // Additions
    @SerializedName("signal-stack-size") SIGNAL_STACK_SIZE,
    @SerializedName("signal-heart") SIGNAL_HEART,
    @SerializedName("signal-skull") SIGNAL_SKULL,
    @SerializedName("signal-ghost") SIGNAL_GHOST;

    @Override
    public String getType() {
        return "virtual";
    }
}
