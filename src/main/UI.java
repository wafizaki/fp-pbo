package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.DecimalFormat;

import entity.Entity;
import object.OBJ_Heart;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font font2d, font2d_40B, font2d_25;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean isGameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	BufferedImage heart_full, heart_half, heart_blank;

	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;

		try {
			InputStream is = getClass().getResourceAsStream("/font/font2d.ttf");
//			InputStream is_1 = getClass().getResourceAsStream("/font/DePixelKlein.ttf");
			font2d = Font.createFont(Font.TRUETYPE_FONT, is);
			font2d_40B = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 40F);
			font2d_25 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 25F);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image1;
		heart_blank = heart.image2;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;
		g2.setFont(font2d);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
//		TITLE STATE
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
//		PLAY STATE
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
		}
//		PAUSE STATE
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
			drawPlayerLife();
		}
//		DIALOGUE STATE
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
//		CHARACTER STATE
		if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
		}

		if (isGameFinished == true) {
			g2.setFont(font2d_25);
			g2.setColor(Color.WHITE);

			String text;
			int x, y, textLength;

			text = "You found the treasure!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - (gp.tileSize * 3);
			g2.drawString(text, x, y);

			text = "Your Time is :" + dFormat.format(playTime) + "!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 4);
			g2.drawString(text, x, y);

			g2.setFont(font2d_40B);
			g2.setColor(Color.yellow);
			text = "CONGRATULATIONS!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 2);
			g2.drawString(text, x, y);

			gp.gameThread = null;
		} else {
			g2.setFont(font2d_25);
			g2.setColor(Color.WHITE);

			// message
			if (messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(18F));
				g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

				messageCounter++;

				if (messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}

	private void drawCharacterScreen() {
//		CREATE A FRAME
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 6;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

//		TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(18F));

		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 38;

//		NAMES
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("EXP", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 20;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;

//		VALUES
		int tailX = (frameX + frameWidth) - 30;
//		Reset textY
		textY = frameY + gp.tileSize;
		String value;

		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tileSize, textY-15,null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1,tailX-gp.tileSize, textY-15,null);
		textY +=gp.tileSize;
	}

	private void drawPlayerLife() {
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;

//		DRAW BLANK IMAGE
		while (i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}

//	RESET
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;

//	DRAW CURRENT LIFE
		while (i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
				i++;
				x += gp.tileSize;
			}

		}

	}

	private void drawTitleScreen() {
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
//		TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));
		String text = "Adventure of";
		String text1 = "Reke";
		int x = getXforCenteredTest(text);
		int x1 = getXforCenteredTest(text1);
		int y = gp.tileSize * 3;

//		SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y - 15);
		g2.drawString(text1, x1 + 5, y + 60);
//		MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y - 20);
		g2.drawString(text1, x1, y + 55);

//		IMAGENYA REKE
		x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
		y += gp.tileSize * 2;
		g2.drawImage(gp.player.down, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

//		MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));

		text = "NEW GAME";
		x = getXforCenteredTest(text);
		y += gp.tileSize * 3.5;
//		SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
//		MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.setColor(Color.gray);
			g2.drawString(">", x - gp.tileSize + 3, y + 3);
			g2.setColor(Color.white);
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "LOAD GAME";
		x = getXforCenteredTest(text);
		y += gp.tileSize;
//		SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
//		MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.setColor(Color.gray);
			g2.drawString(">", x - gp.tileSize + 3, y + 3);
			g2.setColor(Color.white);
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "QUIT";
		x = getXforCenteredTest(text);
		y += gp.tileSize;
//		SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
//		MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.setColor(Color.gray);
			g2.drawString(">", x - gp.tileSize + 3, y + 3);
			g2.setColor(Color.white);
			g2.drawString(">", x - gp.tileSize, y);
		}

	}

	private void drawDialogueScreen() {
//		WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
		x += gp.tileSize;
		y += gp.tileSize;

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}

	private void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60));
		String text = "PAUSED";
		int x = getXforCenteredTest(text);

		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);
	}

	public int getXforCenteredTest(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;

	}

	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}