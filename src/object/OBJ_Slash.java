package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Slash extends Projectile {
	GamePanel gp;

	public OBJ_Slash(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Slash";
		speed = 5;
		maxLife = 20;
		life = maxLife;
		attack = 2;
//		useCost=1;
		alive = false;
		getImage();
	}
	public void getImage() {
		up=setup("/projectile/slash_up_1",gp.tileSize,gp.tileSize);
		up1=setup("/projectile/slash_up_2",gp.tileSize,gp.tileSize);
		up2=setup("/projectile/slash_up_3",gp.tileSize,gp.tileSize);
		down=setup("/projectile/slash_down_1",gp.tileSize,gp.tileSize);
		down1=setup("/projectile/slash_down_2",gp.tileSize,gp.tileSize);
		down2=setup("/projectile/slash_down_3",gp.tileSize,gp.tileSize);
		left=setup("/projectile/slash_left_1",gp.tileSize,gp.tileSize);
		left1=setup("/projectile/slash_left_2",gp.tileSize,gp.tileSize);
		left2=setup("/projectile/slash_left_3",gp.tileSize,gp.tileSize);
		right=setup("/projectile/slash_right_1",gp.tileSize,gp.tileSize);
		right1=setup("/projectile/slash_right_2",gp.tileSize,gp.tileSize);
		right2=setup("/projectile/slash_right_3",gp.tileSize,gp.tileSize);
		
	}

}
