package com.example.fifteen.anim;

import com.example.fifteen.Tile;

public class TileScaleAnimator extends TileAnimator {

    public TileScaleAnimator(Tile target) {
        super(target);
    }

    @Override
    protected void update(Tile target, float value) {
        target.setScale(value);
    }

}
