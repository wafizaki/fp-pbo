package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	
	GamePanel gp;
	Font font2d_25, font2d_40B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean isGameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		 try {
	            // Load the TTF font
			 	File fontFile = new File("C:\\Users\\rog\\Downloads\\press-start-2p-font\\font2d.ttf");
	            font2d_25 = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 25);
	            font2d_40B = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 40);
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
		
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		if(isGameFinished == true) {
			g2.setFont(font2d_25);
			g2.setColor(Color.WHITE);
			
			String text;
			int x, y, textLength;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();	
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text = "Your Time is :" + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();	
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text, x, y);
			
			g2.setFont(font2d_40B);
			g2.setColor(Color.yellow);
			text = "CONGRATULATIONS!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();	
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
		}
		else {
			g2.setFont(font2d_25);
			g2.setColor(Color.WHITE);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x" + gp.player.hasKey, 74, 65);
			
			//timer
			playTime +=(double)1/60;
			g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*10, 65);
			
			//message
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(18F));
				g2.drawString(message,gp.tileSize/2, gp.tileSize*5);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}	
		}
	}
}