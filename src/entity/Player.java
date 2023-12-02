package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX, screenY;
	public int hasKey = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 14;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 20;
		solidArea.height = 26;

		setDefaultValues();
		getPlayerImage();

	}

	public void setDefaultValues() {
		// player starting potition
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {
		
		up1 = setup("walk_up_1");
		up2 = setup("walk_up_2");
		up = setup("walk_up");
		down1 = setup("walk_down_1");
		down2 = setup("walk_down_2");
		down = setup("walk_down");
		left1 = setup("walk_left_1");
		left2 = setup("walk_left_2");
		left = setup("walk_left");
		right1 = setup("walk_right_1");
		right2 = setup("walk_right_2");
		right = setup("walk_right");
	}
	
	public BufferedImage setup(String imageName) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image  = ImageIO.read(getClass().getResourceAsStream("/player/"+ imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
		
	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			// check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// klo false,player bisa gerak
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
		}

	}

	public void pickUpObject(int i) {

		if (i != 999) {
			String objectName = gp.obj[i].name;

			switch (objectName) {
				case "Key":
					gp.playSE(1);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("You got a key!");
					break;
				case "Door":
					if (hasKey > 0) {
						gp.playSE(3);
						gp.obj[i] = null;
						hasKey--;
						gp.ui.showMessage("You opened the door!");
					} else
						gp.ui.showMessage("Find more Key to open the door!");
					break;
				case "Boots":
					gp.playSE(2);
					speed += 1.5;
					gp.obj[i] = null;
					gp.ui.showMessage("Speed up!");
					break;
				case "Chest":
					gp.ui.isGameFinished = true;
					gp.stopMusic();
					gp.playSE(4);
					break;
			}
		}
	}

	public void draw(Graphics2D g2) {
		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch (direction) {
			case "up":
				if (spriteNumber == 1) {
					image = up1;
				}
				if (spriteNumber == 2) {
					image = up;
				}
				if (spriteNumber == 3) {
					image = up2;
				}
				if (spriteNumber == 4) {
					image = up;
				}
				break;
			case "down":
				if (spriteNumber == 1) {
					image = down1;
				} else if (spriteNumber == 2) {
					image = down;
				} else if (spriteNumber == 3) {
					image = down2;
				} else if (spriteNumber == 4) {
					image = down;
				}
				break;
			case "left":
				if (spriteNumber == 1) {
					image = left1;
				}
				if (spriteNumber == 2) {
					image = left;
				}
				if (spriteNumber == 3) {
					image = left2;
				}
				if (spriteNumber == 4) {
					image = left;
				}
				break;
			case "right":
				if (spriteNumber == 1) {
					image = right1;
				}
				if (spriteNumber == 2) {
					image = right;
				}
				if (spriteNumber == 3) {
					image = right2;
				}
				if (spriteNumber == 4) {
					image = right;
				}
				break;
		}
		g2.drawImage(image, screenX, screenY, null);
		//g2.setColor(Color.red);
		//g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}

}