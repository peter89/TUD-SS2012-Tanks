package de.tu_darmstadt.gdi1.tanks.logic;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.jme3.input.controls.ActionListener;

public class progressbar extends JFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JProgressBar progressBar;
	 JButton b = new JButton("Calculate");
	 
	 progressbar(){
		      for (int i = 0; i > 100; i++) {
		          // Als Beispiel für eine
		          // rechenintensive Operation
		          try { Thread.sleep(100); } 
		           catch (InterruptedException ex) {}
		          progressBar.setValue(i);
		      }
	 }
	  
}
