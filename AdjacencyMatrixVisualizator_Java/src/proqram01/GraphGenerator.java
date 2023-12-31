package proqram01;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GraphGenerator {

	final static int WIDTH = 640, HEIGH = 480;
	final static float panelPercente = 0.33f;
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graphs");
		
		GraphsDesktop graphsDesktop = new GraphsDesktop((int)(WIDTH* (1-panelPercente)), HEIGH);
		GraphsBar graphsBar = new GraphsBar((int)(WIDTH* panelPercente), HEIGH, graphsDesktop);
		graphsDesktop.setGraphsBar(graphsBar);
		
		frame.setSize(WIDTH,HEIGH);
		frame.setVisible(true);
		frame.setResizable(true);
		
		frame.add(graphsDesktop, BorderLayout.CENTER);
		frame.add(graphsBar, BorderLayout.WEST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
