public class HangmanDisplayPanel extends JPanel {
	int bodyPartAppearNumber;//此参数代表当前显示hangman多少个部分,初始值为零
	
	public HangmanDisplayPanel() {
		super();
		this.setPreferredSize(new Dimension(400,600));
		bodyPartAppearNumber = 0;//初始值为零
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//以下两部分显示绞刑架
		//Scaffold
		g.drawLine(getWidth() / 2 - BEAM_LENGTH,
				(getHeight() - SCAFFOLD_HEIGHT) / 2, 
				getWidth() / 2 - BEAM_LENGTH, 
				(getHeight() + SCAFFOLD_HEIGHT) / 2);
		
		//Beam
		g.drawLine(getWidth()/2 - BEAM_LENGTH,
				(getHeight() - SCAFFOLD_HEIGHT)/2,
				getWidth()/2,
				(getHeight() - SCAFFOLD_HEIGHT)/2);
		//根据bodyPartAppearNumber的值,显示hangman身体部位
		displayMan(g);
		
	}
	
    //根据bodyPartAppearNumber的值,显示hangman身体部位
	private void displayMan(Graphics g){
		switch (bodyPartAppearNumber){
		case 8:
                     //画右脚的两条线
			g.drawLine((getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					(getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			
			g.drawLine((getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					(getWidth() + HIP_WIDTH)/2 + FOOT_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		case 7:
                    //画左脚的两条线
			g.drawLine((getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					(getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			
			g.drawLine((getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					(getWidth() - HIP_WIDTH)/2 - FOOT_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		case 6:
			//hip
			g.drawLine((getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					(getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		case 5:
			//Right Arm
			//Upper Arm
			g.drawLine(getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 + UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			//Lower Arm
			g.drawLine(getWidth()/2 + UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 + UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		
		case 4:
			//Left Arm
			//Upper Arm
			g.drawLine(getWidth()/2 - UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			//Lower Arm
			g.drawLine(getWidth()/2 - UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 - UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		case 3:
			//Body
			g.drawLine(getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS,
					getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		case 2:
                    //head
			g.drawOval((getWidth()/2 - HEAD_RADIUS),
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH,
					2 * HEAD_RADIUS,2 * HEAD_RADIUS);
		case 1:
                   // 头上的线
			g.drawLine(getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2,
					getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH);
		}
		
	}

/** Resets the display so that only the scaffold appears重新开始,只显示绞刑架 */
     public void reset() {
		
		bodyPartAppearNumber = 0;
		repaint();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		
	}

/**
 * 当用户猜错更新显示,此方法根据参数指定已猜错的次数,显示hangman的身体部位.
*Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(int gussNumUsed) {
		/* You fill this in */
		bodyPartAppearNumber = gussNumUsed;
		repaint();
	
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}

//4.HangmanLexicon
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */


