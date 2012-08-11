package de.tu_darmstadt.gdi1.tanks.logic;
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.SystemColor; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JProgressBar; 
import javax.swing.border.EmptyBorder; 
import javax.swing.plaf.basic.BasicProgressBarUI; 

public class FarbigeStatusAnzeige extends JFrame { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FarbigeProgressBar bar; 
    JButton button; 
    int max = 4000, min = 0; 

    public FarbigeStatusAnzeige() { 

        bar = new FarbigeProgressBar(min, max); 
        bar.setValue(max); 
        bar.setStringPainted(true); 

        final Runnable runnable = new Runnable() { 
            int rot = 0, gruen = 255; 
            double faktor = max / 255 / 2; 

            public void run() { 
                for (int i = max; i >= min; i--) {
                	
                    if (i < max / 2.4 * 2) { 
                        rot += faktor / 5; 
                    } 
                    if (i < max / 3) {
                    	rot = 255;
                    	
                        gruen -= faktor / 25; 
                        rot += faktor / 15; 
                    }
                     
                    //color should be between 0 and 255
                    rot = rot > 255 ? 255 : rot; 
                    rot = rot < 0 ? 0 : rot; 
                    gruen = gruen > 255 ? 255 : gruen; 
                    gruen = gruen < 0 ? 0 : gruen; 
                    
                    bar.setForeground(new Color(rot, gruen, 0)); 
                    bar.setValue(i); 
                    try { 
                        Thread.sleep(1); 
                    } catch (InterruptedException ex) { 
                    }
                } 
                rot = 0; 
                gruen = 255; 
            } 
        }; 

        button = new JButton("start"); 
        button.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                Thread thread = new Thread(runnable); 
                thread.start(); 
            } 
        }); 
        setLayout(new BorderLayout()); 
        add(button, BorderLayout.SOUTH); 
        add(bar, BorderLayout.CENTER); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        pack(); 
        setVisible(true); 
    } 

    public static void main(String[] args) { 
        new FarbigeStatusAnzeige(); 
    } 
} 

class FarbigeProgressBar extends JProgressBar { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FarbigeProgressBar(int start, int end) { 
        setMinimum(start); 
        setMaximum(end); 
        setForeground(SystemColor.window); 
        setBackground(SystemColor.window); 
        setBorder(new EmptyBorder(3, 5, 3, 5)); 
        Dimension size = new Dimension(300, 20); 
        setPreferredSize(size); 
        setMaximumSize(size); 
        setMinimumSize(size); 
        BasicProgressBarUI ui = new BasicProgressBarUI() { 
            protected Color getSelectionForeground(){ 
                return Color.BLACK; 
            } 
            protected Color getSelectionBackground(){ 
                return Color.BLACK; 
            } 
        }; 
        setUI(ui); 
    } 
} 