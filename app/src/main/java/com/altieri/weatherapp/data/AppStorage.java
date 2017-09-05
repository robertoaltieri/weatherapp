package com.altieri.weatherapp.data;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

class AppStorage {
    private final AssetManager mAssets;

    @Inject
    AppStorage(AssetManager assets) {
        mAssets = assets;
    }

    String fileFromAsset(String filename) {
        String json;
        try {
            InputStream is = mAssets.open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            return null;
        }
        return json;
    }
}
