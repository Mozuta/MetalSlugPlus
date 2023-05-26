package com.example.fifteen.anim;

import com.example.fifteen.Tile;

public class TileYAnimator extends TileAnimator {

    public TileYAnimator(Tile target) {
        super(target);
    }

    @Override
    protected void update(Tile target, float value) {
        target.setCanvasY(value);
    }

}
