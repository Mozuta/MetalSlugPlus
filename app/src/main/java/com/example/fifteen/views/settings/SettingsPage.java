package com.example.fifteen.views.settings;

import android.graphics.Canvas;

import com.example.fifteen.views.SettingsView;

public interface SettingsPage {

    void init(int lineSpacing, int topMargin, int padding, int textHeight);

    void draw(Canvas canvas, float valueRight, float textLeft, float textYOffset);

    void onClick(float x, float y, float dx);

    void update();

    void addCallback(SettingsView.Callbacks callbacks);
}
