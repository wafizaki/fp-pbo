package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/oldman_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
		up = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/oldman_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/oldman_down_2",gp.tileSize,gp.tileSize);
		down = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/oldman_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/oldman_left_2",gp.tileSize,gp.tileSize);
		left = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/oldman_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/oldman_right_2",gp.tileSize,gp.tileSize);
		right = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
	}
	
	public void setDialogue() {
		dialogue[0] = "Hail and well met, young traveler!\nHow dost thou fare on this enchanted\nday?!";
		dialogue[1] = "The key thou seeketh lies concealed,\na hidden key behind yon colossal tree!";
		dialogue[2] = "Take heed, brave adventurer, as you\nembark upon thy journey. Perils may\nlie ahead, so tread carefully and may\nfortune favor thee";
		dialogue[3] = "Once, in the days of yore, I was a\nformidable wizard, weaving spells and \nwielding the arcane with great prowess.";
		dialogue[4]= "Behind the colossal tree, lies thou\nfirst step on thou journey";
	}

	public void setAction() {

		actionLockCounter++;

		if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}

	}
	public void speak() {
		super.speak();
	}

}
