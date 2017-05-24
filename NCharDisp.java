/** to draw background and put low-res character  
 */ 
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class NCharDisp extends Frame{
	
	Image bimg1;
	Image off;
	Graphics offg;
	int x=0;
	int y=0;
	boolean drawflag=false;
	Button btn1,btn2,btn3;
	File f1=new File("flower.txt");
	N80Char chr[]=new N80Char[20];
	public NCharDisp(String title){
		super(title);
	}
	public static void main(String[] args){
		NCharDisp gwrite=new NCharDisp("graph write");
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
		for(int i=0;i<=19;i++){
			chr[i]=new N80Char();
		}		
		setSize(1000,600);
		setVisible(true);

		off=createImage(1000,600);
		offg=off.getGraphics();
		bimg1=Toolkit.getDefaultToolkit().getImage("pic3.jpg");
		for(int i=0;i<=19;i++){
			chr[i].load(f1);
		}

				 
		repaint();
		setBackground(Color.black);
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==0x27){x++;}
				if(ke.getKeyCode()==0x25){x--;}
				if(ke.getKeyCode()==0x26){y--;}
				if(ke.getKeyCode()==0x28){y++;}


				repaint();
			}
		});
			
	
	
	
	}
	public void update(Graphics g){
		paint(g);
	}
	public void paint(Graphics g){
		offg.drawImage(bimg1,0,0,this);
		for(int i=0;i<=19;i++){

			N80Graph.N80Put(offg,chr[i],i%5*20+x,i/5*20+y);
		}

//		g.drawImage(bimg1,0,0,this);

//		N80Graph.N80Put(g,chr1,x,y);
//		offg.setColor(Color.white);
		g.drawImage(off,0,0,this);
		

	}
}