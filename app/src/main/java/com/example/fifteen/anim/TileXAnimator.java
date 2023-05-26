package com.example.fifteen.anim;

import com.example.fifteen.Tile;

public class TileXAnimator extends TileAnimator {

    public TileXAnimator(Tile target) {
        super(target);
    }

    @Override
    protected void update(Tile target, float value) {
        target.setCanvasX(value);
    }

}
