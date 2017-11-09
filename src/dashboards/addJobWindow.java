package dashboards;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import jobschedule.JobScheduler;
import jobschedule.JobSchedulingBlock;

import java.awt.*;
import java.awt.event.*;
public class addJobWindow /*implements ActionListener*/{
	JobScheduler js;
	JFrame aWindow;
	
	JPanel main;
	JPanel header;
	JPanel textAndField1;
	JPanel textAndField2;
	JPanel textAndField3;
	JPanel footer;
	
	JLabel jobTypeLabel;
	JLabel addNewJob;
	JLabel clientIDLabel;
	JLabel carIDLabel;
	
	JButton submitButton;
	JComboBox<String> jobType;
	JCheckBox walkIn;
	JTextField clientID;
	JTextField carID;
	
	public addJobWindow(JobScheduler js){
		this.js = js;
	}
	
	public void init(){
		aWindow = new JFrame();
		aWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		header = new JPanel();
		header.setBorder(new EmptyBorder(10,10,10,10));
		header.setBackground(Color.gray);
		
		footer = new JPanel();
		footer.setBorder(new EmptyBorder(5,5,5,5));
		footer.setBackground(Color.gray);
		
		addNewJob = new JLabel("Add a new job");
		addNewJob.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.setBorder(new EmptyBorder(10,10,10,10));
		
		textAndField1 = new JPanel();
		textAndField2 = new JPanel();
		textAndField3 = new JPanel();
		
		walkIn = new JCheckBox("Walk in");
		walkIn.setSelected(false);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new submitButtonListener());
		
		jobTypeLabel = new JLabel("Job type");
		String jobTypeList[] = {"sampleJobType", "sampleJobType2", "sampleJobType3"};
		jobType = new JComboBox<String>(jobTypeList);
		jobType.setSelectedIndex(0);
		
		clientIDLabel = new JLabel("Client ID");
		clientID = new JTextField("Enter client ID");
		clientID.setColumns(12);
		
		carIDLabel = new JLabel("  Car ID");
		carID = new JTextField("Enter car ID");
		carID.setColumns(12);
		
		textAndField1.add(jobTypeLabel);
		textAndField1.add(jobType);
		
		textAndField2.add(clientIDLabel);
		textAndField2.add(clientID);
		
		textAndField3.add(carIDLabel);
		textAndField3.add(carID);
		
		header.add(addNewJob);
		footer.add(submitButton);
		
		main.add(textAndField1);
		main.add(textAndField2);
		main.add(textAndField3);
		main.add(walkIn);
		
		aWindow.getContentPane().add(BorderLayout.NORTH, header);
		aWindow.getContentPane().add(BorderLayout.CENTER, main);
		aWindow.getContentPane().add(BorderLayout.SOUTH, footer);
		aWindow.setSize(400,300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		aWindow.setLocation(dim.width/2-aWindow.getSize().width/2, dim.height/2-aWindow.getSize().height/2);
		aWindow.setVisible(true);
	}
	
	class submitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String jobTypeInput = (String)jobType.getSelectedItem();
			boolean walkInInput = walkIn.isSelected();
			int clientIDInput = Integer.parseInt(clientID.getText());
			int carIDInput = Integer.parseInt(carID.getText());

			js.scheduleJob(new JobSchedulingBlock(jobTypeInput, walkInInput, clientIDInput, carIDInput));
			
			aWindow.setVisible(false);
			
			
			//if client or car do not exist in the database, pop up error message: implement later
			
			
		}
	}
}
