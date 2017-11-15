package dashboards;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class Dashboard {
	public JFrame window;
	
	JPanel header;
	JPanel navigationBar;
	
	JLabel mass;
	
	JButton jobs;
	JButton clients;
	JButton orders;
	JButton parts;
	
	public Dashboard(){
		init();
	}
	
	public void init(){
		window = new JFrame("MASS");
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(dim.width, dim.height);
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
		
		header = new JPanel();
		header.setBorder(new EmptyBorder(10,10,10,10));
		header.setBackground(Color.gray);
		
		navigationBar = new JPanel();
		navigationBar.setMaximumSize(navigationBar.getPreferredSize());
		navigationBar.setLayout(new BoxLayout(navigationBar, BoxLayout.Y_AXIS));
		navigationBar.setBorder(new EmptyBorder(0,10,0,10));
		navigationBar.setBackground(Color.GRAY);
		
		mass = new JLabel("MASS: Mechanic Auto Shop Software");
		
		jobs = new JButton("Jobs");
		jobs.setMinimumSize(new Dimension(100,175));
		jobs.setMaximumSize(new Dimension(100,175));
		jobs.addActionListener(new jobsButtonListener());
		clients = new JButton("Clients");
		clients.setMinimumSize(new Dimension(100,175));
		clients.setMaximumSize(new Dimension(100,175));
		clients.addActionListener(new clientsButtonListener());
		orders = new JButton("Orders");
		orders.setMinimumSize(new Dimension(100,175));
		orders.setMaximumSize(new Dimension(100,175));
		orders.addActionListener(new ordersButtonListener());
		parts = new JButton("Parts");
		parts.setMinimumSize(new Dimension(100,175));
		parts.setMaximumSize(new Dimension(100,175));
		parts.addActionListener(new partsButtonListener());
		
		header.add(mass);
		navigationBar.add(jobs);
		navigationBar.add(clients);
		navigationBar.add(orders);
		navigationBar.add(parts);
		
		
		window.add(BorderLayout.NORTH, header);
		window.add(BorderLayout.WEST, navigationBar);
	}
	
	class jobsButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//close and launch jobs dash
			jobsDashboard2 jd= new jobsDashboard2();
			jd.init();
			window.setVisible(false);
		}
	}
	class clientsButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			clientsDashboard cd = new clientsDashboard();
			cd.setVisible(true);
			window.setVisible(false);
		}
	}
	class ordersButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//close and launch orders dash
			PartsOrderDash od = new PartsOrderDash();
			od.init();
		}
	}
	class partsButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//close and launch parts dash
		}
	}

}
