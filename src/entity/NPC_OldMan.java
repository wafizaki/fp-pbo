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

		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		up = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		down = setup("/npc/oldman_up_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		left = setup("/npc/oldman_up_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
		right = setup("/npc/oldman_up_2");
	}
	
	public void setDialogue() {
		dialogue[0] = "Wassup bro";
		dialogue[1] = "Aye how u been big bro?";
		dialogue[2] = "Man its been a minute";
		dialogue[3] = "Aye yo my G";
		dialogue[4]= "BROOOOO THIS SHIT CRAZY\nAYE YOU GOTTA CHECK'EM OUT\nBIG BRO";
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
