package com.example.metalslug.comp;
import android.content.Context;
import android.graphics.Canvas;

import com.example.metalslug.GameView;

import java.util.ArrayList;
import java.util.List;

public class NpcManager {
    private static final List<Npc> npcList = new ArrayList<>();
    private static final float MIN_DISTANCE = 30.0f; // 最小距离为50像素

    private static int dialog_flag = 0;
    // Method to draw all the NPCs in the game
    public static void drawNPC(Canvas canvas) {
        Npc npc = null;
        // draw the NPCs in the list
        for (int i = 0; i < npcList.size(); i++) {
            npc = npcList.get(i);
            if (npc == null) {
                continue;
            }
            npc.draw(canvas);
        }


    }
    // Method to generate and add a new NPC to the game
    public static void generateNpc() {
        if (npcList.size() < 3 + Util.rand(3)) {
            // Create a new NPC
            Npc npc = new Npc(1 + Util.rand(3));
            npcList.add(npc);
        }
    }

    // Method to update the position of the NPC
    public static void updatePosition(int shift) {
        Npc npc = null;
        // Define a collection to save all the NPCs that will be deleted
        List<Npc> delList = new ArrayList<>();
        // Traverse the NPC collection
        for (int i = 0; i < npcList.size(); i++) {
            npc = npcList.get(i);
            if (npc == null) {
                continue;
            }
            // Update the position of the NPC
            npc.updateShift(shift);
            // If the X coordinate of the NPC is out of bounds, add the NPC to the delList collection
            if (npc.getPositionX() < 0) {
                delList.add(npc);
            }
        }
        // Delete all NPCs in the delList collection
        npcList.removeAll(delList);
    }
    // check whether the player is neer the NPC

    public static void checkPlayerNearNpc() {
        Npc npc = null;
        // Traverse the NPC collection
        for (int i = 0; i < npcList.size(); i++) {
            npc = npcList.get(i);
            if (npc == null) {
                continue;
            }
            // If the player is near the NPC, the NPC will talk to the player
            if (Math.abs(npc.getPositionX() - GameView.player.getX()) < MIN_DISTANCE) {
                //show the dialog button,if click the button, the NPC will talk to the player
                if (dialog_flag == 0)
                {
                    GameView.addDialogButtonView();
                }
                dialog_flag = 1;
            }
            else
            {
                //hide the dialog button
               // GameView.removeDialogButtonView();
                dialog_flag = 0;
            }
        }
    }
}


