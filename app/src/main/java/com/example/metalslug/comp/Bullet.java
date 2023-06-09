package com.example.metalslug.comp;


import android.graphics.Bitmap;

import com.example.metalslug.ViewManager;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com<br>
 * @version 1.0
 */
public class Bullet  // 子弹类
{
	// 定义代表子弹类型的常量（如果程序还需要增加更多子弹，只需在此处添加常量即可）
	public static final int BULLET_TYPE_1 = 1;
	public static final int BULLET_TYPE_2 = 2;
	public static final int BULLET_TYPE_3 = 3;
	public static final int BULLET_TYPE_4 = 4;
	public static final int BULLET_TYPE_5 = 5;
	//定义子弹是否已经打出
	public boolean isShoot = false;
	// 定义子弹的类型
	private int type;
	//定义子弹是否暴击
	public boolean isCrit = false;
	//定义子弹的暴击倍数
	public double critTimes = 0;
	//定义子弹的伤害
	public double damage = 0;
	// 子弹的x、y坐标
	private int x;
	private int y;
	// 定义子弹的射击方向
	private int dir;
	// 定义子弹在y方向上的加速度
	private int yAccelate = 0;
	// 子弹是否有效
	private boolean isEffect = true;
	// 定义子弹的构造器
	public Bullet(int type, int x, int y, int dir)
	{
		this.type = type;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	//获取子弹的暴击倍数
	public double getCritTimes()
	{
		return critTimes;
	}
	//设置子弹的暴击倍数
	public void setCritTimes(double critTimes)
	{
		this.critTimes = critTimes;
	}
	//获取子弹的伤害
	public double getDamage()
	{
		return damage;
	}
	//设置子弹的伤害
	public void setDamage(double damage)
	{
		this.damage = damage;
	}
	//定义子弹类型
	public int getType()
	{
		return type;
	}
	//定义子弹类型
	public void setType(int type)
	{
		this.type = type;
	}

	// 根据子弹类型获取子弹对应的图片
	public Bitmap getBitmap()
	{
		switch (type)
		{
			case BULLET_TYPE_1:
				return ViewManager.bulletImage[0];
			case BULLET_TYPE_2:
				return ViewManager.bulletImage[1];
			case BULLET_TYPE_3:
				return ViewManager.bulletImage[2];
			case BULLET_TYPE_4:
				return ViewManager.bulletImage[3];
				case BULLET_TYPE_5:
				return ViewManager.bulletImage[4];
			default:
				return null;
		}
	}
	//获取是否暴击
	public boolean getIsCrit()
	{
		return isCrit;
	}
	//设置是否暴击
	public void setIsCrit(boolean isCrit)
	{
		this.isCrit = isCrit;
	}
	//获取是否开火
	public boolean getIsShoot()
	{
		return isShoot;
	}
	//设置是否开火
	public void setIsShoot(boolean isShoot)
	{
		this.isShoot = isShoot;
	}
	// 根据子弹类型来计算子弹在X方向上的速度
	public int getSpeedX()
	{
		// 根据玩家的方向来计算子弹方向和移动方向
		int sign = dir == Player.DIR_RIGHT ? 1 : -1;
		switch (type)
		{
			// 对于第1种子弹，以12为基数来计算它的速度
			case BULLET_TYPE_1:
				return (int) (ViewManager.scale * 12) * sign;
			// 对于第2种子弹，以8为基数来计算它的速度
			case BULLET_TYPE_2:
				return (int) (ViewManager.scale * 8) * sign;
			// 对于第3种子弹，以8为基数来计算它的速度
			case BULLET_TYPE_3:
				return (int) (ViewManager.scale * 8) * sign;
			// 对于第4种子弹，以8为基数来计算它的速度
			case BULLET_TYPE_4:
				return (int) (ViewManager.scale * 8) * sign;
			// 对于第5种子弹，以12为基数来计算它的速度
			case BULLET_TYPE_5:
				return (int) (ViewManager.scale * 12) * sign;
			default:
				return (int) (ViewManager.scale * 8) * sign;
		}
	}

	// 根据子弹类型来计算子弹在Y方向上的速度
	public int getSpeedY()
	{
		// 如果yAccelate不为0，则以yAccelate作为Y方向上的速度
		if (yAccelate != 0)
		{
			return yAccelate;
		}
		// 此处控制只有第3种子弹才有Y方向的速度（子弹会斜着向下移动）
		switch (type)
		{
			case BULLET_TYPE_1:
				return 0;
			case BULLET_TYPE_2:
				return 0;
			case BULLET_TYPE_3:
				return (int) (ViewManager.scale * 6);
			case BULLET_TYPE_4:
				return 0;
			default:
				return 0;
		}
	}

	// 定义控制子弹移动的方法
	public void move()
	{
		x += getSpeedX();
		y += getSpeedY();
		//y += 0;
	}

	// 下面是各成员变量的setter、getter方法
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getDir()
	{
		return dir;
	}

	public void setDir(int dir)
	{
		this.dir = dir;
	}

	public boolean isEffect()
	{
		return isEffect;
	}

	public void setEffect(boolean isEffect)
	{
		this.isEffect = isEffect;
	}

	public int getyAccelate()
	{
		return yAccelate;
	}

	public void setyAccelate(int yAccelate)
	{
		this.yAccelate = yAccelate;
	}
}
