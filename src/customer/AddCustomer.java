package customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import customer.AddCustomer;
import customer.ViewCustomer;
import model.Supplier;
import network.SQLService;
import network.response.QueryResponse;
import orders.CreateOrder;
import products.AddProduct;
import purchase.CreatePurchase;
import supplier.AddSupplier;
import supplier.ViewSupplier;
import view.ViewStocks;

public class AddCustomer extends javax.swing.JFrame {

	public AddCustomer() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		setLayout(null);
		initViews();
		setButtonLister();
		setTextFieldListener();
		service = new SQLService();
		service.getConnection();

	}

	public static void main(String[] args) {
		new AddCustomer().setVisible(true);

	}

	public void initViews() {
		setMenuBar();
		tvAddSupplier = new JLabel("Add Customer");
		tvAddSupplier.setBounds(564, 5, 300, 54);
		tvAddSupplier.setFont(new Font("Times New Roman", 1, 36));
		tvAddSupplier.setForeground(Color.RED);
		add(tvAddSupplier);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(185, 75, 997, 585);
		Border redBorder = BorderFactory.createLineBorder(Color.red);
		panel1.setBorder(redBorder);
		add(panel1);

		jScrollPane1 = new javax.swing.JScrollPane();
	

		firstNameLabel = new JLabel("First Name : ");
		lastNameLabel = new JLabel("Last Name : ");
		emailLabel = new JLabel("Email : ");
		mobileLabel = new JLabel("Mobile : ");
		addressLabel = new JLabel("Address : ");
		detailsLabel=new JLabel("Details : ");
		remarksLabel = new JLabel("Remarks : ");

		firstNameLabel.setBounds(140, 140, 150, 30);
		firstNameLabel.setFont(new Font("Times New Roman", 1, 20));
		lastNameLabel.setBounds(140, 210, 150, 30);
		lastNameLabel.setFont(new Font("Times New Roman", 1, 20));
		emailLabel.setBounds(140, 280, 150, 30);
		emailLabel.setFont(new Font("Times New Roman", 1, 20));
		mobileLabel.setBounds(140, 350, 150, 30);
		mobileLabel.setFont(new Font("Times New Roman", 1, 20));
		addressLabel.setBounds(540, 100, 150, 30);
		addressLabel.setFont(new Font("Times New Roman", 1, 20));
		detailsLabel.setBounds(540, 220, 150, 30);
		detailsLabel.setFont(new Font("Times New Roman", 1, 20));
		remarksLabel.setBounds(540, 340, 150, 30);
		remarksLabel.setFont(new Font("Times New Roman", 1, 20));
		panel1.add(firstNameLabel);
		panel1.add(lastNameLabel);
		panel1.add(emailLabel);
		panel1.add(mobileLabel);
		panel1.add(addressLabel);
		panel1.add(detailsLabel);
		panel1.add(remarksLabel);
		
		tvFirstName = new JTextField();
		tvLastName = new JTextField();
		tvEmail = new JTextField();
		tvMobile = new JTextField();
		tvAddress = new JTextArea();
		tvDetails=new JTextField();
		tvRemarks=new JTextField();
		tvFirstName.setBounds(290, 140, 150, 30);
		tvLastName.setBounds(290, 210, 150, 30);
		tvEmail.setBounds(290, 280, 150, 30);
		tvMobile.setBounds(290, 350, 150, 30);
		Border blackBorder = BorderFactory.createLineBorder(Color.gray);
		tvAddress.setBounds(640, 100, 220, 80);
		tvAddress.setBorder(blackBorder);
		tvDetails.setBounds(640, 220, 220, 80);
		tvRemarks.setBounds(640, 340, 220, 80);
		panel1.add(tvFirstName);
		panel1.add(tvLastName);
		panel1.add(tvEmail);
		panel1.add(tvMobile);
		panel1.add(tvAddress);
		panel1.add(tvDetails);
		panel1.add(tvRemarks);

		btnAdd = new JButton("Add Customer");
		btnAdd.setBounds(457, 480, 100, 40);
		btnAdd.setBackground(new Color(0, 255, 153));
		btnAdd.setFont(new Font("Times New Roman", 1, 14));
		btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		Border bevelBorder=BorderFactory.createBevelBorder(DEFAULT_CURSOR);
		btnAdd.setBorder(bevelBorder);
		panel1.add(btnAdd);

	}

	public void setMenuBar() {
		menuBar = new javax.swing.JMenuBar();
		menuProduct = new javax.swing.JMenu();
		menuSupplier = new javax.swing.JMenu();
		menuAddSupplier = new javax.swing.JMenuItem();
		menuViewSupplier = new javax.swing.JMenuItem();
		menuCustomers = new javax.swing.JMenu();
		menuAddCustomer = new javax.swing.JMenuItem();
		menuViewCustomer = new javax.swing.JMenuItem();
		menuTransactions = new javax.swing.JMenu();
		menuCreatePurchase = new javax.swing.JMenuItem();
		menuCreaterOrder = new javax.swing.JMenuItem();
		menuView = new javax.swing.JMenu();
		menuStocks = new javax.swing.JMenuItem();
		menuAddProduct=new JMenuItem();

		menuBar.setBackground(new java.awt.Color(255, 0, 0));
		menuBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null,
				new java.awt.Color(255, 0, 0), null, null));
		menuBar.setForeground(new java.awt.Color(255, 0, 0));
		menuBar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		menuBar.setPreferredSize(new java.awt.Dimension(325, 30));
		menuBar.setRequestFocusEnabled(false);

		menuProduct.setForeground(new java.awt.Color(255, 255, 255));
		menuProduct.setText("Product");
		menuAddProduct.setText("Add Product");
		menuProduct.add(menuAddProduct);
		menuBar.add(menuProduct);

		menuSupplier.setForeground(new java.awt.Color(255, 255, 255));
		menuSupplier.setText("Suppliers");

		menuAddSupplier.setText("Add Supplier");

		menuSupplier.add(menuAddSupplier);

		menuViewSupplier.setText("View Supplier");
		menuSupplier.add(menuViewSupplier);

		menuBar.add(menuSupplier);

		menuCustomers.setForeground(new java.awt.Color(255, 255, 255));
		menuCustomers.setText("Customers");

		menuAddCustomer.setText("Add Customer");
		menuCustomers.add(menuAddCustomer);

		menuViewCustomer.setText("View Customer");

		menuCustomers.add(menuViewCustomer);

		menuBar.add(menuCustomers);

		menuTransactions.setForeground(new java.awt.Color(255, 255, 255));
		menuTransactions.setText("New Purchase/Order");

		menuCreatePurchase.setText("Create Purchase");
		menuTransactions.add(menuCreatePurchase);

		menuCreaterOrder.setText("Create Order");
		menuTransactions.add(menuCreaterOrder);

		menuBar.add(menuTransactions);

		menuView.setForeground(new java.awt.Color(255, 255, 255));
		menuView.setText("View");

		menuStocks.setText("Stocks");
		menuView.add(menuStocks);

		menuBar.add(menuView);

		setJMenuBar(menuBar);

		setMenuListener();

	}
	
	public void setButtonLister() {
	
		 btnAdd.addActionListener(e->{
			 if(ValidateFields()) {
			 String firstName=tvFirstName.getText();
		    	String lastName = tvLastName.getText();
		    	String email = tvEmail.getText();
		    	String mobile = tvMobile.getText();
		    	String address = tvAddress.getText();
		    	String details = tvDetails.getText();
		    	String remarks = tvRemarks.getText();
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		        LocalDateTime now = LocalDateTime.now();
		        String date=dtf.format(now);
		    	String query="INSERT INTO customers(first_name,last_name,email,mobile,address,created_date,updated_date,details,remarks) values("+"\'"+firstName+"\'"+",\'"+lastName+"\'"+",\'"+email+"\'"+",\'"+mobile+"\'"+",\'"+address+"\'"+",\'"+date+"\'"+",\'"+date+"\'"+",\'"+details+"\'"+",\'"+remarks+"\'"+")";
		    	System.out.println(query);
		    	QueryResponse qs=service.executeUpdate(query);
		    	if(qs.statusCode==1) {
		    		JOptionPane.showMessageDialog(null, "Customer Created SuccessFully","Info", JOptionPane.INFORMATION_MESSAGE, null);
		    		clearUpdateDetails();
		    	}else {
		    		JOptionPane.showMessageDialog(null, qs.message,"Error", JOptionPane.ERROR_MESSAGE, null);
		    	}
			 }
		 } );
		 }
		 
		 
		 
		 
	

	public void setMenuListener() {
		menuViewSupplier.addActionListener(e -> {
			menuViewSupplierActionPerformed(e);

		});
		menuAddProduct.addActionListener(e -> {
			menuAddProductActionPerformed(e);
		});
		menuAddSupplier.addActionListener(e -> {
			menuAddSupplierActionPerformed(e);
		});
		menuViewCustomer.addActionListener(e -> {
			menuViewCustomerActionPerformed(e);
		});
		menuStocks.addActionListener(e->{
			menuViewStocksActionPerformed(e);
		});
		menuCreatePurchase.addActionListener(e->{
			new CreatePurchase().setVisible(true);
			dispose();
		});
		menuCreaterOrder.addActionListener(e->{
			new CreateOrder().setVisible(true);
			dispose();
		});
	}


	

	public void menuViewSupplierActionPerformed(ActionEvent evt) {
		new ViewSupplier().setVisible(true);
		dispose();
	}

	public void menuAddProductActionPerformed(ActionEvent evt) {

		new AddProduct().setVisible(true);
		dispose();
	}
	public void menuAddSupplierActionPerformed(ActionEvent evt) {

		new AddSupplier().setVisible(true);
		dispose();
	}
	private void menuViewCustomerActionPerformed(ActionEvent evt) {

		
		new ViewCustomer().setVisible(true);
		this.dispose();
	}
	private void menuViewStocksActionPerformed(ActionEvent evt) {

		
		new ViewStocks().setVisible(true);
		this.dispose();
	}
	

	public void setTextFieldListener() {
//		
		
		
	}
	


	
	public void clearUpdateDetails() {
		tvFirstName.setText("");
		tvLastName.setText("");
		tvEmail.setText("");
		tvMobile.setText("");
		tvAddress.setText("");
		tvDetails.setText("");
		tvRemarks.setText("");
	}
	
	public boolean ValidateFields() {

        if(tvFirstName.getText()==null ||  tvFirstName.getText()=="" || tvFirstName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "First name should not be empty","Error", JOptionPane.ERROR_MESSAGE, null);
            return false;     
        }
    
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(tvEmail.getText());  
        if( matcher.matches()==false)  {
        	JOptionPane.showMessageDialog(null, "Invalid Email","Error", JOptionPane.ERROR_MESSAGE, null);
        	return false;
        }
        
        String regexMob = "^\\d{10}$";
        Pattern patternMob = Pattern.compile(regexMob);
        Matcher matcherMob = patternMob.matcher(tvMobile.getText());
        if( matcherMob.matches()==false)  {
        	JOptionPane.showMessageDialog(null, "Mobile Number should contain 10 digits","Error", JOptionPane.ERROR_MESSAGE, null);
        	return false;
        }
       
        if(tvAddress.getText()==null ||  tvAddress.getText()=="" || tvAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Address should not be empty","Error", JOptionPane.ERROR_MESSAGE, null);
            return false;     
        }
       
        return true;
	}     

	

	
//Menu
	private javax.swing.JMenu menuSupplier;
	private javax.swing.JMenu menuCustomers;
	private javax.swing.JMenu menuTransactions;
	private javax.swing.JMenu menuView;
	private javax.swing.JMenu menuProduct;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem menuStocks;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JMenuItem menuAddCustomer;
	private javax.swing.JMenuItem menuAddSupplier;
	private javax.swing.JMenuItem menuCreatePurchase;
	private javax.swing.JMenuItem menuCreaterOrder;
	private javax.swing.JMenuItem menuViewCustomer;
	private javax.swing.JMenuItem menuViewSupplier;
private JMenuItem menuAddProduct;
	// Panels

	private JPanel panel1;

//label
	private JLabel tvAddSupplier;
	private javax.swing.JLabel addressLabel;
	private javax.swing.JLabel emailLabel;
	private javax.swing.JLabel firstNameLabel;
	private javax.swing.JLabel lastNameLabel;
	private javax.swing.JLabel mobileLabel;;
	private JLabel detailsLabel ;
	private JLabel remarksLabel;

	// Textfields
	private javax.swing.JTextArea tvAddress;
	private javax.swing.JTextField tvEmail;
	private javax.swing.JTextField tvFirstName;
	private javax.swing.JTextField tvLastName;
	private javax.swing.JTextField tvMobile;;
	private javax.swing.JTextField tvDetails;
	private javax.swing.JTextField tvRemarks;

	// Buttons
	private javax.swing.JButton btnAdd;


	// sql
	private SQLService service;

	

}
