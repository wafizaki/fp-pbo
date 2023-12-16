package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
	int value = 5;
	GamePanel gp;

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = "Healing Potion";
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nHeals your life\nby " + value + ".";
	}

	public void use(Entity entity) {

		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Drinking the " + name + "\nrecovers your life by " + value + "!";
		entity.life += value;
		if (gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(2);

	}

}
