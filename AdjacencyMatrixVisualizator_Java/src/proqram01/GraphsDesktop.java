package proqram01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GraphsDesktop extends JPanel implements MouseMotionListener,MouseListener
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Point> graphVerticesCoordinate = new ArrayList<>();
	private ArrayList<ArrayList<Boolean>> matrixOfGraph = new ArrayList<>();
	
	private GraphsBar graphsBar;
	
	public GraphsDesktop(int panelWidth,int panelHeigh) 
	{
		setPreferredSize(new Dimension(panelWidth,panelHeigh));
		setBackground(Color.white);
		
		setFocusable(true);
		requestFocusInWindow();
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		
	}
	
	public GraphsDesktop(){}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.blue);
		
		LinesOfVertices(g2d);
		
		drawGraphVertices(g2d);
		
		
		repaint();
	}
	
	public void setGraphVertices(Point point)
	{
		if(graphVerticesCoordinate.size() < 50)
		graphVerticesCoordinate.add(point);
	}
	
	public void getGraphVertices()
	{
		for(int i = 0; i < graphVerticesCoordinate.size(); i++)
			graphVerticesCoordinate.get(i);
	}

	public void drawGraphVertices(Graphics2D g2d)
	{
		int width = 20, heigh = 20;
		for(int i = 0; i < graphVerticesCoordinate.size(); i++)
		{
			g2d.setColor(Color.BLUE);
			g2d.fillOval(graphVerticesCoordinate.get(i).x - width/2, graphVerticesCoordinate.get(i).y - heigh/2, width, heigh);
			g2d.setColor(Color.white);
			g2d.drawString("" + (i + 1), graphVerticesCoordinate.get(i).x - 4, graphVerticesCoordinate.get(i).y + 3);
		}
		
	}
	
	public void LinesOfVertices(Graphics2D g2d)
	{
		matrixOfGraph = graphsBar.getMatrixOfGraph();
		
		for(int i = 0; i < matrixOfGraph.size() - 1; i++) 
		{
			for(int j = i + 1; j < matrixOfGraph.size(); j++)
			{
				if(matrixOfGraph.get(i).get(j)) 
				{
					g2d.setColor(Color.black);
					g2d.drawLine(graphVerticesCoordinate.get(i).x, graphVerticesCoordinate.get(i).y, graphVerticesCoordinate.get(j).x ,graphVerticesCoordinate.get(j).y);
				}
			}
		}

	}
	
	
	public void RandomVertices(int sizeOfVertices)
	{
		Point[] points = new Point[sizeOfVertices]; 
		Random random = new Random();
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point();
			points[i].x = random.nextInt(getWidth() - 40) + 20;
			points[i].y = random.nextInt(getHeight() - 40) + 20;
			setGraphVertices(points[i]);
		}
	}
	

	public void ClearGraphVertices()
	{
		graphVerticesCoordinate.removeAll(graphVerticesCoordinate);
	}
	
	
	//getter&setter
	
	
	public int getGraphVerticesSize(){
		return graphVerticesCoordinate.size();
	}
	
	
	public GraphsBar getGraphsBar() {
		return graphsBar;
	}

	public void setGraphsBar(GraphsBar graphsBar) {
		this.graphsBar = graphsBar;
	}

	public void setMatrixOfGraph(ArrayList<ArrayList<Boolean>> matrixOfGraph) {
		this.matrixOfGraph = matrixOfGraph;
	}

	//end of getter setter
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
System.out.println("Clicked");
		
		setGraphVertices(new Point(e.getX(), e.getY()));
		
		for(int i = 0; i < graphVerticesCoordinate.size(); i++) System.out.println("-->" + graphVerticesCoordinate.get(i));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
