import java.awt.EventQueue;

import javafx.scene.text.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;


public class cloudGUI {

	private JFrame frame;
	
	private JTextField textField_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cloudGUI window = new cloudGUI();
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
	public cloudGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.getContentPane().setForeground(Color.CYAN);
		frame.setBounds(100, 100, 936, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
	JButton btnNewButton = new JButton("Query 1");
	btnNewButton.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton.setForeground(new Color(0, 0, 0));
	btnNewButton.setBackground(Color.ORANGE);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			SpeedQuery sq = new SpeedQuery();
		
			try {
				 int result = sq.createQuery(sdb);
		            textField_1.setText("Count of High Speeds > 100  is " + Integer.toString(result));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}
	});
	btnNewButton.setBounds(254, 261, 115, 29);
	frame.getContentPane().add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Query 2");
	btnNewButton_1.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton_1.setBackground(Color.ORANGE);
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			TotalVolume sq = new TotalVolume();
			try {
				 int result = sq.createQuery(sdb);
		            textField_1.setText("Total volume for Foster NB station is " + Integer.toString(result));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		}
	});
	btnNewButton_1.setBounds(142, 312, 170, 29);
	frame.getContentPane().add(btnNewButton_1);
	
	JButton btnNewButton_2 = new JButton("Query 3");
	btnNewButton_2.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton_2.setBackground(Color.ORANGE);
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			SingleStation sq = new SingleStation();
			try {
				 float result = sq.createQuery(sdb);
		            textField_1.setText("Travel Time FosterNB for 5-min interval is " +Float.toString(result)+"secs");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	});
	btnNewButton_2.setBounds(323, 312, 169, 29);
	frame.getContentPane().add(btnNewButton_2);
	
	JButton btnNewButton_3 = new JButton("Query 4 Part 1");
	btnNewButton_3.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton_3.setBackground(Color.ORANGE);
	btnNewButton_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			PeakTravelTime sq = new PeakTravelTime();
			try {
				 float result = sq.createQuery1(sdb);
		            textField_1.setText("Travel Time between 7-9AM FosterNB is " + Float.toString(result)+"secs");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	});
	btnNewButton_3.setBounds(143, 391, 169, 29);
	frame.getContentPane().add(btnNewButton_3);
	
	JButton btnNewButton_7 = new JButton("Query 4 Part 2");
	btnNewButton_7.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton_7.setBackground(Color.ORANGE);
	btnNewButton_7.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			PeakTravelTime sq = new PeakTravelTime();
			try {
				 float result = sq.createQuery1(sdb);
		            textField_1.setText("Travel Time between 4-6PM FosterNB is " + Float.toString(result)+"secs");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	});
	btnNewButton_7.setBounds(323, 391, 169, 29);
	frame.getContentPane().add(btnNewButton_7);
	
	JButton btnNewButton_4 = new JButton("Query 5 Part 1");
	btnNewButton_4.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton_4.setBackground(Color.ORANGE);
	btnNewButton_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			PeakAverage_I205NB sq = new PeakAverage_I205NB();
			try {
				 float result = sq.createQuery1(sdb);
		            textField_1.setText("TravelTime between 7-9AM for I-205NB is " + Float.toString(result)+ "mins");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnNewButton_4.setBounds(143, 483, 169, 29);
	frame.getContentPane().add(btnNewButton_4);
	
	JButton btnNewButton_6 = new JButton("Query 5 Part 2");
	btnNewButton_6.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton_6.setBackground(Color.ORANGE);
	btnNewButton_6.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			PeakAverage_I205NB sq = new PeakAverage_I205NB();
		
			try {
				 float result = sq.createQuery2(sdb);
		            textField_1.setText("TravelTime between 4-6PM for I-205NB is "+ Float.toString(result)+"mins");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	});
	btnNewButton_6.setBounds(323, 483, 169, 29);
	frame.getContentPane().add(btnNewButton_6);
	
	
	JButton btnNewButton_5 = new JButton("Query 6");
	btnNewButton_5.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 20));
	btnNewButton_5.setBackground(Color.ORANGE);
	btnNewButton_5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AmazonSimpleDB sdb =null;
			sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);	
			
			RouteFinding sq = new RouteFinding();
			
			try {
				 String result = sq.createQuery(sdb);
		            textField_1.setText("Johnson Creek to Columbia Blvd on I-205NB is " + result);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	});
	btnNewButton_5.setBounds(254, 557, 115, 29);
	frame.getContentPane().add(btnNewButton_5);
	
	textField_1 = new JTextField(20);
	textField_1.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 24));
	textField_1.setBounds(585, 283, 918, 232);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	JLabel lblNewLabel = new JLabel("Output of Query");
	lblNewLabel.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 30));
	lblNewLabel.setBounds(645, 220, 254, 49);
	frame.getContentPane().add(lblNewLabel);
	
	JLabel lblSimpledbProject = new JLabel("SimpleDB project");
	lblSimpledbProject.setFont(new java.awt.Font("Times New Roman", java.awt.Font.ITALIC, 45));
	lblSimpledbProject.setBounds(347, 49, 363, 92);
	frame.getContentPane().add(lblSimpledbProject);
	
	JLabel label = new JLabel("");
	Image img = new ImageIcon(this.getClass().getResource("/cloud-icon.png")).getImage();
	label.setIcon(new ImageIcon(img));
	label.setBounds(853, 16, 305, 226);
	frame.getContentPane().add(label);
	
	
	
	
	
	
	
	
	
	
	}
}
