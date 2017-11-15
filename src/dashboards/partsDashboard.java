/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connector.OrderDatabaseConnector;
import connector.PartDatabaseConnector;
import dashboards.PartsOrderDash.createOrderButtonListener;
import dashboards.PartsOrderDash.orderArrivedButtonListener;
import dashboards.PartsOrderDash.orderTableMouseClicked;

public class partsDashboard extends Dashboard{
	JPanel main;
	JPanel infoDisplay;
	JPanel infoDisplay2;
	JPanel functions;
	JPanel functions2;
	JPanel header1;
	JPanel header2;
	
	JLabel activeOrders;
	JLabel inventory;
	
	JScrollPane orderScroll;
	JScrollPane partScroll;
	
	JTable orderTable;
	DefaultTableModel tableModel;
	orderTableMouseClicked otm;
	
	JTable partsTable;
	DefaultTableModel partsTableModel;
	partsTableMouseClicked ptm;
	
	JButton createOrder;
	JButton orderArrived;
	JButton updateStock;
	JButton deletePart;
	JButton newPart;
	
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
		header1 = new JPanel();
		header1.setBackground(Color.YELLOW);
		header1.setBorder(new EmptyBorder(10,10,10,10));
        activeOrders = new JLabel("Active orders");
        header1.add(activeOrders);
		header1.setMinimumSize(new Dimension(dim.width-108,40));
		header1.setMaximumSize(new Dimension(dim.width-108,40));
        main.add(header1);
        
		orderScroll = new JScrollPane(orderTable);
		orderScroll.setMinimumSize(new Dimension((dim.width-108), 200));
		orderScroll.setMaximumSize(new Dimension((dim.width-108), 200));
        orderScroll.setBounds(0, 0, (dim.width-108), 300);
        
		infoDisplay = new JPanel();
		infoDisplay.setLayout(null);
		infoDisplay.setMinimumSize(new Dimension(dim.width-108, 200));
		infoDisplay.setMaximumSize(new Dimension(dim.width-108, 200));
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
		
		PartDatabaseConnector pdc = new PartDatabaseConnector();
		try{
			partsTableModel = buildTableModel(pdc.getAllParts());
			partsTable = new JTable(partsTableModel);
			ptm = new partsTableMouseClicked();
			partsTable.addMouseListener(ptm);
		
		}catch(SQLException e){}
		header2 = new JPanel();
		header2.setBackground(Color.YELLOW);
		header2.setBorder(new EmptyBorder(10,10,10,10));
        inventory = new JLabel("Inventory");
        header2.add(inventory);
		header2.setMinimumSize(new Dimension(dim.width-108,40));
		header2.setMaximumSize(new Dimension(dim.width-108,40));
        main.add(header2);
        
		partScroll = new JScrollPane(partsTable);
		partScroll.setMinimumSize(new Dimension((dim.width-108), 300));
		partScroll.setMaximumSize(new Dimension((dim.width-108), 300));
        partScroll.setBounds(0, 0, (dim.width-108), 300);
        
		infoDisplay2 = new JPanel();
		infoDisplay2.setLayout(null);
		infoDisplay2.setMinimumSize(new Dimension(dim.width-108, 200));
		infoDisplay2.setMaximumSize(new Dimension(dim.width-108, 200));
		infoDisplay2.add(partScroll);
		infoDisplay2.setBackground(Color.GREEN);
		main.add(infoDisplay2);
		
		updateStock = new JButton("Update inventory");
		updateStock.addActionListener(new updateStockButtonListener());
		
		deletePart = new JButton("Delete part type");
		deletePart.addActionListener(new deletePartButtonListener());
		
		newPart = new JButton("Create new part type");
		newPart.addActionListener(new newPartButtonListener());
		
		functions2 = new JPanel();
		functions2.add(updateStock);
		functions2.add(deletePart);
		functions2.add(newPart);
		functions2.setMinimumSize(new Dimension(dim.width-108, 40));
		functions2.setMaximumSize(new Dimension(dim.width-108, 40));
		main.add(functions2);
		
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
	
	class createOrderButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			createOrderWindow cow = new createOrderWindow();
			cow.init();
		}
	}
	class updateStockButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//action
		}
	}
	class deletePartButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//action
		}
	}
	class newPartButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//action
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
	
	public class partsTableMouseClicked implements MouseListener{
		int partID;
		int row;
		@Override
		public void mouseClicked(MouseEvent e) {
	        row = partsTable.getSelectedRow();
	        partID = (int)partsTable.getModel().getValueAt(row, 0);
	        
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
