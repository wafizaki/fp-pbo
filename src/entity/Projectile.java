package entity;

import main.GamePanel;

public class Projectile extends Entity {
	Entity user;

	public Projectile(GamePanel gp) {
		super(gp);
	}

	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}

	public void update() {

		if (user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if (monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack);
				alive = false;
			}
		}
		if (user != gp.player) {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if (gp.player.invincible == false && contactPlayer == true) {
				damagePlayer(attack);
				alive = false;
			}
		}
		switch (direction) {
		case "up":
			worldY -= speed;
			break;
		case "down":
			worldY += speed;
			break;
		case "right":
			worldX += speed;
			break;
		case "left":
			worldX -= speed;
			break;
		}
		life--;
		if (life <= 0) {
			alive = false;
		}

		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNumber == 1) {
				spriteNumber = 2;
			} else if (spriteNumber == 2) {
				spriteNumber = 3;
			} else if (spriteNumber == 3) {
				spriteNumber = 1;
			}
			spriteCounter = 0;
		}
	}
}
