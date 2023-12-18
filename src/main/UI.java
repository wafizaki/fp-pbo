package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.FontMetrics;
import entity.Entity;
import object.OBJ_Heart;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font font2d, font2d_40B, font2d_25;
	public boolean messageOn = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	BufferedImage heart_full, heart_half, heart_blank;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public int slotCol = 0;
	public int slotRow = 0;
	int subState = 0;
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;

		try {
			InputStream is = getClass().getResourceAsStream("/font/font2d.ttf");
			// InputStream is_1 = getClass().getResourceAsStream("/font/DePixelKlein.ttf");
			font2d = Font.createFont(Font.TRUETYPE_FONT, is);
			font2d_40B = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 40F);
			font2d_25 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 25F);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image1;
		heart_blank = heart.image2;
	}

	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;
		g2.setFont(font2d);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// PLAY STATE
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		// PAUSE STATE
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
			drawPlayerLife();
		}
		// DIALOGUE STATE
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		// CHARACTER STATE
		if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		// OPTIONS STATE
		if (gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		// WIN STATE
		if (gp.gameState == gp.winState) {
			drawWinScreen();
		}
		// GAME OVER
		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		// CREDITS STATE
		if (gp.gameState == gp.creditsState) {
			drawCreditsScreen();
		}
	}

	private void drawGameOverScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

		text = "Game Over";
		// SHADOW
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x, y);
		// MAIN
		g2.setColor(Color.white);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x - 4, y - 4);

		// RETRY
		g2.setFont(g2.getFont().deriveFont(32F));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 48, y);
		}
		// MAIN MENU
		text = "Main menu";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 48, y);
		}

	}

	private void drawWinScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));
		String text = "YOU WIN!";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3;

		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y - 15);
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y - 20);

		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));

		// SHADOW
		text = "MAIN MENU";
		x = getXforCenteredText(text);
		y += gp.tileSize * 3.5;
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 48, y);
		}
	}

	private void drawCreditsScreen() {
		// Fill the screen with a black background
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		String developer1 = "Developers :";
		String developer2 = "\n";
		String developer3 = "Ardhika Krishna";
		String developer4 = "Wafi Zaki";

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
		int x = getXforCenteredText(developer1);
		int y = gp.tileSize * 4;

		g2.setColor(Color.white);

		g2.drawString(developer1, x, y);
		g2.drawString(developer2, x, y + gp.tileSize);
		g2.drawString(developer3, x, y + gp.tileSize * 2);
		g2.drawString(developer4, x, y + gp.tileSize * 3);

		String text = "BACK";
		x = getXforCenteredText(text);
		y = gp.tileSize * 10;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.setColor(Color.gray);
			g2.drawString(">", x - gp.tileSize + 3, y + 3);
			g2.setColor(Color.white);
			g2.drawString(">", x - gp.tileSize, y);
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.titleState;
				commandNum = 0;
			}
		}
	}

	private void drawOptionsScreen() {

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(18F));

		// SUB WINDOW
		int frameX = gp.tileSize * 5;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 10;
		int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
			case 0:
				options_top(frameX, frameY);
				break;
			case 1:
				options_control(frameX, frameY);
				break;
			case 2:
				options_endGameConfirmation(frameX, frameY);
				break;
		}
		gp.keyH.enterPressed = false;
	}

	private void options_control(int frameX, int frameY) {
		int textX;
		int textY;

		// TITLE
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Confirm/Attack", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Shoot", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Inventory", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Pause", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Debug", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 0;
			}
		}

		textX = frameX + gp.tileSize * 7;
		textY = frameY + gp.tileSize * 2;

		g2.drawString("WASD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Enter", textX, textY);
		textY += gp.tileSize;
		g2.drawString("F", textX, textY);
		textY += gp.tileSize;
		g2.drawString("C", textX, textY);
		textY += gp.tileSize;
		g2.drawString("P", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ESC", textX, textY);
		textY += gp.tileSize;
		g2.drawString("T", textX, textY);
		textY += gp.tileSize;
	}

	private void options_endGameConfirmation(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;

		currentDialogue = "Quit the game\nand return to the\ntitle screen?";

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}

		// YES
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize * 3;
		g2.drawString(text, textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 24, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
				gp.stopMusic();
			}
		}

		// NO
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 24, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 1;
			}
		}
	}

	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;

		// TITLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		// CONTROL
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Controls", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 24, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 1;
				commandNum = 0;
			}
		}

		// END GAME
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 24, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		// BACK
		textY += gp.tileSize * 5;
		g2.drawString("Back", textX, textY);
		if (commandNum == 2) {
			g2.drawString(">", textX - 24, textY);
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		//
	}

	private void drawInventory() {

		int frameX = gp.tileSize * 12;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = gp.tileSize * 5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;

		// CURSOR
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		// DRAW CURSOR
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		// DRAW PLAYER'S ITEM
		for (int i = 0; i < gp.player.inventory.size(); i++) {

			// EQUIP CURSOR
			if (gp.player.inventory.get(i) == gp.player.currentWeapon
					|| gp.player.inventory.get(i) == gp.player.currentShield) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}

			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

			slotX += slotSize;
			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}

		// DESCRPIPTION FRAME
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize * 3;

		// DRAW DESCRPTION TEXT
		int textX = dFrameX + 20;
		int textY = dFrameY + 32;
		g2.setFont(g2.getFont().deriveFont(12F));

		int itemIndex = getItemIndexOnSlot();
		if (itemIndex < gp.player.inventory.size()) {
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 24;

			}
		}
	}

	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}

	private void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));

		for (int i = 0; i < message.size(); i++) {
			if (message.get(i) != null) {

				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);

				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;

				if (messageCounter.get(i) > 120) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}

	private void drawCharacterScreen() {
		// CREATE A FRAME
		final int frameX = gp.tileSize * 2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 6;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(18F));

		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 38;

		// NAMES
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

		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
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

		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 15, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 15, null);
		textY += gp.tileSize;
	}

	private void drawPlayerLife() {
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;

		// DRAW BLANK IMAGE
		while (i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}

		// RESET
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;

		// DRAW CURRENT LIFE
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
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));
		String text = "Adventure of";
		String text1 = "Reke";
		int x = getXforCenteredText(text);
		int x1 = getXforCenteredText(text1);
		int y = gp.tileSize * 3;

		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y - 15);
		g2.drawString(text1, x1 + 5, y + 60);
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y - 20);
		g2.drawString(text1, x1, y + 55);

		// IMAGENYA REKE
		x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
		y += gp.tileSize * 2;
		g2.drawImage(gp.player.down, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));

		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 3.5;
		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.setColor(Color.gray);
			g2.drawString(">", x - gp.tileSize + 3, y + 3);
			g2.setColor(Color.white);
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "CREDITS";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.setColor(Color.gray);
			g2.drawString(">", x - gp.tileSize + 3, y + 3);
			g2.setColor(Color.white);
			g2.drawString(">", x - gp.tileSize, y);
			if (gp.keyH.enterPressed) {
				gp.gameState = gp.creditsState;
				commandNum = 0;
			}
		}

		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;

		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
		// MAIN COLOR
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
		// WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18));
		x += gp.tileSize;
		y += gp.tileSize;

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x - 20, y);
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
		int x = getXforCenteredText(text);

		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);
	}

	public int getXforCenteredText(String text) {
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