package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
	public BufferedImage attackUp, attackUp1, attackUp2, attackRight, attackRight1, attackRight2, attackLeft,
			attackLeft1, attackLeft2, attackDown, attackDown1, attackDown2;
	public String direction = "down";
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	String dialogue[] = new String[20];
	int dialogueIndex = 0;
	public BufferedImage image, image1, image2;
	public String name;
	public boolean collision = false;
//	0=player 1=npc 2=monster
	public int type;
	boolean attacking = false;

//	CHARACTER STATUS
	public int maxLife;
	public int life;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {

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

	public void update() {
		setAction();

		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if (this.type == 2 && contactPlayer == true) {
			if (gp.player.invincible == false) {
//				we can give damage
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
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
			if (invincible == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			if (invincible == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}

	}
}
