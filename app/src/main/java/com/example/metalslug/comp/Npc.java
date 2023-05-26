package com.example.metalslug.comp;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.example.game.Graphics;
import com.example.metalslug.GameView;
import com.example.metalslug.R;
import com.example.metalslug.ViewManager;

public class Npc {

    public static final int OLD_MAN = 1;
    public static final int YOUNG_WOMAN = 2;
    public static final int MINER = 3;
    public static final int WOUND_SOLDER = 4;
    public static final int SMOKER = 5;
    private int type = OLD_MAN;
    private String dialogue;
    private String supplies;
    private boolean isInteracting;
    private int x = 0;
    private int y = 0;
    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;
    private ImageView button; // 按钮视图


    int drawIndex = 0;

    public Npc(int type) {
        this.type = type;
        this.isInteracting = false;
        x = ViewManager.SCREEN_WIDTH + Util.rand(ViewManager.SCREEN_WIDTH >> 1)
                - (ViewManager.SCREEN_WIDTH >> 2);
        y = Player.Y_DEFALUT;
        //different types of NPCs have different dialogue and supplies
        switch (type) {
            case OLD_MAN:
                this.dialogue = "I'm an oldman!";
                this.supplies = "I have some supplies for you!";
                break;
            case YOUNG_WOMAN:
                this.dialogue = "I'm a youngwoman!";
                this.supplies = "I have some supplies for you!";
                break;
            case MINER:
                this.dialogue = "I'm a miner!";
                this.supplies = "I have some supplies for you!";
                break;
            case WOUND_SOLDER:
                this.dialogue = "I'm a wound solder!";
                this.supplies = "I have some supplies for you!";
                break;
            case SMOKER:
                this.dialogue = "I'm a smoker!";
                this.supplies = "I have some supplies for you!";
                break;
        }
    }

    // Getters and setters for the NPC attributes

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getPositionX() {
        return x;
    }
    public void setPositionX(int positionX) {
        this.x = positionX;
    }
    public int getPositionY() {
        return y;
    }
    public void setPositionY(int positionY) {
        this.y = positionY;
    }
    public void setInteracting(boolean interacting) {
        isInteracting = interacting;
    }
    public void onPlayerCollision() {
        // Trigger conversation with the NPC
        if (!isInteracting) {
            isInteracting = true;
            // Display the conversation UI and provide options for dialogue
            // Once the dialogue is over, the player can receive supplies
        }
    }

    public void draw(Canvas canvas) {
        if (canvas == null)
        {
            return;
        }
        switch (type)
        {
            // 5 kind of NPCs
            case OLD_MAN:
                drawAni(canvas, ViewManager.oldMan);
                break;
            case YOUNG_WOMAN:
                drawAni(canvas, ViewManager.youngWoman);
                break;
            case MINER:
                drawAni(canvas, ViewManager.miner);
                break;
            case WOUND_SOLDER:
                drawAni(canvas, ViewManager.woundSolder);
                break;
            case SMOKER:
                drawAni(canvas, ViewManager.smoker);
                break;

        }
    }
    public void drawAni(Canvas canvas, Bitmap[] bitmapArr)
    {
        drawIndex = drawIndex % bitmapArr.length;
        Bitmap bitmap = bitmapArr[drawIndex];
        if (bitmap == null || bitmap.isRecycled())
        {
            return;
        }
        if (bitmap.isRecycled())
        {
            return;
        }
        int drawX = x;
        int drawY = y - bitmap.getHeight();
        // 画怪物动画帧的位图
        Graphics.drawMatrixImage(canvas, bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), Graphics.TRANS_NONE, drawX, drawY, 0,
                Graphics.TIMES_SCALE);
        startX = drawX;
        startY = drawY;
        endX = startX + bitmap.getWidth();
        endY = startY + bitmap.getHeight();
        drawIndex++;

    }
    public void updateShift(int shift)
    {
        x -= shift;
        startX -= shift;
        endX -= shift;
    }

    public void showButton() {

    }

}
