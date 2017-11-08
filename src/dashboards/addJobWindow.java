package dashboards;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import jobschedule.JobSchedulingBlock;

import java.awt.*;
import java.awt.event.*;
public class addJobWindow /*implements ActionListener*/{
	JFrame window;
	
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
	
	public void init(){
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		String jobTypeList[] = {"sampleJobType", "sampleJobType1", "sampleJobType2"};
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
		
		window.getContentPane().add(BorderLayout.NORTH, header);
		window.getContentPane().add(BorderLayout.CENTER, main);
		window.getContentPane().add(BorderLayout.SOUTH, footer);
		window.setSize(400,300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
		window.setVisible(true);
	}
	
	public static void main(String[] args){
		addJobWindow ajw = new addJobWindow();
		ajw.init();
	}
	class submitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String jobTypeInput = (String)jobType.getSelectedItem();
			boolean walkInInput = walkIn.isSelected();
			int clientIDInput = Integer.parseInt(clientID.getText());
			int carIDInput = Integer.parseInt(carID.getText());
			
			JobSchedulingBlock jsb = new JobSchedulingBlock(jobTypeInput, walkInInput, clientIDInput, carIDInput);
			
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			
			//if client or car do not exist in the database, pop up error message: implement later
			
			
			
		}
	}
}
