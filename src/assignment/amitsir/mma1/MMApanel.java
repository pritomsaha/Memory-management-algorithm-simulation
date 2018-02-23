package assignment.amitsir.mma1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * 
 * Assignment by :
 * 				Pritom Saha Akash(0604)
 * 				Sharafat Ahmed(0617)
 * 
 * */
public class MMApanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JLabel area1=new JLabel();
	JLabel area2=new JLabel();
	JLabel area3=new JLabel();
	JLabel area4=new JLabel();
	JTextField ProcessSize = new JTextField();
	String items[] = {"First Fit","Next Fit","Best Fit","Worst Fit"};
	JComboBox Options = new JComboBox(items);
	JButton allocateButton = new JButton();
	JButton processInsertButton=new JButton();
	JButton processFreedButton=new JButton();
	JTextField insertProcessIndex=new JTextField();
	JTextField insertProcessSize=new JTextField();
	JTextField freedProcessIndex=new JTextField();
	JButton defaultAllocation=new JButton();
	JButton randomAllocation=new JButton();
	JButton process=new JButton();
	JButton hole=new JButton();
	JButton resetButton=new JButton();
	
	public Segment first;
	public Segment currentPos;
	public double memorySize=1000;
	
	
	public MMApanel(){
		setLayout(null);
        setBackground(Color.WHITE);
        randomAllocation();
        currentPos=first;

		JPanel optionPanel = new JPanel(new FlowLayout());
		optionPanel.add(new JLabel("Select Fit"));
		optionPanel.add(Options);
		add(optionPanel);
		optionPanel.setBounds(80, 75, 100, 50);

		JPanel processSizePanel = new JPanel(new FlowLayout());
		processSizePanel.add(new JLabel("Process Size"));
		add(processSizePanel);
		processSizePanel.setBounds(190,75,100,25);
		add(ProcessSize);
		ProcessSize.setBounds(190,100,100,25);

		add(allocateButton);
		allocateButton.setBounds(80,145,190,50);
		allocateButton.setText("Allocate");
		allocateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int processLength;
				try {
					processLength=Integer.parseInt(ProcessSize.getText());
					if(processLength>0){
						if(Options.getSelectedItem().toString() == "First Fit"){
                            firstfit(processLength);
                            repaint();
                        }
						else if(Options.getSelectedItem().toString() == "Next Fit"){
                            nextfit(processLength);
                            repaint();
                        }
                        else if(Options.getSelectedItem().toString() == "Best Fit"){
                            bestfit(processLength);
                            repaint();
                        }
                        else if(Options.getSelectedItem().toString() == "Worst Fit"){
                            worstfit(processLength);
                            repaint();
                        }
					}
					
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Please Enter a Valid Process Length");
				}
				
			}
		});
		
		add(processInsertButton);
		processInsertButton.setBounds(310, 75, 170, 50);
		processInsertButton.setText("Insert Process");
		processInsertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int start,length;
				try {
					start=Integer.parseInt(insertProcessIndex.getText());
					length=Integer.parseInt(insertProcessSize.getText());
					insertProcess(start, length);
					repaint();
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Please Enter a Valid Index/Length");
				}
				
			}
		});
		
		add(insertProcessIndex);
		insertProcessIndex.setBounds(310,145,80,50);
		insertProcessIndex.setToolTipText("Index");
		
		add(insertProcessSize);
		insertProcessSize.setBounds(400,145,80,50);
		insertProcessSize.setToolTipText("Lnegth");
		
		add(processFreedButton);
		processFreedButton.setBounds(520, 75, 170, 50);
		processFreedButton.setText("Freed Process");
		processFreedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int start;
				try {
					start=Integer.parseInt(freedProcessIndex.getText());
					freed(start);
					repaint();
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Suitable space not found!!!");
				}
			}
		});
		
		add(freedProcessIndex);
		freedProcessIndex.setBounds(565,145,80,50);
		freedProcessIndex.setToolTipText("Index");
		
		add(defaultAllocation);
		defaultAllocation.setBounds(730,75,170,50);
		defaultAllocation.setText("Default Allocation");
		defaultAllocation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				defaultAllocation();
			}
		});
		
		add(randomAllocation);
		randomAllocation.setBounds(730,145,170,50);
		randomAllocation.setText("Random Allocation");
		randomAllocation.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				randomAllocation();
			}
		});
		
		add(process);
		process.setBounds(20,360,20,20);
		process.setBackground(Color.DARK_GRAY);
		
		add(hole);
		hole.setBounds(20, 400, 20, 20);
		hole.setBackground(Color.LIGHT_GRAY);
		
		add(area2);
		area2.setText("Memory");
		area2.setBounds(470,330,100,20);
		
		add(area3);
		area3.setText("Process");
		area3.setBounds(50,360,100,20);
		
		add(area4);
		area4.setText("Hole");
		area4.setBounds(50,400,100,20);
		
		add(resetButton);
		resetButton.setText("Reset");
		resetButton.setBounds(800, 400, 170, 50);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Segment current=first;
		do{
			if(current.type==1){
				g.setColor(Color.DARK_GRAY);
				g.fillRect((int)current.start, 250,(int)current.length, 70);
				g.setColor(Color.BLACK);
				g.fillRect((int)(current.start+current.length-1),250, 1, 70);
			}
			else {
				g.setColor(Color.lightGray);
				g.fillRect((int)current.start, 250,(int)current.length, 70);
				g.setColor(Color.BLACK);
				g.fillRect((int)(current.start+current.length-1),250, 1, 70);
			}
		}while((current=current.next)!=null);
	}
	
	public void firstfit(double processSize){
		Segment current=first;
		Segment New=new Segment();
		boolean flag=false;
		do{
			if(current.type==0 && current.length>=processSize){
				if(current.length==processSize)current.type=1;
				else {
					New.start=current.start+processSize;
					New.length=current.length-processSize;
					New.type=0;
					current.type=1;
					current.length=processSize;
					if(current.next==null){
						New.previous=current;
						current.next=New;
					}
					else {
						New.previous=current;
						current.next.previous=New;
						New.next=current.next;
						current.next=New;
					}
				}
				flag=true;
				break;
			}
		}while((current=current.next)!=null);
		if(!flag)JOptionPane.showMessageDialog(null, "Suitable space not found!!!");
	}
	
	public void nextfit(double processSize){
		Segment current=currentPos;
		if(current==null)current=first;
		Segment New=new Segment();
		boolean flag=false;
		while(true){
			if(current.type==0 && current.length>=processSize){
				if(current.length==processSize)current.type=1;
				else {
					New.start=current.start+processSize;
					New.length=current.length-processSize;
					New.type=0;
					current.type=1;
					current.length=processSize;
					if(current.next==null){
						New.previous=current;
						current.next=New;
					}
					else {
						New.previous=current;
						current.next.previous=New;
						New.next=current.next;
						current.next=New;
					}
				}
				flag=true;
				break;
			}
			current=current.next;
			if(current==null)current=first;
			if(current==currentPos)break;
		}
		if(!flag)JOptionPane.showMessageDialog(null, "Suitable space not found!!!");
		currentPos=current.next;
	}
	
	public void bestfit(double processSize){
		Segment current=first;
		Segment bestSpace=new Segment();
		bestSpace.length=Double.MAX_VALUE;
		do{
			if(current.type==0 && current.length>=processSize){
				if(current.length<bestSpace.length)bestSpace=current;
			}
		}while((current=current.next)!=null);
		if(bestSpace.length==Double.MAX_VALUE)JOptionPane.showMessageDialog(null, "Suitable space not found!!!");
		else {
			if(bestSpace.length==processSize)bestSpace.type=1;
			else {
				Segment New=new Segment();
				New.start=bestSpace.start+processSize;
				New.length=bestSpace.length-processSize;
				New.type=0;
				bestSpace.type=1;
				bestSpace.length=processSize;
				
				if(bestSpace.next==null){
					New.previous=bestSpace;
					bestSpace.next=New;
				}
				else {
					New.previous=bestSpace;
					bestSpace.next.previous=New;
					New.next=bestSpace.next;
					bestSpace.next=New;
				}
			}
		}
	}
	
	public void worstfit(double processSize){
		Segment current=first;
		Segment worstSpace=new Segment();
		worstSpace.length=-1.0;
		do{
			if(current.type==0 && current.length>=processSize){
				if(current.length>worstSpace.length)worstSpace=current;
			}
		}while((current=current.next)!=null);
		if(worstSpace.length==-1.0)JOptionPane.showMessageDialog(null, "Suitable space not found!!!");
		else {
			if(worstSpace.length==processSize)worstSpace.type=1;
			else {
				Segment New=new Segment();
				New.start=worstSpace.start+processSize;
				New.length=worstSpace.length-processSize;
				New.type=0;
				worstSpace.type=1;
				worstSpace.length=processSize;
				
				if(worstSpace.next==null){
					New.previous=worstSpace;
					worstSpace.next=New;
				}
				else {
					New.previous=worstSpace;
					worstSpace.next.previous=New;
					New.next=worstSpace.next;
					worstSpace.next=New;
				}
			}
		}
	}
	
	public void insertProcess(int startFrom,int length){
		Segment current=first;
		Segment New =new Segment();
		while(current.next!=null){
			if(current.type==0&&current.start<=startFrom&&(current.length-startFrom+current.start)>=length)break;
			current=current.next;
		}
		if(current.type==0&&current.start<=startFrom&&(current.length-startFrom+current.start)>=length){
			if(current.length==length)current.type=1;
			else if(current.start==startFrom){
				New.start=current.start+length;
				New.length=current.length-length;
				current.type=1;
				current.length=length;
				if(current.next==null){
					New.previous=current;
					current.next=New;
				}
				else {
					New.previous=current;
					current.next.previous=New;
					New.next=current.next;
					current.next=New;
				}
			}
			else {
				int length2=(int) (current.length-startFrom+current.start);
				current.length=startFrom-current.start;
				New.start=startFrom;
				if(current.next!=null){
					current.next.previous=New;
					New.next=current.next;
				}
				current.next=New;
				New.previous=current;
				New.length=length;
				if(length==length2)New.type=1;
				else {
					Segment New2=new Segment();
					New2.start=New.start+length;
					New2.length=length2-length;
					New.type=1;
					if(New.next==null){
						New2.previous=New;
						New.next=New2;
					}
					else {
						New2.previous=New;
						New.next.previous=New2;
						New2.next=New.next;
						New.next=New2;
					}
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Suitable space not found!!!");
		}
	}
	
	public void freed(double startFrom){
		Segment current=first;
		while(current.start!=startFrom&&current.next!=null){
			current=current.next;
		}
		if(current.start==startFrom&&current.type==1){
			current.type=0;
			if(current!=first&&current.previous.type==0){
				if(current.previous==first){
					first.length+=current.length;
					first.next=current.next;
					current.next.previous=first;
					current=first;
				}
				else {
					current.start=current.previous.start;
					current.length+=current.previous.length;
					current.previous.previous.next=current;
					current.previous=current.previous.previous;
				}
			}
			if(current.next!=first&&current.next.type==0){
				current.length+=current.next.length;
				current.next.next.previous=current;
				current.next=current.next.next;
			}
		}
	}
	
	public void defaultAllocation(){
		first=new Segment();
		double[] blocks={100.0,80.0,150.0,50.0,120.0,150.0,280.0,30.0,40.0};
		
		first.start=0; first.type=0;first.length=blocks[0];
		Segment current=first;	
		for(int i=1;i<9;i++){
			Segment next=new Segment();
			next.start=current.start+current.length;
			next.length=blocks[i];
			next.type=1-current.type;
			next.previous=current;
			current.next=next;current=next;
		}
		current.next=null;
		currentPos=first;
		repaint();
	}
	
	public void randomAllocation(){
		Vector<Integer> blocks=new Vector<Integer>();
		Random randomGen=new Random();
		int temp=0,total=0;
		while(total<memorySize){
			temp=1+randomGen.nextInt(Math.min(150, (int)memorySize - total));
			blocks.addElement(temp);
			total+=temp;
		}
		first=new Segment();first.start=0; first.type=0;
		first.length=blocks.firstElement();
		Segment current=first;	
		for(int i=1;i<blocks.size();i++){
			Segment next=new Segment();
			next.start=current.start+current.length;
			next.length=blocks.elementAt(i);
			next.type=1-current.type;
			next.previous=current;
			current.next=next;current=next;
		}
		current.next=null;
		currentPos=first;
		repaint();
	}
	
	public void reset(){
		first=new Segment(0,memorySize);
		currentPos=first;
		repaint();
	}
	
}


