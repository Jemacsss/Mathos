package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Panel;


public class Player extends Entity{

		Panel gp;
		KeyHandler keyH;
		
		public final int screenX;
		public final int screenY;
		
		public Player(Panel gp, KeyHandler keyH) {
			
			this.gp = gp;
			this.keyH = keyH;
			
			screenX = gp.screenWidth/2 - (gp.tileSize/2);
			screenY = gp.screenHeight/2- (gp.tileSize/2);
			
			solidArea = new Rectangle();
			solidArea.x = 8;
			solidArea.y = 8;
			solidArea.width = 24;
			solidArea.height = 24;
			
			
			setDefaultValues();
			getPlayerImage();
		}
		
		public void setDefaultValues() {
			
			worldX = gp.tileSize * 10;
			worldY = gp.tileSize * 10;
//			speed = 4;
			speed = gp.worldWidth/240;
					
			direction = "down";
		}
		public void getPlayerImage() {
			
			try {
				
				up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
				up3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_3.png"));
				up4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_4.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
				down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
				down3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_3.png"));
				down4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_4.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
				left3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_3.png"));
				left4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_4.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
				right3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_3.png"));
				right4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_4.png"));
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public void update() {
		    if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
		        if (keyH.upPressed) {
		            direction = "up";
		        } else if (keyH.downPressed) {
		            direction = "down";
		        } else if (keyH.leftPressed) {
		            direction = "left";
		        } else if (keyH.rightPressed) {
		            direction = "right";
		        }

		        // Check collision
		        collisionOn = false;
		        gp.cChecker.checkTile(this);

		        // If no collision, move player
		        if (!collisionOn) {
		            switch (direction) {
		                case "up": worldY -= speed; break;
		                case "down": worldY += speed; break;
		                case "left": worldX -= speed; break;
		                case "right": worldX += speed; break;
		            }
		        }
		    }

		    // Sprite animation logic
		    spriteCounter++;
		    if (spriteCounter > 20) {
		        spriteNum = (spriteNum % 4) + 1;
		        spriteCounter = 0;
		    }
		}

		
		public void draw(Graphics2D g2) {
			
//			g2.setColor(Color.white);
//			g2.fillRect(x, y, gp.tileSize, gp.tileSize);
			
			BufferedImage image = null;
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				if(spriteNum == 3) {
					image = up3;
				}
				if(spriteNum == 4) {
					image = up4;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				if(spriteNum == 3) {
					image = down3;
				}
				if(spriteNum == 4) {
					image = down4;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				if(spriteNum == 3) {
					image = left3;
				}
				if(spriteNum == 4) {
					image = left4;
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				if(spriteNum == 3) {
					image = right3;
				}
				if(spriteNum == 4) {
					image = right4;
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
}
