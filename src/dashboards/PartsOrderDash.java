package dashboards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connector.JobDatabaseConnector;
import connector.OrderDatabaseConnector;
import connector.PartDatabaseConnector;
import dashboards.jobsDashboard2.jobsTableMouseClicked;
import jobschedule.Mechanic;

public class PartsOrderDash extends Dashboard {
	
	JPanel main;
	JPanel infoDisplay;
	JPanel functions;
	
	JScrollPane orderScroll;
	
	JTable orderTable;
	DefaultTableModel tableModel;
	orderTableMouseClicked otm;
	
	JButton createOrder;
	JButton orderArrived;
	
	public void init(){
		super.init();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.setBackground(Color.gray);
		
		OrderDatabaseConnector odc = new OrderDatabaseConnector();
		try{
			tableModel = buildTableModel(odc.getAllOrders());
			orderTable = new JTable(tableModel);
			otm = new orderTableMouseClicked();
			orderTable.addMouseListener(otm);
		
		}catch(SQLException e){}
		orderScroll = new JScrollPane(orderTable);
		orderScroll.setMinimumSize(new Dimension((dim.width-108), 300));
		orderScroll.setMaximumSize(new Dimension((dim.width-108), 300));
        orderScroll.setBounds(0, 0, (dim.width-108), 300);
        
		infoDisplay = new JPanel();
		infoDisplay.setLayout(null);
		infoDisplay.setMinimumSize(new Dimension(dim.width-108, 300));
		infoDisplay.setMaximumSize(new Dimension(dim.width-108, 300));
		infoDisplay.add(orderScroll);
		infoDisplay.setBackground(Color.GREEN);
		main.add(infoDisplay);
		
		createOrder = new JButton("Create a new order");
		createOrder.addActionListener(new createOrderButtonListener());
		
		orderArrived = new JButton("Mark order as arrived");
		orderArrived.addActionListener(new orderArrivedButtonListener());
		
		functions = new JPanel();
		functions.add(createOrder);
		functions.add(orderArrived);
		functions.setMinimumSize(new Dimension(dim.width-108, 40));
		functions.setMaximumSize(new Dimension(dim.width-108, 40));
		main.add(functions);
		
		window.add(BorderLayout.CENTER, main);
		window.setVisible(true);
		
	}
	 public static DefaultTableModel buildTableModel(ResultSet rs)
	            throws SQLException {

	        ResultSetMetaData metaData = rs.getMetaData();

	        // names of columns
	        Vector<String> columnNames = new Vector<String>();
	        int columnCount = metaData.getColumnCount();
	        for (int column = 1; column <= columnCount; column++) {
	            columnNames.add(metaData.getColumnName(column));
	        }

	        // data of the table
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	        while (rs.next()) {
	            Vector<Object> vector = new Vector<Object>();
	            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	                vector.add(rs.getObject(columnIndex));
	            }
	            data.add(vector);
	        }

	        return new DefaultTableModel(data, columnNames);

	    }
	 
	public static void main(String[] args){
		PartsOrderDash pod = new PartsOrderDash();
		pod.init();
	}
	
	class createOrderButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			createOrderWindow cow = new createOrderWindow();
			cow.init();
		}
	}
	class orderArrivedButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			int orderID = otm.orderID;
			//update parts database and remove order from order database
			OrderDatabaseConnector odc = new OrderDatabaseConnector();
			PartDatabaseConnector pdc = new PartDatabaseConnector();
			int partID = odc.getPartID(orderID);
			int partQty = odc.getPartQuantity(orderID);
			int inStock = pdc.getPartQuantity(partID);
			pdc.updatePartQuantity(partID, inStock+partQty);
			
			odc.deleteOrder(orderID);
		
		}
	}
	
	public class orderTableMouseClicked implements MouseListener{
		int orderID;
		int row;
		@Override
		public void mouseClicked(MouseEvent e) {
	        row = orderTable.getSelectedRow();
	        orderID = (int)orderTable.getModel().getValueAt(row, 0);
	        
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
