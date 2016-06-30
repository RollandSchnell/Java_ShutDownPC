import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;



public class Main extends JFrame{
	
	
	JButton ok = new JButton("OK");
	JTextField area = new JTextField(15);
	JLabel label = new JLabel("Enter the shut down count down in minutes.");
	JLabel label2 = new JLabel("");
	int time = 0;
	int second = 59;
	
	
	public Main() {
		
		this.setTitle("ShutDown");
	    this.setLayout(new FlowLayout());
		this.setSize(300,125);
		label.setFont(new Font("Tahoma",Font.BOLD,12));
		this.add(label);
		this.add(area);
		this.add(ok);
		label2.setFont(new Font("Tahoma",Font.BOLD,15));
		this.add(label2);
		this.setLocation(new Point(550,250));
		ok.addActionListener(e -> {
			
			shutDown();
		});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	
	
	public void shutDown() {
		
		 int minutes = Integer.valueOf(area.getText());
		 int sec = minutes * 60;
		 time = sec;
	     Timer timer = new Timer(); 
        
	        timer.schedule(new TimerTask() {

	            @Override
	            public void run() {
	                ProcessBuilder processBuilder = new ProcessBuilder("shutdown",
	                        "/s");
	                try {
	                    processBuilder.start();
	                } catch (IOException e) {
	                	
	                	JOptionPane.showMessageDialog(new JFrame(), "Error !!");
	                    throw new RuntimeException(e);
	                }
	            }

	        }, minutes * 60 * 1000);
	        
	 area.setEditable(false);
	 area.setText("");
	 area.setBackground(new Color(140,140,140));
     label2.setText(timeFormat(time));
    
     countDown(sec);
		
	}
	
	
	public void countDown(int sec) {
		
		
		 int delay = 1000;
	     int period = 1000;
	     Timer count = new Timer();
		
	     
	     
		count.scheduleAtFixedRate(new TimerTask() {

            public void run() {
            	
            	if(time == 1) {
            		
            		count.cancel();
            		
            	}
            	
            second--;
            
            	
            	label2.setText(timeFormat(setInterval()));
               
               
            }
        }, delay, period);
	}
	
	public int setInterval() {
		
		if(time == 1) {
			
			return 0;
			
		} else {
			
			return --time;
		}
	}
		
	public String timeFormat(int sec) {
		
		
		int hr = 0;
		int min = 0;
	
	    if(time == 1) {
	    	
	    	return("Shutting Down !!!");
	    	
	    }
		 
        if(sec % 60 == 0) {
			
			sec -= 1;
		}
        
        hr = sec / 3600;
		min = sec / 60 -  hr * 60 ;
		
		if(second == 0 && min != 0) {
			
			min -= 1;
			second = 59;
			
		} else if(min == 0 && second == 0) {
			
			second = 59;
			hr -= 1;
			min = 59;
			
		} 
		
		return hr + ":" + min +":" + second + " left ";
		
		
	}
 
		    public static void main(String[] args) {

		    	
		    	try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    
					JOptionPane.showMessageDialog(new JFrame(), "Error !!");
				}
		    	
		    	
		       new Main();
		    }
		 

}
