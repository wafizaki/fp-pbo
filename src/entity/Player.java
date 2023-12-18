package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield_Wood;
import object.OBJ_Slash;
import object.OBJ_Sword_Normal;

public class Player extends Entity {

	KeyHandler keyH;

	public final int screenX, screenY;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
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

		// attackArea.width = 36;
		// attackArea.height = 36;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}

	public void setDefaultValues() {
		// player starting potition
		worldX = gp.tileSize * 12;
		worldY = gp.tileSize * 10;
		speed = 3;
		direction = "down";

		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		hasKey=0;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Slash(gp);
		attack = getAttack();
		defense = getDefense();
	}

	public void setItems() {

		inventory.add(currentWeapon);
		inventory.add(currentShield);
	}

	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}

	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}

	public void getPlayerImage() {

		up1 = setup("/player/walk_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/walk_up_2", gp.tileSize, gp.tileSize);
		up = setup("/player/walk_up", gp.tileSize, gp.tileSize);
		down1 = setup("/player/walk_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/walk_down_2", gp.tileSize, gp.tileSize);
		down = setup("/player/walk_down", gp.tileSize, gp.tileSize);
		left1 = setup("/player/walk_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/walk_left_2", gp.tileSize, gp.tileSize);
		left = setup("/player/walk_left", gp.tileSize, gp.tileSize);
		right1 = setup("/player/walk_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/walk_right_2", gp.tileSize, gp.tileSize);
		right = setup("/player/walk_right", gp.tileSize, gp.tileSize);
	}

	public void getPlayerAttackImage() {
		// KALO MAU GANTI SPRITE SETIAP GANTI SENJATA INI DIBIKIN AJA IF DENGAN KONDISI
		// CURRENTWEAPON.TYPE = TYPE_BLABLA
		attackUp = setup("/player/attack_up", gp.tileSize, gp.tileSize * 2);
		attackUp1 = setup("/player/attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/player/attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown = setup("/player/attack_down", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/player/attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/player/attack_down_2", gp.tileSize, gp.tileSize * 2);
		attackRight = setup("/player/attack_right", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/player/attack_right_1", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/player/attack_right_2", gp.tileSize * 2, gp.tileSize);
		attackLeft = setup("/player/attack_left", gp.tileSize * 2, gp.tileSize);
		attackLeft1 = setup("/player/attack_left_1", gp.tileSize * 2, gp.tileSize);
		attackLeft2 = setup("/player/attack_left_2", gp.tileSize * 2, gp.tileSize);
	}

	public void update() {
		if (attacking == true) {
			attacking();
		} else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true || keyH.enterPressed == true) {
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
			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			// CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			// CHECK EVENT
			gp.eHandler.checkEvent();

			// klo false,player bisa gerak
			if (collisionOn == false && keyH.enterPressed == false) {

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
			if (keyH.enterPressed == true && attackCanceled == false) {
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			attackCanceled = false;
			gp.keyH.enterPressed = false;

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
		// OUTSIDE OF KEY
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 90) {
			// SET DEFAULT COORDINATES, DIRECTION AND USE
			projectile.set(worldX, worldY, direction, true, this);

			// ADD PROJECTILE TO ARRAYLIST
			gp.projectileList.add(projectile);

			shotAvailableCounter = 0;
			attacking = true;
			gp.playSE(7);
		}
		if (shotAvailableCounter < 90) {
			shotAvailableCounter++;
		}
		if (life > maxLife) {
			life = maxLife;
		}
	}

	private void attacking() {
		spriteCounter++;

		if (spriteCounter <= 5) {
			spriteNumber = 1;
		}
		if (spriteCounter > 5 && spriteCounter <= 25) {
			spriteNumber = 2;

			// SAVE THE CURRENT WORLDX,WORLDY,SOLIDAREA
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			// ADJUST PLAYER'S WORLD X/Y FOR THE ATTACKAREA
			switch (direction) {
			case "up":
				worldY -= attackArea.height;
				break;
			case "down":
				worldY += attackArea.height;
				break;
			case "right":
				worldX += attackArea.width;
				break;
			case "left":
				worldX -= attackArea.width;
				break;

			}
			// attackArea becomes solidArae
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			// check monster collision with the updated worldx,worldy and solidarea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack);
			// after checking collision, restore the original data

			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if (spriteCounter > 25 && spriteCounter <= 30) {
			spriteNumber = 3;
		}
		if (spriteCounter > 30) {
			spriteNumber = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

	public void damageMonster(int i, int attack) {
		if (i != 999) {
			if (gp.monster[i].invincible == false) {
				gp.playSE(5);

				int damage = attack - gp.monster[i].defense;
				if (damage < 0) {
					damage = 0;
				}

				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + " damage!");

				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();

				if (gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
					gp.ui.addMessage("killed the " + gp.monster[i].name + "!");
					gp.ui.addMessage("You gained: " + gp.monster[i].exp + " exp!");
					exp += gp.monster[i].exp;
					eheckLevelUp();
				}
			}
		}
	}

	private void eheckLevelUp() {
		if (exp >= nextLevelExp) {

			level++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";

		}
	}

	private void contactMonster(int i) {
		if (i != 999) {
			if (invincible == false && gp.monster[i].dying == false) {
				gp.playSE(6);

				int damage = gp.monster[i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}

				life -= damage;
				invincible = true;
			}
		}
	}

	private void interactNPC(int i) {

		if (gp.keyH.enterPressed == true) {
			if (i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
	}

	public void pickUpObject(int i) {

		if (i != 999) {

			// PICKUP ONLY ITEMS
			if (gp.obj[i].type == type_pickupOnly) {

				gp.obj[i].use(this);
				gp.obj[i] = null;
			}
			// INVENTORY ITEMS
			else if (gp.obj[i].type != type_pickupOnly && gp.obj[i].type != type_door && gp.obj[i].type != type_chest) {
				String text;
				if (inventory.size() != maxInventorySize) {
					inventory.add(gp.obj[i]);
					if(gp.obj[i].type == type_key) {
						hasKey++;
					}
					gp.playSE(1);
					text = "Got a " + gp.obj[i].name + "!";
				} else {
					text = "Your inventory is full!";
				}
				gp.ui.addMessage(text);
				gp.obj[i] = null;
			} else if (gp.obj[i].type == type_door) {
				if (hasKey < 1) {
					gp.gameState = gp.dialogueState;
					gp.ui.currentDialogue = ("The door seems to be locked!\nI need to find the key!");
				} else {
					gp.playSE(3);
					gp.ui.addMessage("Unlocked door!");
					hasKey--;
					gp.obj[i] = null;
				}
			} else if (gp.obj[i].type == type_chest) {
				gp.gameState = gp.winState;

			}

			// String objectName = gp.obj[i].name;
			//
			// switch (objectName) {
			// case "Key":
			// gp.playSE(1);
			// hasKey++;
			// gp.obj[i] = null;
			// gp.ui.addMessage("You got a key!");
			// break;
			// case "Door":
			// if (hasKey > 0) {
			// gp.playSE(3);
			// gp.obj[i] = null;
			// hasKey--;
			// gp.ui.addMessage("You opened the door!");
			// } else
			// gp.ui.addMessage("Find more Key to open the door!");
			// break;
			// case "Boots":
			// gp.playSE(2);
			// speed += 1.5;
			// gp.obj[i] = null;
			// gp.ui.addMessage("Speed up!");
			// break;
			// case "Chest":
			// gp.ui.isGameFinished = true;
			// gp.stopMusic();
			// gp.playSE(4);
			// break;
			// }
		}

	}

	public void draw(Graphics2D g2) {
		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch (direction) {
		case "up":
			if (attacking == false) {
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
			}
			if (attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if (spriteNumber == 1) {
					image = attackUp;
				}
				if (spriteNumber == 2) {
					image = attackUp1;
				}
				if (spriteNumber == 3) {
					image = attackUp2;
				}
			}

			break;
		case "down":
			if (attacking == false) {
				if (spriteNumber == 1) {
					image = down1;
				}
				if (spriteNumber == 2) {
					image = down;
				}
				if (spriteNumber == 3) {
					image = down2;
				}
				if (spriteNumber == 4) {
					image = down;
				}
			}
			if (attacking == true) {
				if (spriteNumber == 1) {
					image = attackDown;
				}
				if (spriteNumber == 2) {
					image = attackDown1;
				}
				if (spriteNumber == 3) {
					image = attackDown2;
				}
			}
			break;
		case "left":
			if (attacking == false) {
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
			}
			if (attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if (spriteNumber == 1) {
					image = attackLeft;
				}
				if (spriteNumber == 2) {
					image = attackLeft1;
				}
				if (spriteNumber == 3) {
					image = attackLeft2;
				}
			}
			break;
		case "right":
			if (attacking == false) {
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
			}
			if (attacking == true) {
				if (spriteNumber == 1) {
					image = attackRight;
				}
				if (spriteNumber == 2) {
					image = attackRight1;
				}
				if (spriteNumber == 3) {
					image = attackRight2;
				}
			}
			break;
		}
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);

		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// DEBUG
		// g2.setFont(new Font("Arial", Font.PLAIN, 26));
		// g2.setColor(Color.white);
		// g2.drawString("Invincible:" + invincibleCounter, 10, 400);
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();

		if (itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);

			if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if (selectedItem.type == type_shield) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if (selectedItem.type == type_consumable) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}

}