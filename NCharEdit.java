/** to edit low-res character and save
 */ 
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class NCharEdit extends Frame{
	int color[][]=new int[100][100];
	int lineNum=1;
	int curcolor=0;
	int height,width;
	int i,j,x,y;
	boolean drawflag=false;
	Button btn1,btn2,btn3;
	final JFileChooser myFileChooser=new JFileChooser();
	public NCharEdit(String title){
		super(title);
	}
	public static void main(String[] args){
		NCharEdit gwrite=new NCharEdit("graph write");
		gwrite.init();
		gwrite.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
//				this.stop();
//				this.destroy();
				System.exit(0);
			}
		});	
	} 
	public void init(){
    		while(width<1 || width>100){ 
			try{  
				String result = 
		JOptionPane.showInputDialog("input width(1-99)");
				width=Integer.parseInt(result);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}    while(height<1 || height>100){ 
			try{  
				String result = 
		JOptionPane.showInputDialog("input height(1-99)");
				height=Integer.parseInt(result);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		for(i=0;i<=height;i++){
			for(j=0;j<=width;j++){
				color[j][i]=8;
		//color=8 => gray(transparent)
				repaint();
			}
		}
		setSize(width*8,height*8+100);
		setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		btn1=new Button("load");
		btn2=new Button("save");
//		btn3=new Button("undo");
		Choice colMenu=new Choice();
		colMenu.addItem("black");
		colMenu.addItem("red");
		colMenu.addItem("blue");
		colMenu.addItem("magenta");
		colMenu.addItem("green");
		colMenu.addItem("cyan");
		colMenu.addItem("yellow");		 
		colMenu.addItem("white");		 
		colMenu.addItem("transparent");		 
		add(btn1);add(btn2);add(colMenu);
		setVisible(true);
		repaint();
		setBackground(Color.white);
	
		addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
			if(lineNum<99){lineNum++;}

			x=me.getX();
			y=me.getY();
			x=(int)(x/8);
			if(y>80){y=(int)((y-100)/8);color[x][y]=curcolor;
}			repaint();
		}
		

		});
		colMenu.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
			String choise=(String)e.getItem();
			if(choise.equals("red")){curcolor=1;}
			if(choise.equals("blue")){curcolor=2;}
			if(choise.equals("magenta")){curcolor=3;}
			if(choise.equals("green")){curcolor=4;}
			if(choise.equals("cyan")){curcolor=5;}
			if(choise.equals("yellow")){curcolor=6;}
			if(choise.equals("white")){curcolor=7;}
			if(choise.equals("black")){curcolor=0;}
			if(choise.equals("transparent")){curcolor=8;}
			}
		});

		btn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int intret=myFileChooser.showOpenDialog(
					NCharEdit.this);
				File fname = myFileChooser.getSelectedFile();
				if(intret==JFileChooser.APPROVE_OPTION){
					try{
						FileReader fRead=new FileReader(fname.getAbsolutePath());
						BufferedReader strRead=new BufferedReader(fRead);
						width=strRead.read();
						height=strRead.read();
							for(i=0;i<=height;i++){
								for(j=0;j<=width;j++){
									color[j][i]=strRead.read();
								}
							}

							strRead.close();
							repaint();
					}catch(FileNotFoundException e){
						e.printStackTrace();
					}catch(IOException e){
						e.printStackTrace();
					}
				}

			}
		}); 

		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int intret=myFileChooser.showSaveDialog(
					NCharEdit.this);
			if(intret==JFileChooser.APPROVE_OPTION){
		try{
			File fname = myFileChooser.getSelectedFile();
//			String strFile=(String)fname;
//			if(strFile.indexOf(".")==-1){fname+=".txt";}
			FileWriter fWrite=new FileWriter(fname.getAbsolutePath());
			BufferedWriter strWrite=new BufferedWriter(fWrite);
			strWrite.write(width);
			strWrite.write(height);
			for(i=0;i<=height;i++){
				for(j=0;j<=width;j++){
				strWrite.write(color[j][i]);
				}
			}


			strWrite.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}


			}}
		}); 
/*
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				curcolor
				repaint();
			}
		}); 
*/
	}
	public void paint(Graphics g){
		g.setColor(Color.white);
		for(i=0;i<=height;i++){
			for(j=0;j<=width;j++){
				if(color[j][i]==1){g.setColor(Color.red);}
				if(color[j][i]==2){g.setColor(Color.blue);}
				if(color[j][i]==3){g.setColor(Color.magenta);}
				if(color[j][i]==4){g.setColor(Color.green);}
				if(color[j][i]==5){g.setColor(Color.cyan);}
				if(color[j][i]==6){g.setColor(Color.yellow);}
				if(color[j][i]==7){g.setColor(Color.white);}
				if(color[j][i]==0){g.setColor(Color.black);}
				if(color[j][i]==8){g.setColor(Color.gray);}
				g.fillRect(j*8,i*8+100,8,8);
			}
		
		}
	}
}