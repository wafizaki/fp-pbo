package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
	GamePanel gp;

	public OBJ_Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Rock";
		speed = 5;
		maxLife = 40;
		life = maxLife;
		attack = 2;
//		useCost=1;
		alive = false;
		getImage();
	}
	public void getImage() {
		up=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		up1=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		up2=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		down=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		down1=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		down2=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		left=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		left1=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		left2=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		right=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		right1=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		right2=setup("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
		
	}

}
