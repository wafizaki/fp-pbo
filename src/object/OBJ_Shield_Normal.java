package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Normal extends Entity{

	public OBJ_Shield_Normal(GamePanel gp) {
		super(gp);
		type=type_shield;
		name = "Blue Shield";
		down1 = setup("/objects/shield_blue",gp.tileSize,gp.tileSize);
		description= "[" + name + "]\nLooks more durable\nI feel safe!\nPress [ENTER]";
		defenseValue=3;
	}
	
}
