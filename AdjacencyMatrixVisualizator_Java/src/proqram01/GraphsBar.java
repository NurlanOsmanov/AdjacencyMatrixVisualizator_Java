package proqram01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GraphsBar extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ArrayList<Boolean>> matrixOfGraph = new ArrayList<>();
	private Point GraphMatrixLocation = new Point(0,0);
	
	private ArrayList<ArrayList<JButton>>  matrixButton = new ArrayList<>();
	JButton resetButton = new JButton("Reset");
	
	GraphsDesktop desktop;
	public GraphsBar(int panelWidth,int panelHeigh, GraphsDesktop desktop) 
	{
		setPreferredSize(new Dimension(panelWidth,panelHeigh));
		setBackground(new Color(240,240,240));
		
		this.desktop = desktop;
		
		setFocusable(true);
		requestFocusInWindow();
		
		addMouseListener(this);
		
		setLayout(null);
		
		resetButton.setBounds(10, (int)(panelHeigh * 0.58), panelWidth/2 - 20, (int)(panelHeigh * 0.10));
		add(resetButton);
		
		ResetButton();
		RandomButton(panelWidth, panelHeigh);
		
		//Graph Matrix:
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawLine(getWidth()-1, getHeight(), getWidth() - 1, 0);
		
		ChangeOfSizeOfMatrix();
		
		AdjacencyMatrix(g2d, GraphMatrixLocation.x, GraphMatrixLocation.y, 10,desktop.getGraphVerticesSize());
		Guide(g2d, strokeOfLine/2,(int)(GraphGenerator.WIDTH * GraphGenerator.panelPercente) + strokeOfLine/2, 50);
		
		
		repaint();
		
	}
	
	int strokeOfLine;
	
	public void AdjacencyMatrix(Graphics2D g2d, int x, int y, int strokeOfLine,int sizeOfMatrix)
	{
		
		x += strokeOfLine;
		y += strokeOfLine;
		strokeOfLine *= 2;
		
		this.strokeOfLine = strokeOfLine;
		int width = (int)(GraphGenerator.WIDTH * GraphGenerator.panelPercente) - strokeOfLine;
		
		g2d.setColor(new Color(150,150,150));
		g2d.fillRect(x, y, width, width);
		
		g2d.setColor(new Color(0,0,0));
		g2d.drawRect(x, y, width, width);
		
		sizeOfMatrix ++;
		
		if(sizeOfMatrix != 0)
		for(int i=0; i < sizeOfMatrix ;i++)
		{
			for(int j=0; j < sizeOfMatrix ;j++)
			{
				if(i==j) 
				{
					g2d.setColor(new Color(200,200,200));
						
					if(i == 0 && j == 0)g2d.setColor(new Color(200,200,200));
				}
				else 
				{
					if(i == 0 || j == 0) g2d.setColor(new Color(200,200,200));
					else 
						{
							g2d.setColor(new Color(100,100,100));
						}
				}
				
				if(i == 0 || j == 0 || i == j)
				g2d.fillRect(x + (j* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2), y + (i* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2), width/sizeOfMatrix- strokeOfLine/sizeOfMatrix, width/sizeOfMatrix - strokeOfLine/sizeOfMatrix);
				
				if(i == 0 && j != 0) 
				{
					g2d.setFont(new Font("" + j,getFont().getStyle(), width/sizeOfMatrix - strokeOfLine/sizeOfMatrix - 5));
					g2d.setColor(Color.white);
					g2d.drawString("" + j, x + (j* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2) + (width/sizeOfMatrix - strokeOfLine/sizeOfMatrix)/4, y + (i* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2) + (width/sizeOfMatrix - strokeOfLine/sizeOfMatrix)/2 + g2d.getFontMetrics().getHeight()/4);
				}
				else
					if(i != 0 && j == 0) 
					{
						g2d.setFont(new Font("" + j,getFont().getStyle(), width/sizeOfMatrix - strokeOfLine/sizeOfMatrix - 5));
						g2d.setColor(Color.white);
						g2d.drawString("" + i, x + (j* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2) + (width/sizeOfMatrix - strokeOfLine/sizeOfMatrix)/4, y + (i* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2) + (width/sizeOfMatrix - strokeOfLine/sizeOfMatrix)/2 + g2d.getFontMetrics().getHeight()/4);
					}
				
				
			}
		}
		
		g2d.setColor(getBackground());
		
		for (int i = 1; i < sizeOfMatrix; i++) {
			for (int j = 1; j < sizeOfMatrix; j++)
			{
				if(i != j)
				{	
					if(matrixOfGraph.get(i-1).get(j-1))
	   				{
	   					matrixButton.get(i-1).get(j-1).setBackground(new Color(0,255,0));
	   				}
	   				else
	   				{
	   					matrixButton.get(i-1).get(j-1).setBackground(new Color(100,100,100));
	   				}
				}
			}
		}
	}
	
	
	public void MatrixButton(int x, int y, int strokeOfLine,int sizeOfMatrix)
	{
		
		x += strokeOfLine;
		y += strokeOfLine;
		strokeOfLine *= 2;
		
		this.strokeOfLine = strokeOfLine;
		int width = (int)(GraphGenerator.WIDTH * GraphGenerator.panelPercente) - strokeOfLine;
		
		sizeOfMatrix ++;
		
		for (int i = 1; i < sizeOfMatrix; i++) {
			for (int j = 1; j < sizeOfMatrix; j++)
			{
				if(i != j)
				{
					matrixButton.get(i-1).get(j-1).setBounds(x + (j* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2), y + (i* width/sizeOfMatrix + strokeOfLine/sizeOfMatrix/2), width/sizeOfMatrix- strokeOfLine/sizeOfMatrix, width/sizeOfMatrix - strokeOfLine/sizeOfMatrix);
					
					if(matrixOfGraph.get(i-1).get(j-1))
	   				{
	   					matrixButton.get(i-1).get(j-1).setBackground(new Color(0,255,0));
	   				}
	   				else
	   				{
	   					matrixButton.get(i-1).get(j-1).setBackground(new Color(100,100,100));
	   				}
				}
			}
		}
		System.out.println(" -->" + matrixButton.size());
	}
	
	public void Guide(Graphics2D g2d,int x, int y , int heigh)
	{
		int width = (int)(GraphGenerator.WIDTH * GraphGenerator.panelPercente) - strokeOfLine;
		
		g2d.setColor(new Color(150,150,150));
		g2d.fillRect(x, y, width , heigh);
		
		g2d.setColor(new Color(0,255,0));
		g2d.fillRect(x+10,y + heigh/2 - 10, 20, 20);
		
		g2d.setColor(Color.white);
		g2d.setFont(new Font("-1",getFont().getStyle(), 15));
		g2d.drawString("- 1", x + 35, y + heigh/2 + 5);
		
		g2d.setColor(new Color(100,100,100));
		g2d.fillRect(x+60,y + heigh/2 - 10, 20, 20);
		
		g2d.setColor(Color.white);
		g2d.setFont(new Font("- 0",getFont().getStyle(), 15));
		g2d.drawString("- 0", x + 85, y + heigh/2 + 5);
		
		g2d.setColor(new Color(200,200,200));
		g2d.fillRect(x+110,y + heigh/2 - 10, 20, 20);
		
		g2d.setColor(Color.white);
		g2d.setFont(new Font("",getFont().getStyle(), 15));
		g2d.drawString("- Boş", x + 135, y + heigh/2 + 5);
	}

	
	public void MatrixValue(int i, int j, int sizeOfMatrix)
	{

			if(!matrixOfGraph.get(i).get(j))
			{
				matrixOfGraph.get(i).set(j, true);
				matrixOfGraph.get(j).set(i, true);				
			}
			else
			{
				matrixOfGraph.get(i).set(j, false);
				matrixOfGraph.get(j).set(i, false);	
			}		
			
			for (int k1 = 0; k1 < sizeOfMatrix; k1++) {
				for (int k2 = 0; k2 < sizeOfMatrix; k2++) {
					System.out.print(matrixOfGraph.get(k1).get(k2) + " ");
				}
				System.out.println();
			}
			System.out.println("\n");
	}
	
	int sizeOfMatrix = 0;
	
	public boolean ChangeOfSizeOfMatrix()
	{
		int changeDif;
		
		boolean change = false;
		if(sizeOfMatrix != desktop.getGraphVerticesSize()) 
		{
			changeDif = desktop.getGraphVerticesSize() - sizeOfMatrix;
			System.out.println("<<->> " + changeDif );
			
			change = true;
				
			for(int k = 0; k < changeDif; k++)
			{
				sizeOfMatrix++;
				System.out.println("<<->> " + changeDif );
				ArrayList<Boolean> row = new ArrayList<>();
				matrixOfGraph.add(row);
				
				
			   	ArrayList<JButton> buttonRow = new ArrayList<>();
			    matrixButton.add(buttonRow);
			    
			    
				
			   	for (int i = 0; i < sizeOfMatrix; i++) 
			   	{
			   		final int colButton = i;
			   		final int rowButton = sizeOfMatrix-1;
			   		JButton button = new JButton();
			   		add(button);	
			   		button.setBackground(new Color(100,100,100));
			   		matrixButton.get(i).add(button);
			   		
			   		matrixButton.get(i).get(rowButton).addActionListener(new ActionListener() {
			   			@Override
			   			public void actionPerformed(ActionEvent e) {
			   				System.out.println("Toxundu: " + colButton + " - " + rowButton + "  --- " + matrixButton.size());
			   				
			   				MatrixValue(colButton, rowButton, sizeOfMatrix);
			   			}
			   		});
			   		
			   		JButton eButton = new JButton();
			   		add(eButton);
			   		eButton.setBackground(new Color(100,100,100));
			   		matrixButton.get(matrixButton.size() - 1).add(eButton);	  
			   		matrixButton.get(matrixButton.size() - 1).get(i).addActionListener(new ActionListener() {
			   			@Override
			   			public void actionPerformed(ActionEvent e) {
			   				System.out.println("Toxundu: " + (rowButton) + " - " + colButton + "  --- " + matrixButton.size());
			   				
			   				MatrixValue(rowButton, colButton, sizeOfMatrix);
			   			}
			   		});
			    }
				
				for(int i = 0; i < sizeOfMatrix; i++) 
				{
					matrixOfGraph.get(i).add(false);
					matrixOfGraph.get(matrixOfGraph.size()-1).add(false);
				}
				
				System.out.println("Changed");
			}
			MatrixButton(GraphMatrixLocation.x, GraphMatrixLocation.y, 10,desktop.getGraphVerticesSize());
				
		}
		else 
		{
			change = false;
		}
		return change;
	}
	
	public void ResetButton()
	{
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.ClearGraphVertices();
				
				for (ArrayList<JButton> arrayList : matrixButton)
				{
					for (JButton jButton : arrayList) {
						remove(jButton);
					}
					
				}
				matrixOfGraph.removeAll(matrixOfGraph);
				matrixButton.removeAll(matrixButton);
				
				sizeOfMatrix = 0;
				
				repaint();
			}
		});
	}
	
	public void Reset()
	{
		desktop.ClearGraphVertices();
				
		for (ArrayList<JButton> arrayList : matrixButton)
		{
			for (JButton jButton : arrayList) {
				remove(jButton);
			}
				
		}
		matrixOfGraph.removeAll(matrixOfGraph);
		matrixButton.removeAll(matrixButton);
				
		sizeOfMatrix = 0;
				
		repaint();
	}
	
	public void RandomButton(int width,int heigh)
	{
		JButton randomButton = new JButton("Random");
		randomButton.setBounds(width/2 + strokeOfLine, (int)(heigh * 0.58), width/2 - 20, (int)(heigh * 0.10));
		add(randomButton);
		
		randomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sizeOfVerticesInPane = JOptionPane.showInputDialog(null,"Neçə təpə nöqtəsi lazımdır?");
				int sizeOfVertices = Integer.parseInt(sizeOfVerticesInPane);
				
				if(sizeOfVertices < 0) sizeOfVertices = 0;
				else if(sizeOfVertices > 50) sizeOfVertices = 50;
				System.out.println("--<>" + sizeOfVertices);
				Reset();
				desktop.RandomVertices(sizeOfVertices);
				
				repaint();
			}
		});
	}
	
	//getter && setter
	
	
	
	
	public ArrayList<ArrayList<Boolean>> getMatrixOfGraph() {
		return matrixOfGraph;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//MatrixValue(e.getX(), e.getY(),desktop.getGraphVerticesSize());
		System.out.println(e.getX() + " - " + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
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
