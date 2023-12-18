package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		
		type=type_axe;
		name = "Lumberjack's Axe";
		down1=setup("/objects/axe",gp.tileSize,gp.tileSize);
		description= "[" + name + "]\nLooks pretty old.\nMaybe it can still\nbe used.\nPress [ENTER]";
		attackValue=2;
		attackArea.width =28;
		attackArea.height=28;
	}

}
