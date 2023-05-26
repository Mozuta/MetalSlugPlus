package com.example.fifteen.views;

import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.animation.AccelerateInterpolator;

import com.example.fifteen.Colors;
import com.example.fifteen.Dimensions;
import com.example.fifteen.GameState;
import com.example.metalslug.R;
import com.example.fifteen.Settings;
import com.example.fifteen.Tools;

import java.util.ArrayList;
import java.util.List;

public class HardModeView extends BaseView {

    private static final int ID_CHECK = 0;
    private static final int ID_PEEK = 1;

    private final Paint mPaintTextValue;
    private final Resources mResources;
    private final List<Button> mButtons = new ArrayList<>(2);
    private final TimeInterpolator interpolator = new AccelerateInterpolator();
    private final RectF mRect = new RectF();
    private final int mTextOffsetY;
    private Callbacks mCallbacks;

    public HardModeView(Resources res) {
        mResources = res;
        mPaintTextValue = new Paint();
        mPaintTextValue.setAntiAlias(Settings.antiAlias);
        mPaintTextValue.setTypeface(Settings.typeface);
        mPaintTextValue.setTextAlign(Paint.Align.CENTER);
        mPaintTextValue.setTextSize(Dimensions.interfaceFontSize * 1.4f);

        updateRect();

        Rect r = new Rect();
        mPaintTextValue.getTextBounds("A", 0, 1, r);
        mTextOffsetY = r.centerY();

        updateButtons();
    }

    @Override
    public void draw(Canvas canvas, long elapsedTime) {
        for (Button button : mButtons) {
            int color;
            if ((button.frame -= elapsedTime) > 0) {
                float fraction = interpolator.getInterpolation(1 - (float) button.frame / getAnimDuration());
                color = Tools.interpolateColor(Colors.ERROR, Colors.getHardModeButtonsColor(), fraction);
            } else {
                GameState state = GameState.get();
                if (state.isNotStarted() || state.paused || state.isSolved()) {
                    color = Colors.getHardModeButtonsColor() & 0x40ffffff;
                } else {
                    color = Colors.getHardModeButtonsColor();
                }
            }
            mPaintTextValue.setColor(color);
            canvas.drawText(button.title, button.rect.centerX(), button.rect.centerY() - mTextOffsetY, mPaintTextValue);
        }
    }

    @Override
    public void update() {
        mPaintTextValue.setAntiAlias(Settings.antiAlias);
        updateRect();
        updateButtonDimensions();
    }

    public void setCallbacks(Callbacks callbacks) {
        mCallbacks = callbacks;
    }

    public boolean isPeekAt(float x, float y) {
        Button peek = findButtonById(ID_PEEK);
        return peek.contains(x, y);
    }

    public boolean onClick(float x, float y) {
        GameState state = GameState.get();
        if (mCallbacks != null && !state.isNotStarted() && !state.paused) {
            Button check = findButtonById(ID_CHECK);
            if (check.contains(x, y)) {
                if (!mCallbacks.onCheckButtonClick()) {
                    check.frame = getAnimDuration();
                }
                return true;
            }
        }
        return false;
    }

    private Button findButtonById(int id) {
        for (Button button : mButtons) {
            if (button.id == id) {
                return button;
            }
        }
        throw new IllegalArgumentException("Unknown button with id=" + id);
    }

    private void updateButtons() {
        mButtons.clear();
        mButtons.add(new Button(ID_CHECK, mResources.getString(R.string.action_hm_check)));
        mButtons.add(new Button(ID_PEEK, mResources.getString(R.string.action_hm_peek)));
        updateButtonDimensions();
    }

    private void updateButtonDimensions() {
        int size = mButtons.size();
        float width = mRect.width() / size;
        Button b;
        for (int i = 0; i < size; i++) {
            b = mButtons.get(i);
            b.rect.set(mRect.left + width * i, mRect.top, mRect.left + width * (i + 1), mRect.bottom);
        }
    }

    private void updateRect() {
        float width = Dimensions.surfaceWidth * .75f;
        float margin = (Dimensions.surfaceWidth - width) / 2;
        mRect.set(
                margin,
                Dimensions.hardModeViewMarginBottom - Dimensions.hardModeViewHeight,
                margin + width,
                Dimensions.hardModeViewMarginBottom
        );
    }

    private long getAnimDuration() {
        return Settings.animationSpeed * 2;
    }

    private static class Button {

        final RectF rect;
        final int id;
        final String title;
        long frame = 0;

        Button(int id, String title) {
            this.rect = new RectF();
            this.title = title;
            this.id = id;
        }

        boolean contains(float x, float y) {
            return rect.contains(x, y);
        }
    }

    public interface Callbacks {

        boolean onCheckButtonClick();
    }
}
