package com.example.fifteen.views;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.animation.AccelerateInterpolator;

import com.example.fifteen.Colors;
import com.example.fifteen.Dimensions;
import com.example.fifteen.Settings;

import java.util.ArrayList;

public class TopPanelView extends BaseView {

    private final ArrayList<Button> mButtons;
    private Callback mCallback;

    private final int mButtonTextOffset;
    private final Paint mPaintButton;
    private final Paint mPaintOverlay;
    private final Paint mPaintTextButton;
    private final TimeInterpolator interpolator = new AccelerateInterpolator();

    public TopPanelView() {
        mButtons = new ArrayList<>();

        mPaintTextButton = new Paint();
        mPaintTextButton.setAntiAlias(Settings.antiAlias);
        mPaintTextButton.setColor(Colors.getTileTextColor());
        mPaintTextButton.setTypeface(Settings.typeface);
        mPaintTextButton.setTextAlign(Paint.Align.CENTER);
        mPaintTextButton.setTextSize(Dimensions.interfaceFontSize);

        mPaintButton = new Paint();
        mPaintButton.setAntiAlias(Settings.antiAlias);
        mPaintButton.setColor(Colors.getTileColor());

        mPaintOverlay = new Paint();
        mPaintOverlay.setAntiAlias(Settings.antiAlias);
        mPaintOverlay.setColor(Colors.getBackgroundColor());

        Rect r = new Rect();
        mPaintTextButton.getTextBounds("A", 0, 1, r);
        mButtonTextOffset = r.centerY();

        mShow = true;
    }

    public void addButton(int id, String caption) {
        mButtons.add(new Button(id, caption));
        updateWidths();
    }

    public void setButtonCaption(int id, String caption) {
        for (Button button : mButtons) {
            if (button.id == id) {
                button.caption = caption;
            }
        }
    }

    private void updateWidths() {
        int size = mButtons.size();
        float width = Dimensions.surfaceWidth / size;
        Button b;
        for (int i = 0; i < size; i++) {
            b = mButtons.get(i);
            b.rect.set(width * i, 0.0f, width * (i + 1), Dimensions.topBarHeight);
        }
    }

    public boolean onClick(float x, float y) {
        for (Button b : mButtons) {
            if (b.contains(x, y)) {
                b.setOverlay(Settings.animationSpeed);
                if (mCallback != null) {
                    mCallback.onTopPanelButtonClick(b.id);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas, long elapsedTime) {
        if (!mShow) {
            return;
        }

        canvas.drawRect(0.0f, 0.0f, Dimensions.surfaceWidth, Dimensions.topBarHeight, mPaintButton);

        for (Button button : mButtons) {
            if ((button.frame -= elapsedTime) > 0) {
                float a = interpolator.getInterpolation((float) button.frame / Settings.animationSpeed);
                mPaintOverlay.setAlpha((int) (255 * a));
                canvas.drawRect(button.rect, mPaintOverlay);
            }
            canvas.drawText(button.caption, button.rect.centerX(),
                    button.rect.centerY() - mButtonTextOffset, mPaintTextButton);
        }
    }

    public void update() {
        mPaintTextButton.setAntiAlias(Settings.antiAlias);
        mPaintButton.setAntiAlias(Settings.antiAlias);
        mPaintOverlay.setAntiAlias(Settings.antiAlias);

        mPaintTextButton.setColor(Colors.getTileTextColor());
        mPaintOverlay.setColor(Colors.getBackgroundColor());
        mPaintButton.setColor(Colors.getTileColor());
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    private static class Button {

        final RectF rect;
        final int id;
        long frame = 0;
        String caption;

        Button(int id, String caption) {
            this.rect = new RectF();
            this.caption = caption;
            this.id = id;
        }

        boolean contains(float x, float y) {
            return rect.contains(x, y);
        }

        void setOverlay(long frames) {
            frame = frames;
        }
    }

    public interface Callback {

        void onTopPanelButtonClick(int id);
    }
}
