package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {
	GamePanel gp;

	public OBJ_Boots(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = "Boots";
		down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nOld looking boots.\nPress [ENTER]";
	}

	public void use(Entity entity) {

		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You suddenly\nfeel faster!";
		entity.speed +=1;
		gp.playSE(2);

	}
}
