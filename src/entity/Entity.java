package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
	public BufferedImage attackUp, attackUp1, attackUp2, attackRight, attackRight1, attackRight2, attackLeft,
			attackLeft1, attackLeft2, attackDown, attackDown1, attackDown2;
	public BufferedImage image, image1, image2;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	String dialogue[] = new String[20];

//	STATE
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNumber = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	boolean hpBarOn = false;

//	COUNTER
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int spriteCounter = 0;
	public int shotAvailableCounter = 0;
	int dyingCounter = 0;
	int hpBarCounter = 0;

//	CHARACTER ATTRIBUTES
	public String name;
	public int speed;
	public int maxLife;
	public int life;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int hasKey;
	public Entity currentWeapon;
	public Entity currentShield;
//	public int maxMana;
//	public int mana;
	public Projectile projectile;

//	ITEM ATTRIBUTES
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int value;
//	public int useCost;

//	TYPE
	public int type;
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickupOnly = 7;
	public final int type_door = 8;
	public final int type_chest = 9;
	public final int type_key = 10;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {

	}

	public void damageReaction() {

	}

	public void use(Entity entity) {

	}

	public void speak() {
		if (dialogue[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogue[dialogueIndex];
		dialogueIndex++;

		switch (gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "right":
			direction = "left";
			break;
		case "left":
			direction = "right";
			break;
		}
	}

	public void checkDrop() {

	}

	public void dropItem(Entity droppedItem) {

		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] == null) {
				gp.obj[i] = droppedItem;
				gp.obj[i].worldX = worldX;
				gp.obj[i].worldY = worldY;
				break;
			}
		}
	}

	public void update() {
		setAction();

		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if (this.type == type_monster && contactPlayer == true) {
			damagePlayer(attack);
		}
		if (collisionOn == false) {

			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}

		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNumber == 1) {
				spriteNumber = 2;
			} else if (spriteNumber == 2) {
				spriteNumber = 3;
			} else if (spriteNumber == 3) {
				spriteNumber = 4;
			} else if (spriteNumber == 4) {
				spriteNumber = 1;
			}
			spriteCounter = 0;
		}
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 30) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if (shotAvailableCounter < 90) {
			shotAvailableCounter++;
		}
	}

	public BufferedImage setup(String imagePath, int width, int height) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		// mapnya bakalan digambar sesuai dengan batas karakternya agar ga berat
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			switch (direction) {
			case "up":
				if (spriteNumber == 1) {
					image = up1;
				}
				if (spriteNumber == 2) {
					image = up2;
				}
				if (spriteNumber == 3) {
					image = up1;
				}
				if (spriteNumber == 4) {
					image = up2;
				}
				break;
			case "down":
				if (spriteNumber == 1) {
					image = down1;
				} else if (spriteNumber == 2) {
					image = down2;
				} else if (spriteNumber == 3) {
					image = down1;
				} else if (spriteNumber == 4) {
					image = down2;
				}
				break;
			case "left":
				if (spriteNumber == 1) {
					image = left1;
				}
				if (spriteNumber == 2) {
					image = left2;
				}
				if (spriteNumber == 3) {
					image = left1;
				}
				if (spriteNumber == 4) {
					image = left2;
				}
				break;
			case "right":
				if (spriteNumber == 1) {
					image = right1;
				}
				if (spriteNumber == 2) {
					image = right2;
				}
				if (spriteNumber == 3) {
					image = right1;
				}
				if (spriteNumber == 4) {
					image = right2;
				}
				break;
			}

//			Monster HP Bar
			if (type == 2 && hpBarOn == true) {
				double normalizedLife = Math.max(0, life);
				double hpBarValue = ((double) normalizedLife / maxLife) * gp.tileSize;

//				BLACK BAR
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

//				RED BAR
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

				hpBarCounter++;

				if (hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			if (invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4F);
			}
			if (dying == true) {
				dyingAnimation(g2);
			}
			g2.drawImage(image, screenX, screenY, null);
			changeAlpha(g2, 1F);
		}
	}

	public void damagePlayer(int attack) {
		if (gp.player.invincible == false) {
//			we can give damage
			gp.playSE(6);

			int damage = attack - gp.player.defense;
			if (damage < 0) {
				damage = 0;
			}
			gp.player.life -= damage;
			gp.player.invincible = true;
		}

	}

	private void dyingAnimation(Graphics2D g2) {

		dyingCounter++;

		int i = 5;

		if (dyingCounter <= i) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i && dyingCounter <= i * 2) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 8) {
			alive = false;
		}
	}

	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

	}

}
