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
		gp.obj[i].worldX = 51 * gp.tileSize;
		gp.obj[i].worldY = 43 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 46 * gp.tileSize;
		gp.obj[i].worldY = 41 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 31 * gp.tileSize;
		gp.obj[i].worldY = 37 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 50 * gp.tileSize;
		gp.obj[i].worldY = 8 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 28 * gp.tileSize;
		gp.obj[i].worldY = 47 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 17 * gp.tileSize;
		gp.obj[i].worldY = 37 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 17 * gp.tileSize;
		gp.obj[i].worldY = 32 * gp.tileSize;
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
		gp.obj[i].worldX = 48 * gp.tileSize;
		gp.obj[i].worldY = 24 * gp.tileSize;
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
		gp.obj[i].worldX = 37 * gp.tileSize;
		gp.obj[i].worldY = 46 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Potion_Red(gp);
		gp.obj[i].worldX = 18 * gp.tileSize;
		gp.obj[i].worldY = 41 * gp.tileSize;
		i++;
	}

	public void setNPC() {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;

		gp.npc[1] = new NPC_OldMan(gp);
		gp.npc[1].worldX = gp.tileSize * 10;
		gp.npc[1].worldY = gp.tileSize * 10;

		gp.npc[2] = new NPC_OldMan(gp);
		gp.npc[2].worldX = gp.tileSize * 42;
		gp.npc[2].worldY = gp.tileSize * 13;
	}

	public void setMonster() {
		int i = 0;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 27;
		gp.monster[i].worldY = gp.tileSize * 26;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 28;
		gp.monster[i].worldY = gp.tileSize * 27;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 28;
		gp.monster[i].worldY = gp.tileSize * 29;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 29;
		gp.monster[i].worldY = gp.tileSize * 30;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 27;
		gp.monster[i].worldY = gp.tileSize * 34;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 27;
		gp.monster[i].worldY = gp.tileSize * 44;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 29;
		gp.monster[i].worldY = gp.tileSize * 45;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 28;
		gp.monster[i].worldY = gp.tileSize * 46;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 17;
		gp.monster[i].worldY = gp.tileSize * 39;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 17;
		gp.monster[i].worldY = gp.tileSize * 44;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 16;
		gp.monster[i].worldY = gp.tileSize * 45;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 16;
		gp.monster[i].worldY = gp.tileSize * 40;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 18;
		gp.monster[i].worldY = gp.tileSize * 40;
		i++;
	}
}
