package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
	// DEBUG
	boolean showDebugText = false;
	GamePanel gp;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		// TITILE STATE
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}
		// PLAY STATE
		else if (gp.gameState == gp.playState) {
			playState(code);
		}
		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}

		// DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}

		// CHARACTER STATE
		else if (gp.gameState == gp.characterState) {
			characterState(code);
		}
		// OPTIONS STATE
		else if (gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		// ENDING SCREEN
		else if (gp.gameState == gp.winState) {
			winState(code);
		}
		// GAME OVER
		else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
//		CREDITS STATE
		else if (gp.gameState == gp.creditsState) {
			creditsState(code);
		}
	}

	private void gameOverState(int code) {
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.retry();
			}
			if (gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.stopMusic();
				gp.restart();
			}
		}
	}

	private void optionsState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.playSE(9);
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			gp.playSE(9);
			enterPressed = true;
		}

		int maxCommandNum = 0;
		switch (gp.ui.subState) {
//		UTAMA
		case 0:
			maxCommandNum = 4;
			break;
//		CONTROL
		case 1:
			maxCommandNum = 0;
			break;
//			END GAME
		case 4:
			maxCommandNum = 1;
			break;
		}
		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if (gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if (code == KeyEvent.VK_A) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				if (gp.ui.commandNum == 2 && gp.music.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(9);
				}
			}
		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
				gp.music.volumeScale++;
				gp.music.checkVolume();
				gp.playSE(9);
			}
			if (gp.ui.commandNum == 2 && gp.music.volumeScale < 5) {
				gp.se.volumeScale++;
				gp.playSE(9);
			}
		}

	}

	public void winState(int code) {
		if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.titleState;
				gp.stopMusic();
				gp.restart();

		}
	}

	public void titleState(int code) {
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.playSE(9);
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.playSE(9);
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.playSE(9);
				gp.playMusic(0);
				gp.gameState = gp.playState;
			}
			if (gp.ui.commandNum == 1) {
				gp.playSE(9);
				gp.gameState = gp.creditsState;
			}
			if (gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}

	public void playState(int code) {
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}

		// DEBUG
		if (code == KeyEvent.VK_T) {
			if (showDebugText == false) {
				showDebugText = true;
			} else if (showDebugText == true) {
				showDebugText = false;
			}
		}
		if (code == KeyEvent.VK_R) {
			gp.tileM.loadMap("/maps/finalmap.txt");
		}
	}

	public void pauseState(int code) {
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}

	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}
	}

	public void characterState(int code) {
		if (code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
			gp.playSE(9);
		}
		if (code == KeyEvent.VK_W) {
			gp.ui.slotRow--;
			gp.playSE(9);
			if (gp.ui.slotRow < 0) {
				gp.ui.slotRow = 3;
			}

		}
		if (code == KeyEvent.VK_A) {
			gp.ui.slotCol--;
			gp.playSE(9);
			if (gp.ui.slotCol < 0) {
				gp.ui.slotCol = 4;
			}

		}
		if (code == KeyEvent.VK_S) {
			gp.ui.slotRow++;
			gp.playSE(9);
			if (gp.ui.slotRow > 3) {
				gp.ui.slotRow = 0;
			}

		}
		if (code == KeyEvent.VK_D) {
			gp.ui.slotCol++;
			gp.playSE(9);
			if (gp.ui.slotCol > 4) {
				gp.ui.slotCol = 0;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
	}

	private void creditsState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.titleState;
			gp.playSE(9);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}

	}

}
