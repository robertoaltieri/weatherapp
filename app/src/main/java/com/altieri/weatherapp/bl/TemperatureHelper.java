package com.altieri.weatherapp.bl;

import javax.inject.Inject;

public class TemperatureHelper {
    private static final int ZERO_CELSIUS_IN_KELVIN = 273150;

    @Inject
    public TemperatureHelper() {

    }

    public double fromKelvin2Celsius(double kelvin) {
        int diff =  (int) (kelvin * 1000) - ZERO_CELSIUS_IN_KELVIN;
        return diff / 1000.;
    }
}
