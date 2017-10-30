/*
 * CAEZAR JOHNLERY T. SABIJON
 * BS IT - DATABASE MAJOR
 * 
 * TIMER JAVA GUI
 */



package timer_package;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TimerApp implements ActionListener  {

	private JFrame frame;
	private JTextField timeField;
	private JLabel setTime, timeCount, logo;
	private JLabel setTime_1;
	private JButton startButton;
	public static Thread thread;
	public Timer timer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					TimerApp window = new TimerApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TimerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("TIMER");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 315, 215);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		setTime = new JLabel("SET TIME:");
		setTime.setBounds(49, 98, 53, 14);
		frame.getContentPane().add(setTime);
		
		timeField = new JTextField();
		timeField.setBounds(110, 95, 127, 20);
		frame.getContentPane().add(timeField);
		timeField.setColumns(10);
		
		startButton = new JButton("START");
		startButton.setBounds(129, 126, 89, 23);
		frame.getContentPane().add(startButton);
		startButton.addActionListener(this);
		
		setTime_1 = new JLabel("TIME LEFT:");
		setTime_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		setTime_1.setBounds(49, 46, 63, 14);
		frame.getContentPane().add(setTime_1);
		
		timeCount = new JLabel();
		timeCount.setFont(new Font("Tahoma", Font.BOLD, 15));
		timeCount.setHorizontalAlignment(SwingConstants.CENTER);
		timeCount.setBounds(146, 28, 46, 46);
		frame.getContentPane().add(timeCount);
		
		logo = new JLabel("DEEDEERDEE");
		logo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		logo.setBounds(0, 162, 63, 14);
		frame.getContentPane().add(logo);
		
		
		runTime();
	}
	
	//sfx of the time remaining and when it stops (buzzer)
	public void ring(){
		try{
			File file = new File("D://workspace//TimerApp//res//fx//buzzer.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//function of when the timer runs and displays the time on the screen.
	@SuppressWarnings("static-access")
	public void runTime(){
		
		Thread dt = new Thread(){
			public void run(){
				
				String x = timeField.getText();
				int y = Integer.parseInt(x);
				
				for(int i = y; i >= 0; i--){
					try {
						timeCount.setText(" "+i);
						if(i <= 10 && i != 0){
							Toolkit.getDefaultToolkit().beep();
						}else if(i == 0){
							ring();
						}
						thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}	
		};
		dt.start();
	
	}
	
	//get the action from the start button then start the runTime() function
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == startButton){
			runTime();
		}
	}
}

