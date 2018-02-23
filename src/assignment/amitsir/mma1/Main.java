package assignment.amitsir.mma1;

import javax.swing.JFrame;

/*
 * 
 * Assignment by :
 * 				Pritom Saha Akash(0604)
 * 				Sharafat Ahmed(0617)
 * 
 * */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame jFrame = new JFrame("Memory Management Algorithms");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1015, 500);
        jFrame.add(new MMApanel());
        jFrame.setVisible(true);

	}

}
