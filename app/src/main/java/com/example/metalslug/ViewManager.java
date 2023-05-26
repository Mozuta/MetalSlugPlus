package com.example.metalslug;


import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.SparseIntArray;
import android.widget.ImageView;

import com.example.game.Graphics;
import com.example.metalslug.comp.MonsterManager;
import com.example.metalslug.comp.NpcManager;

/**
 * Description: 管理图片加载和图片绘制的工具类<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com<br>
 * @version 1.0
 */
public class ViewManager
{
	// 定义一个SoundPool
	public static SoundPool soundPool;
	public static SparseIntArray soundMap = new SparseIntArray();
	// 地图图片
	public static Bitmap map = null;
	public static Bitmap map2 = null;
	public static Bitmap map3 = null;

	// 保存角色站立时脚部动画帧的图片数组
	public static Bitmap[] legStandImage = null;
	// 保存角色站立时头部动画帧的图片数组
	public static Bitmap[] headStandImage = null;
	// 保存角色跑动时腿部动画帧的图片数组
	public static Bitmap[] legRunImage = null;
	// 保存角色跑动时头部动画帧的图片数组
	public static Bitmap[] headRunImage = null;
	// 保存角色跳动时腿部动画帧的图片数组
	public static Bitmap[] legJumpImage = null;
	// 保存角色跳动时头部动画帧的图片数组
	public static Bitmap[] headJumpImage = null;
	// 保存角色射击时头部动画帧的图片数组
	public static Bitmap[] headShootImage = null;
	// 加载所有子弹的图片
	public static Bitmap[] bulletImage = null;
	// 加载所有npc的gif动画
	public static Bitmap[] npcImage = null;
	// 绘制角色的图片
	public static Bitmap head = null;
	// 保存第一种怪物（炸弹）未爆炸时动画帧的图片
	public static Bitmap[] bombImage = null;
	// 保存第一种怪物（炸弹）爆炸时动画帧的图片
	public static Bitmap[] bomb2Image = null;
	// 保存第二种怪物（飞机）的动画帧的图片
	public static Bitmap[] flyImage = null;
	// 保存第二种怪物（飞机）爆炸的动画帧的图片
	public static Bitmap[] flyDieImage = null;
	// 保存第三种怪物（人）的动画帧的图片
	public static Bitmap[] manImgae = null;
	// 保存第三种怪物（人）的死亡时动画帧的图片
	public static Bitmap[] manDieImage = null;
	public static Bitmap[] oldMan = null;
	public static Bitmap[] youngWoman = null;
	public static Bitmap[] miner = null;
	public static Bitmap[] woundSolder = null;
	public static Bitmap[] smoker = null;

	// 定义游戏对图片的缩放比例

	public static float scale = 1f;
	// 定义背包按钮
	public static Bitmap backpack = null;
	public static Bitmap profile = null;

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;

	public static ImageView dialogButton;

	public static Context context;

	// 获取屏幕初始宽度、高度的方法
	public static void initScreen(int width, int height)
	{
		SCREEN_WIDTH = (short) width;
		SCREEN_HEIGHT = (short) height;
	}

	// 清除屏幕的方法
	public static void clearScreen(Canvas c, int color)
	{
		color = 0xff000000 | color;
		c.drawColor(color);
	}

	// 清除屏幕的方法
	public static void clearScreen(Canvas c)
	{
		c.drawColor(Color.BLACK);
	}
	// 加载所有游戏图片、声音的方法
	public static void loadResource()
	{
		context = MainActivity.mainActivity;
		AudioAttributes attr = new AudioAttributes.Builder()
				// 设置音效使用场景
				.setUsage(AudioAttributes.USAGE_GAME)
				// 设置音效的类型
				.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
				.build();
		soundPool = new SoundPool.Builder()
				// 设置音效池的属性
				.setAudioAttributes(attr)
				.setMaxStreams(10) // 设置最多可容纳10个音频流
				.build();
		// load方法加载指定音频文件，并返回所加载的音频ID
		// 此处使用HashMap来管理这些音频流
		soundMap.put(1, soundPool.load(MainActivity.mainActivity, R.raw.shot, 1));
		soundMap.put(2, soundPool.load(MainActivity.mainActivity, R.raw.bomb, 1));
		soundMap.put(3, soundPool.load(MainActivity.mainActivity, R.raw.oh, 1));

		Bitmap temp = createBitmapByID(MainActivity.res, R.drawable.map);
		Bitmap temp2 = createBitmapByID(MainActivity.res, R.drawable.map2);
		Bitmap temp3 = createBitmapByID(MainActivity.res, R.drawable.map3);
		if (temp != null && !temp.isRecycled())
		{
			int height = temp.getHeight();
			if (height != SCREEN_HEIGHT && SCREEN_HEIGHT != 0)
			{
				scale = (float) SCREEN_HEIGHT / (float) height;
				map = Graphics.scale(temp, temp.getWidth() * scale, height * scale);
				temp.recycle();
			}
			else
			{
				map = temp;
			}
		}
		if (temp2 != null && !temp2.isRecycled())
		{
			int height = temp2.getHeight();
			if (height != SCREEN_HEIGHT && SCREEN_HEIGHT != 0)
			{
				scale = (float) SCREEN_HEIGHT / (float) height;
				map2 = Graphics.scale(temp2, temp2.getWidth() * scale, height * scale);
				temp2.recycle();
			}
			else
			{
				map2 = temp2;
			}
		}
		if (temp3 != null && !temp3.isRecycled())
		{
			int height = temp3.getHeight();
			if (height != SCREEN_HEIGHT && SCREEN_HEIGHT != 0)
			{
				scale = (float) SCREEN_HEIGHT / (float) height;
				map3 = Graphics.scale(temp3, temp3.getWidth() * scale, height * scale);
				temp3.recycle();
			}
			else
			{
				map3 = temp3;
			}
		}
		// R.drawable.dialog_button
		dialogButton = new ImageView(context);
		dialogButton.setImageResource(R.drawable.dialog_button);
		dialogButton.setScaleType(ImageView.ScaleType.FIT_XY);
		// 加载角色站立时腿部动画帧的图片
		legStandImage = new Bitmap[1];
		legStandImage[0] = createBitmapByID(MainActivity.res, R.drawable.leg_stand, scale);
		// 加载角色站立时头部动画帧的图片
		headStandImage = new Bitmap[3];
		headStandImage[0] = createBitmapByID(MainActivity.res, R.drawable.head_stand_1, scale);
		headStandImage[1] = createBitmapByID(MainActivity.res, R.drawable.head_stand_2, scale);
		headStandImage[2] = createBitmapByID(MainActivity.res, R.drawable.head_stand_3, scale);
		// 加载角色跑动时腿部动画帧的图片
		legRunImage = new Bitmap[3];
		legRunImage[0] = createBitmapByID(MainActivity.res, R.drawable.leg_run_1, scale);
		legRunImage[1] = createBitmapByID(MainActivity.res, R.drawable.leg_run_2, scale);
		legRunImage[2] = createBitmapByID(MainActivity.res, R.drawable.leg_run_3, scale);
		// 加载角色跑动时头部动画帧的图片
		headRunImage = new Bitmap[3];
		headRunImage[0] = createBitmapByID(MainActivity.res, R.drawable.head_run_1, scale);
		headRunImage[1] = createBitmapByID(MainActivity.res, R.drawable.head_run_2, scale);
		headRunImage[2] = createBitmapByID(MainActivity.res, R.drawable.head_run_3, scale);

		// 加载角色跳跃时腿部动画帧的图片
		legJumpImage = new Bitmap[5];
		legJumpImage[0] = createBitmapByID(MainActivity.res, R.drawable.leg_jum_1, scale);
		legJumpImage[1] = createBitmapByID(MainActivity.res, R.drawable.leg_jum_2, scale);
		legJumpImage[2] = createBitmapByID(MainActivity.res, R.drawable.leg_jum_3, scale);
		legJumpImage[3] = createBitmapByID(MainActivity.res, R.drawable.leg_jum_4, scale);
		legJumpImage[4] = createBitmapByID(MainActivity.res, R.drawable.leg_jum_5, scale);
		// 加载角色跳跃时头部动画帧的图片
		headJumpImage = new Bitmap[5];
		headJumpImage[0] = createBitmapByID(MainActivity.res, R.drawable.head_jump_1, scale);
		headJumpImage[1] = createBitmapByID(MainActivity.res, R.drawable.head_jump_2, scale);
		headJumpImage[2] = createBitmapByID(MainActivity.res, R.drawable.head_jump_3, scale);
		headJumpImage[3] = createBitmapByID(MainActivity.res, R.drawable.head_jump_4, scale);
		headJumpImage[4] = createBitmapByID(MainActivity.res, R.drawable.head_jump_5, scale);
		// 加载角色射击时头部动画帧的图片
		headShootImage = new Bitmap[6];
		headShootImage[0] = createBitmapByID(MainActivity.res, R.drawable.head_shoot_1, scale);
		headShootImage[1] = createBitmapByID(MainActivity.res, R.drawable.head_shoot_2, scale);
		headShootImage[2] = createBitmapByID(MainActivity.res, R.drawable.head_shoot_3, scale);
		headShootImage[3] = createBitmapByID(MainActivity.res, R.drawable.head_shoot_4, scale);
		headShootImage[4] = createBitmapByID(MainActivity.res, R.drawable.head_shoot_5, scale);
		headShootImage[5] = createBitmapByID(MainActivity.res, R.drawable.head_shoot_6, scale);
		// 加载子弹的图片
		bulletImage = new Bitmap[4];
		bulletImage[0] = createBitmapByID(MainActivity.res, R.drawable.bullet_1, scale);
		bulletImage[1] = createBitmapByID(MainActivity.res, R.drawable.bullet_2, scale);
		bulletImage[2] = createBitmapByID(MainActivity.res, R.drawable.bullet_3, scale);
		bulletImage[3] = createBitmapByID(MainActivity.res, R.drawable.bullet_4, scale);
		head = createBitmapByID(MainActivity.res, R.drawable.head, scale);
		// 加载第一种怪物（炸弹）未爆炸时动画帧的图片
		bombImage = new Bitmap[2];
		bombImage[0] = createBitmapByID(MainActivity.res, R.drawable.bomb_1, scale);
		bombImage[1] = createBitmapByID(MainActivity.res, R.drawable.bomb_2, scale);
		// 加载第一种怪物（炸弹）爆炸时的图片
		bomb2Image = new Bitmap[13];
		bomb2Image[0] = createBitmapByID(MainActivity.res, R.drawable.bomb2_1, scale);
		bomb2Image[1] = createBitmapByID(MainActivity.res, R.drawable.bomb2_2, scale);
		bomb2Image[2] = createBitmapByID(MainActivity.res, R.drawable.bomb2_3, scale);
		bomb2Image[3] = createBitmapByID(MainActivity.res, R.drawable.bomb2_4, scale);
		bomb2Image[4] = createBitmapByID(MainActivity.res, R.drawable.bomb2_5, scale);
		bomb2Image[5] = createBitmapByID(MainActivity.res, R.drawable.bomb2_6, scale);
		bomb2Image[6] = createBitmapByID(MainActivity.res, R.drawable.bomb2_7, scale);
		bomb2Image[7] = createBitmapByID(MainActivity.res, R.drawable.bomb2_8, scale);
		bomb2Image[8] = createBitmapByID(MainActivity.res, R.drawable.bomb2_9, scale);
		bomb2Image[9] = createBitmapByID(MainActivity.res, R.drawable.bomb2_10, scale);
		bomb2Image[10] = createBitmapByID(MainActivity.res, R.drawable.bomb2_11, scale);
		bomb2Image[11] = createBitmapByID(MainActivity.res, R.drawable.bomb2_12, scale);
		bomb2Image[12] = createBitmapByID(MainActivity.res, R.drawable.bomb2_13, scale);
		// 加载第二种怪物（飞机）的动画帧的图片
		flyImage = new Bitmap[6];
		flyImage[0] = createBitmapByID(MainActivity.res, R.drawable.fly_1, scale);
		flyImage[1] = createBitmapByID(MainActivity.res, R.drawable.fly_2, scale);
		flyImage[2] = createBitmapByID(MainActivity.res, R.drawable.fly_3, scale);
		flyImage[3] = createBitmapByID(MainActivity.res, R.drawable.fly_4, scale);
		flyImage[4] = createBitmapByID(MainActivity.res, R.drawable.fly_5, scale);
		flyImage[5] = createBitmapByID(MainActivity.res, R.drawable.fly_6, scale);
		// 加载第二种怪物（飞机）爆炸时的动画帧的图片
		flyDieImage = new Bitmap[13];
		flyDieImage[0] = createBitmapByID(MainActivity.res, R.drawable.fly_die_1, scale);
		flyDieImage[1] = createBitmapByID(MainActivity.res, R.drawable.fly_die_2, scale);
		flyDieImage[2] = createBitmapByID(MainActivity.res, R.drawable.fly_die_3, scale);
		flyDieImage[3] = createBitmapByID(MainActivity.res, R.drawable.fly_die_4, scale);
		flyDieImage[4] = createBitmapByID(MainActivity.res, R.drawable.fly_die_5, scale);
		flyDieImage[5] = createBitmapByID(MainActivity.res, R.drawable.fly_die_6, scale);
		flyDieImage[6] = createBitmapByID(MainActivity.res, R.drawable.fly_die_7, scale);
		flyDieImage[7] = createBitmapByID(MainActivity.res, R.drawable.fly_die_8, scale);
		flyDieImage[8] = createBitmapByID(MainActivity.res, R.drawable.fly_die_9, scale);
		flyDieImage[9] = createBitmapByID(MainActivity.res, R.drawable.fly_die_10, scale);
		// 加载第三种怪物（人）活着时的动画帧的图片
		manImgae = new Bitmap[3];
		manImgae[0] = createBitmapByID(MainActivity.res, R.drawable.man_1, scale);
		manImgae[1] = createBitmapByID(MainActivity.res, R.drawable.man_2, scale);
		manImgae[2] = createBitmapByID(MainActivity.res, R.drawable.man_3, scale);
		// 加载第三种怪物（人）死亡时的动画帧的图片
		manDieImage = new Bitmap[13];
		manDieImage[0] = createBitmapByID(MainActivity.res, R.drawable.man_die_1, scale);
		manDieImage[1] = createBitmapByID(MainActivity.res, R.drawable.man_die_2, scale);
		manDieImage[2] = createBitmapByID(MainActivity.res, R.drawable.man_die_3, scale);
		manDieImage[3] = createBitmapByID(MainActivity.res, R.drawable.man_die_4, scale);
		manDieImage[4] = createBitmapByID(MainActivity.res, R.drawable.man_die_5, scale);

		// npc1
		oldMan = new Bitmap[18];
		oldMan[0] = createBitmapByID(MainActivity.res, R.raw.npc1_1, scale);
		oldMan[1] = createBitmapByID(MainActivity.res, R.raw.npc1_2, scale);
		oldMan[2] = createBitmapByID(MainActivity.res, R.raw.npc1_3, scale);
		oldMan[3] = createBitmapByID(MainActivity.res, R.raw.npc1_4, scale);
		oldMan[4] = createBitmapByID(MainActivity.res, R.raw.npc1_5, scale);
		oldMan[5] = createBitmapByID(MainActivity.res, R.raw.npc1_6, scale);
		oldMan[6] = createBitmapByID(MainActivity.res, R.raw.npc1_7, scale);
		oldMan[7] = createBitmapByID(MainActivity.res, R.raw.npc1_8, scale);
		oldMan[8] = createBitmapByID(MainActivity.res, R.raw.npc1_9, scale);
		oldMan[9] = createBitmapByID(MainActivity.res, R.raw.npc1_10, scale);
		oldMan[10] = createBitmapByID(MainActivity.res, R.raw.npc1_11, scale);
		oldMan[11] = createBitmapByID(MainActivity.res, R.raw.npc1_12, scale);
		oldMan[12] = createBitmapByID(MainActivity.res, R.raw.npc1_13, scale);
		oldMan[13] = createBitmapByID(MainActivity.res, R.raw.npc1_14, scale);
		oldMan[14] = createBitmapByID(MainActivity.res, R.raw.npc1_15, scale);
		oldMan[15] = createBitmapByID(MainActivity.res, R.raw.npc1_16, scale);
		oldMan[16] = createBitmapByID(MainActivity.res, R.raw.npc1_17, scale);
		oldMan[17] = createBitmapByID(MainActivity.res, R.raw.npc1_18, scale);



		// youngWoman 4 frame, miner 67 frame, woundSoldier 10 frame, smoker 35 frame
		youngWoman = new Bitmap[4];
		youngWoman[0] = createBitmapByID(MainActivity.res, R.raw.npc2_1, scale);
		youngWoman[1] = createBitmapByID(MainActivity.res, R.raw.npc2_2, scale);
		youngWoman[2] = createBitmapByID(MainActivity.res, R.raw.npc2_3, scale);
		youngWoman[3] = createBitmapByID(MainActivity.res, R.raw.npc2_4, scale);

		miner = new Bitmap[67];
		miner[0] = createBitmapByID(MainActivity.res, R.raw.npc3_1, scale);
		miner[1] = createBitmapByID(MainActivity.res, R.raw.npc3_2, scale);
		miner[2] = createBitmapByID(MainActivity.res, R.raw.npc3_3, scale);
		miner[3] = createBitmapByID(MainActivity.res, R.raw.npc3_4, scale);
		miner[4] = createBitmapByID(MainActivity.res, R.raw.npc3_5, scale);
		miner[5] = createBitmapByID(MainActivity.res, R.raw.npc3_6, scale);
		miner[6] = createBitmapByID(MainActivity.res, R.raw.npc3_7, scale);
		miner[7] = createBitmapByID(MainActivity.res, R.raw.npc3_8, scale);
		miner[8] = createBitmapByID(MainActivity.res, R.raw.npc3_9, scale);
		miner[9] = createBitmapByID(MainActivity.res, R.raw.npc3_10, scale);
		miner[10] = createBitmapByID(MainActivity.res, R.raw.npc3_11, scale);
		miner[11] = createBitmapByID(MainActivity.res, R.raw.npc3_12, scale);
		miner[12] = createBitmapByID(MainActivity.res, R.raw.npc3_13, scale);
		miner[13] = createBitmapByID(MainActivity.res, R.raw.npc3_14, scale);
		miner[14] = createBitmapByID(MainActivity.res, R.raw.npc3_15, scale);
		miner[15] = createBitmapByID(MainActivity.res, R.raw.npc3_16, scale);
		miner[16] = createBitmapByID(MainActivity.res, R.raw.npc3_17, scale);
		miner[17] = createBitmapByID(MainActivity.res, R.raw.npc3_18, scale);
		miner[18] = createBitmapByID(MainActivity.res, R.raw.npc3_19, scale);
		miner[19] = createBitmapByID(MainActivity.res, R.raw.npc3_20, scale);
		miner[20] = createBitmapByID(MainActivity.res, R.raw.npc3_21, scale);
		miner[21] = createBitmapByID(MainActivity.res, R.raw.npc3_22, scale);
		miner[22] = createBitmapByID(MainActivity.res, R.raw.npc3_23, scale);
		miner[23] = createBitmapByID(MainActivity.res, R.raw.npc3_24, scale);
		miner[24] = createBitmapByID(MainActivity.res, R.raw.npc3_25, scale);
		miner[25] = createBitmapByID(MainActivity.res, R.raw.npc3_26, scale);
		miner[26] = createBitmapByID(MainActivity.res, R.raw.npc3_27, scale);
		miner[27] = createBitmapByID(MainActivity.res, R.raw.npc3_28, scale);
		miner[28] = createBitmapByID(MainActivity.res, R.raw.npc3_29, scale);
		miner[29] = createBitmapByID(MainActivity.res, R.raw.npc3_30, scale);
		miner[30] = createBitmapByID(MainActivity.res, R.raw.npc3_31, scale);
		miner[31] = createBitmapByID(MainActivity.res, R.raw.npc3_32, scale);
		miner[32] = createBitmapByID(MainActivity.res, R.raw.npc3_33, scale);
		miner[33] = createBitmapByID(MainActivity.res, R.raw.npc3_34, scale);
		miner[34] = createBitmapByID(MainActivity.res, R.raw.npc3_35, scale);
		miner[35] = createBitmapByID(MainActivity.res, R.raw.npc3_36, scale);
		miner[36] = createBitmapByID(MainActivity.res, R.raw.npc3_37, scale);
		miner[37] = createBitmapByID(MainActivity.res, R.raw.npc3_38, scale);
		miner[38] = createBitmapByID(MainActivity.res, R.raw.npc3_39, scale);
		miner[39] = createBitmapByID(MainActivity.res, R.raw.npc3_40, scale);
		miner[40] = createBitmapByID(MainActivity.res, R.raw.npc3_41, scale);
		miner[41] = createBitmapByID(MainActivity.res, R.raw.npc3_42, scale);
		miner[42] = createBitmapByID(MainActivity.res, R.raw.npc3_43, scale);
		miner[43] = createBitmapByID(MainActivity.res, R.raw.npc3_44, scale);
		miner[44] = createBitmapByID(MainActivity.res, R.raw.npc3_45, scale);
		miner[45] = createBitmapByID(MainActivity.res, R.raw.npc3_46, scale);
		miner[46] = createBitmapByID(MainActivity.res, R.raw.npc3_47, scale);
		miner[47] = createBitmapByID(MainActivity.res, R.raw.npc3_48, scale);
		miner[48] = createBitmapByID(MainActivity.res, R.raw.npc3_49, scale);
		miner[49] = createBitmapByID(MainActivity.res, R.raw.npc3_50, scale);
		miner[50] = createBitmapByID(MainActivity.res, R.raw.npc3_51, scale);
		miner[51] = createBitmapByID(MainActivity.res, R.raw.npc3_52, scale);
		miner[52] = createBitmapByID(MainActivity.res, R.raw.npc3_53, scale);
		miner[53] = createBitmapByID(MainActivity.res, R.raw.npc3_54, scale);
		miner[54] = createBitmapByID(MainActivity.res, R.raw.npc3_55, scale);
		miner[55] = createBitmapByID(MainActivity.res, R.raw.npc3_56, scale);
		miner[56] = createBitmapByID(MainActivity.res, R.raw.npc3_57, scale);
		miner[57] = createBitmapByID(MainActivity.res, R.raw.npc3_58, scale);
		miner[58] = createBitmapByID(MainActivity.res, R.raw.npc3_59, scale);
		miner[59] = createBitmapByID(MainActivity.res, R.raw.npc3_60, scale);
		miner[60] = createBitmapByID(MainActivity.res, R.raw.npc3_61, scale);
		miner[61] = createBitmapByID(MainActivity.res, R.raw.npc3_62, scale);
		miner[62] = createBitmapByID(MainActivity.res, R.raw.npc3_63, scale);
		miner[63] = createBitmapByID(MainActivity.res, R.raw.npc3_64, scale);
		miner[64] = createBitmapByID(MainActivity.res, R.raw.npc3_65, scale);
		miner[65] = createBitmapByID(MainActivity.res, R.raw.npc3_66, scale);
		miner[66] = createBitmapByID(MainActivity.res, R.raw.npc3_67, scale);

		woundSolder = new Bitmap[10];
		woundSolder[0] = createBitmapByID(MainActivity.res, R.raw.npc4_1, scale);
		woundSolder[1] = createBitmapByID(MainActivity.res, R.raw.npc4_2, scale);
		woundSolder[2] = createBitmapByID(MainActivity.res, R.raw.npc4_3, scale);
		woundSolder[3] = createBitmapByID(MainActivity.res, R.raw.npc4_4, scale);
		woundSolder[4] = createBitmapByID(MainActivity.res, R.raw.npc4_5, scale);
		woundSolder[5] = createBitmapByID(MainActivity.res, R.raw.npc4_6, scale);
		woundSolder[6] = createBitmapByID(MainActivity.res, R.raw.npc4_7, scale);
		woundSolder[7] = createBitmapByID(MainActivity.res, R.raw.npc4_8, scale);
		woundSolder[8] = createBitmapByID(MainActivity.res, R.raw.npc4_9, scale);
		woundSolder[9] = createBitmapByID(MainActivity.res, R.raw.npc4_10, scale);

		smoker = new Bitmap[35];
		smoker[0] = createBitmapByID(MainActivity.res, R.raw.npc5_1, scale);
		smoker[1] = createBitmapByID(MainActivity.res, R.raw.npc5_2, scale);
		smoker[2] = createBitmapByID(MainActivity.res, R.raw.npc5_3, scale);
		smoker[3] = createBitmapByID(MainActivity.res, R.raw.npc5_4, scale);
		smoker[4] = createBitmapByID(MainActivity.res, R.raw.npc5_5, scale);
		smoker[5] = createBitmapByID(MainActivity.res, R.raw.npc5_6, scale);
		smoker[6] = createBitmapByID(MainActivity.res, R.raw.npc5_7, scale);
		smoker[7] = createBitmapByID(MainActivity.res, R.raw.npc5_8, scale);
		smoker[8] = createBitmapByID(MainActivity.res, R.raw.npc5_9, scale);
		smoker[9] = createBitmapByID(MainActivity.res, R.raw.npc5_10, scale);
		smoker[10] = createBitmapByID(MainActivity.res, R.raw.npc5_11, scale);
		smoker[11] = createBitmapByID(MainActivity.res, R.raw.npc5_12, scale);
		smoker[12] = createBitmapByID(MainActivity.res, R.raw.npc5_13, scale);
		smoker[13] = createBitmapByID(MainActivity.res, R.raw.npc5_14, scale);
		smoker[14] = createBitmapByID(MainActivity.res, R.raw.npc5_15, scale);
		smoker[15] = createBitmapByID(MainActivity.res, R.raw.npc5_16, scale);
		smoker[16] = createBitmapByID(MainActivity.res, R.raw.npc5_17, scale);
		smoker[17] = createBitmapByID(MainActivity.res, R.raw.npc5_18, scale);
		smoker[18] = createBitmapByID(MainActivity.res, R.raw.npc5_19, scale);
		smoker[19] = createBitmapByID(MainActivity.res, R.raw.npc5_20, scale);
		smoker[20] = createBitmapByID(MainActivity.res, R.raw.npc5_21, scale);
		smoker[21] = createBitmapByID(MainActivity.res, R.raw.npc5_22, scale);
		smoker[22] = createBitmapByID(MainActivity.res, R.raw.npc5_23, scale);
		smoker[23] = createBitmapByID(MainActivity.res, R.raw.npc5_24, scale);
		smoker[24] = createBitmapByID(MainActivity.res, R.raw.npc5_25, scale);
		smoker[25] = createBitmapByID(MainActivity.res, R.raw.npc5_26, scale);
		smoker[26] = createBitmapByID(MainActivity.res, R.raw.npc5_27, scale);
		smoker[27] = createBitmapByID(MainActivity.res, R.raw.npc5_28, scale);
		smoker[28] = createBitmapByID(MainActivity.res, R.raw.npc5_29, scale);
		smoker[29] = createBitmapByID(MainActivity.res, R.raw.npc5_30, scale);
		smoker[30] = createBitmapByID(MainActivity.res, R.raw.npc5_31, scale);
		smoker[31] = createBitmapByID(MainActivity.res, R.raw.npc5_32, scale);
		smoker[32] = createBitmapByID(MainActivity.res, R.raw.npc5_33, scale);
		smoker[33] = createBitmapByID(MainActivity.res, R.raw.npc5_34, scale);
		smoker[34] = createBitmapByID(MainActivity.res, R.raw.npc5_35, scale);




		backpack = createBitmapByID(MainActivity.res, R.drawable.backpack, scale);
		profile = createBitmapByID(MainActivity.res, R.drawable.profile, scale);



	}
// 绘制游戏界面的方法，该方法先绘制游戏背景地图，再绘制游戏角色，最后绘制所有怪物
public static void drawGame(Canvas canvas, int mapNum)
{
	if (canvas == null)
	{
		return;
	}
	// draw the corresponding map
	Bitmap currentmap = null;
	switch (mapNum) {
		case 1:
			currentmap = map;
			break;
		case 2:
			currentmap = map2;
			break;
		case 3:
			currentmap = map3;
			break;
	}
		if (currentmap != null && !currentmap.isRecycled())
		{
			int width = currentmap.getWidth() + GameView.player.getShift();
			// 绘制map图片，也就是绘制地图
			Graphics.drawImage(canvas, currentmap, 0, 0, -GameView.player.getShift()
					, 0 ,width, currentmap.getHeight());
			int totalWidth = width;
			// 采用循环，保证地图前后可以拼接起来
			while (totalWidth < ViewManager.SCREEN_WIDTH)
			{
				int mapWidth = currentmap.getWidth();
				int drawWidth = ViewManager.SCREEN_WIDTH - totalWidth;
				if (mapWidth < drawWidth)
				{
					drawWidth = mapWidth;
				}
				Graphics.drawImage(canvas, currentmap, totalWidth, 0, 0, 0,
						drawWidth, currentmap.getHeight());
				totalWidth += drawWidth;
			}
		}
	// 画角色
	GameView.player.draw(canvas);
	// 画怪物
	MonsterManager.drawMonster(canvas);
	// 画npc
	NpcManager.drawNPC(canvas);

}

	// 工具方法：根据图片id来获取实际的位图
	private static Bitmap createBitmapByID(Resources res, int resID)
	{
		try
		{
			InputStream is = res.openRawResource(resID);
			return BitmapFactory.decodeStream(is, null, null);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	// 工具方法：根据图片id来获取实际的位图，并按scale进行缩放
	private static Bitmap createBitmapByID(Resources res, int resID, float scale)
	{
		try
		{
			InputStream is = res.openRawResource(resID);
			Bitmap bitmap = BitmapFactory.decodeStream(is, null, null);
			if (bitmap == null || bitmap.isRecycled())
			{
				return null;
			}
			if (scale <= 0 || scale == 1f)
			{
				return bitmap;
			}
			int wdith = (int) (bitmap.getWidth() * scale);
			int height = (int) (bitmap.getHeight() * scale);
			Bitmap newBitmap = Graphics.scale(bitmap, wdith, height);
			if (!bitmap.isRecycled() && !bitmap.equals(newBitmap))
			{
				bitmap.recycle();
			}
			return newBitmap;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	// 工具方法：根据图片的文件名来获取实际的位图，
	private static Bitmap createBitmapByFile(String pathName)
	{
		try
		{
			InputStream fin = MainActivity.mainActivity.getAssets().open(pathName);
			return BitmapFactory.decodeStream(fin, null, null);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
