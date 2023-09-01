package com.iwex.movies.model.review;

import androidx.annotation.ColorRes;

public enum TypeEnum {
    POSITIVE(android.R.color.holo_green_light),
    NEUTRAL(android.R.color.holo_orange_light),
    NEGATIVE(android.R.color.holo_red_light),
    UNKNOWN(android.R.color.holo_blue_dark);

    @ColorRes
    private final int color;

    TypeEnum(@ColorRes int color) {
        this.color = color;
    }

    @ColorRes
    public int getColor() {
        return color;
    }

    public static TypeEnum getTypeFromConstant(String type) {
        switch (type) {
            case Type.POSITIVE:
                return POSITIVE;
            case Type.NEUTRAL:
                return NEUTRAL;
            case Type.NEGATIVE:
                return NEGATIVE;
            default:
                return UNKNOWN;
        }
    }
}
