package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[100];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/finalmap.txt");

	}

	public void getTileImage() {
		// placeholder
		setup(0, "grass00", false);
		setup(1, "grass00", false);
		setup(2, "grass00", false);
		setup(3, "grass00", false);
		setup(4, "grass00", false);
		setup(5, "grass00", false);
		setup(6, "grass00", false);
		setup(6, "grass00", false);
		setup(7, "grass00", false);
		setup(8, "grass00", false);
		setup(9, "grass00", false);
		// placeholder

		setup(10, "grass00", false);
		setup(11, "grass01", false);
		setup(12, "water00", true);
		setup(13, "water01", true);
		setup(14, "water02", true);
		setup(15, "water03", true);
		setup(16, "water04", true);
		setup(17, "water05", true);
		setup(18, "water06", true);
		setup(19, "water07", true);
		setup(20, "water08", true);
		setup(21, "water09", true);
		setup(22, "water10", true);
		setup(23, "water11", true);
		setup(24, "water12", true);
		setup(25, "water13", true);
		setup(26, "road00", false);
		setup(27, "road01", false);
		setup(28, "road02", false);
		setup(29, "road03", false);
		setup(30, "road04", false);
		setup(31, "road05", false);
		setup(32, "road06", false);
		setup(33, "road07", false);
		setup(34, "road08", false);
		setup(35, "road09", false);
		setup(36, "road10", false);
		setup(37, "road11", false);
		setup(38, "road12", false);
		setup(39, "earth", false);
		setup(40, "wall", true);
		setup(41, "palm2", true);
		setup(42, "hut", true);
		setup(43, "floor01", false);
		setup(44, "sand", false);
		setup(45, "table01", true);
		setup(46, "tile000", false);
		setup(47, "tile001", true);
		setup(48, "tile002", true);
		setup(49, "tile003", true);
		setup(50, "tile005", true);
		setup(51, "tile006", true);
		setup(52, "tile007", true);
		setup(53, "tile008", true);
		setup(54, "tile010", true);
		setup(55, "tile011", true);
		setup(56, "tile012", true);
		setup(57, "tile013", true);
		setup(58, "tile015", true);
		setup(59, "tile016", true);
		setup(60, "tile017", true);
		setup(61, "tile018", true);
		setup(62, "tile020", false);
		setup(63, "tile021", false);
		setup(64, "tile022", false);
		setup(65, "tile023", false);
		setup(66, "house01", true);
		setup(67, "house02", true);
		setup(68, "house05", true);
		setup(69, "house06", true);
		setup(70, "house09", true);
		setup(71, "house10", true);
		setup(72, "house13", false);
		setup(73, "house14", false);
		setup(74, "brokenTree1", true);
		// setup(76, "tile017", true);
		// setup(77, "tile017", true);
		// setup(78, "tile017", true);
		// setup(79, "tile017", true);
		// setup(80, "tile017", true);
		// setup(81, "tile017", true);
		// setup(82, "house01", true);
		// setup(83, "house02", true);
		// setup(84, "house05", true);
		// setup(85, "house06", true);
		// setup(86, "house09", true);
		// setup(87, "house10", true);
		// setup(88, "house13", true);
		// setup(89, "house14", true);

	}

	public void setup(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String string) {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/finalmap.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			// mapnya bakalan digambar sesuai dengan batas karakternya agar ga berat
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
