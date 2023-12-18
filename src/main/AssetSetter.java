package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Normal;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		int i = 0;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 11 * gp.tileSize;
		gp.obj[i].worldY = 11 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 12 * gp.tileSize;
		gp.obj[i].worldY = 12 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 31 * gp.tileSize;
		gp.obj[i].worldY = 37 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 46 * gp.tileSize;
		gp.obj[i].worldY = 41 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 15 * gp.tileSize;
		gp.obj[i].worldY = 15 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 42 * gp.tileSize;
		gp.obj[i].worldY = 25 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 28 * gp.tileSize;
		gp.obj[i].worldY = 25 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 28 * gp.tileSize;
		gp.obj[i].worldY = 48 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 40 * gp.tileSize;
		gp.obj[i].worldY = 48 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 26 * gp.tileSize;
		gp.obj[i].worldY = 52 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 17 * gp.tileSize;
		gp.obj[i].worldY = 48 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 17 * gp.tileSize;
		gp.obj[i].worldY = 35 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Boots(gp);
		gp.obj[i].worldX = 14 * gp.tileSize;
		gp.obj[i].worldY = 14 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Axe(gp);
		gp.obj[i].worldX = 31 * gp.tileSize;
		gp.obj[i].worldY = 21 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Shield_Normal(gp);
		gp.obj[i].worldX = 17 * gp.tileSize;
		gp.obj[i].worldY = 21 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Potion_Red(gp);
		gp.obj[i].worldX = 12 * gp.tileSize;
		gp.obj[i].worldY = 20 * gp.tileSize;
		i++;

	}

	public void setNPC() {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}

	public void setMonster() {
		int i = 0;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 14;
		gp.monster[i].worldY = gp.tileSize * 20;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 13;
		gp.monster[i].worldY = gp.tileSize * 21;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 22;
		gp.monster[i].worldY = gp.tileSize * 36;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 20;
		gp.monster[i].worldY = gp.tileSize * 37;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 19;
		gp.monster[i].worldY = gp.tileSize * 36;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 18;
		gp.monster[i].worldY = gp.tileSize * 37;
		i++;
	}
}
