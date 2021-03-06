package com.eliasjr.wsxpto.utils;

public class CidadeUtils {
    private CidadeUtils() {
    }

    public static double getDistance(float[] city1, float[] city2) {
        float dx = city1[0] - city2[0];
        float dy = city1[1] - city2[1];
        return Math.sqrt(dx*dx + dy*dy);
    }
}
