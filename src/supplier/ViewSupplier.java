package supplier;

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
import products.AddProduct;
import supplier.AddSupplier;
import view.ViewStocks;
import model.MyTableModel;

public class ViewSupplier extends javax.swing.JFrame {

	public ViewSupplier() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		setLayout(null);
		initViews();
		setButtonLister();
		setTextFieldListener();
		service = new SQLService();
		service.getConnection();
		fetchAllSuppliers();

	}

	public static void main(String[] args) {
		new ViewSupplier().setVisible(true);

	}

	public void initViews() {
		setMenuBar();
		tvViewSupplier = new JLabel("View Supplier");
		tvViewSupplier.setBounds(564, 5, 300, 54);
		tvViewSupplier.setFont(new Font("Times New Roman", 1, 36));
		tvViewSupplier.setForeground(Color.RED);
		add(tvViewSupplier);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(10, 75, 933, 585);
		Border redBorder = BorderFactory.createLineBorder(Color.red);
		panel1.setBorder(redBorder);
		add(panel1);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(953, 75, 400, 585);
		panel2.setBorder(redBorder);
		add(panel2);

		nameLabel = new JLabel("Name  : ");
		tvSearchBox = new JTextField();
		btnSearch = new JButton("Search");
		nameLabel.setBounds(300, 30, 80, 30);
		nameLabel.setFont(new Font("Times New Roman", 1, 18));
		panel1.add(nameLabel);

		tvSearchBox.setBounds(390, 30, 150, 30);
		panel1.add(tvSearchBox);

		btnSearch.setBounds(416, 70, 80, 30);
		btnSearch.setBackground(new Color(0, 255, 153));
		btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panel1.add(btnSearch);

		supplierTable = new JTable();
		jScrollPane1 = new javax.swing.JScrollPane();
		supplierTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		supplierTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		supplierTable.setForeground(new java.awt.Color(255, 0, 0));
		supplierTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Id", "Firstname" }));
		supplierTable.setRowHeight(30);
		supplierTable.setBounds(10, 120, 900, 400);
		jScrollPane1.setViewportView(supplierTable);
		jScrollPane1.setBounds(10, 120, 900, 400);

		panel1.add(jScrollPane1);
//    panel1.add();
//
//		String[][] datas = new String[][] {
//				{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//				{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//				{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//				{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//				{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//				{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//				{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" } };
//		setTable(datas);

		btnEdit = new JButton("Edit");
		btnDelete = new JButton("Delete");
		btnEdit.setBounds(350, 540, 80, 30);
		btnEdit.setBackground(new Color(0, 255, 153));
		btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panel1.add(btnEdit);
		btnDelete.setBounds(470, 540, 80, 30);
		btnDelete.setBackground(new Color(0, 255, 153));
		btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panel1.add(btnDelete);

		firstNameLabel = new JLabel("First Name : ");
		lastNameLabel = new JLabel("Last Name : ");
		emailLabel = new JLabel("Email : ");
		mobileLabel = new JLabel("Mobile : ");
		addressLabel = new JLabel("Address : ");
		detailsLabel=new JLabel("Details : ");
		remarksLabel = new JLabel("Remarks : ");

		firstNameLabel.setBounds(80, 50, 90, 30);
		firstNameLabel.setFont(new Font("Times New Roman", 1, 16));
		lastNameLabel.setBounds(80, 120, 90, 30);
		lastNameLabel.setFont(new Font("Times New Roman", 1, 16));
		emailLabel.setBounds(80, 190, 90, 30);
		emailLabel.setFont(new Font("Times New Roman", 1, 16));
		mobileLabel.setBounds(80, 260, 90, 30);
		mobileLabel.setFont(new Font("Times New Roman", 1, 16));
		addressLabel.setBounds(80, 320, 90, 30);
		addressLabel.setFont(new Font("Times New Roman", 1, 16));
		detailsLabel.setBounds(80, 390, 90, 30);
		detailsLabel.setFont(new Font("Times New Roman", 1, 16));
		remarksLabel.setBounds(80, 460, 90, 30);
		remarksLabel.setFont(new Font("Times New Roman", 1, 16));
		panel2.add(firstNameLabel);
		panel2.add(lastNameLabel);
		panel2.add(emailLabel);
		panel2.add(mobileLabel);
		panel2.add(addressLabel);
		panel2.add(detailsLabel);
		panel2.add(remarksLabel);
		
		tvFirstName = new JTextField();
		tvLastName = new JTextField();
		tvEmail = new JTextField();
		tvMobile = new JTextField();
		tvAddress = new JTextArea();
		tvDetails=new JTextField();
		tvRemarks=new JTextField();
		tvFirstName.setBounds(170, 50, 150, 30);
		tvLastName.setBounds(170, 120, 150, 30);
		tvEmail.setBounds(170, 190, 150, 30);
		tvMobile.setBounds(170, 260, 150, 30);
		Border blackBorder = BorderFactory.createLineBorder(Color.gray);
		tvAddress.setBounds(170, 320, 150, 30);
		tvAddress.setBorder(blackBorder);
		tvDetails.setBounds(170, 390, 150, 30);
		tvRemarks.setBounds(170, 460, 150, 30);
		panel2.add(tvFirstName);
		panel2.add(tvLastName);
		panel2.add(tvEmail);
		panel2.add(tvMobile);
		panel2.add(tvAddress);
		panel2.add(tvDetails);
		panel2.add(tvRemarks);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(170, 520, 80, 30);
		btnUpdate.setBackground(new Color(0, 255, 153));
		btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		Border bevelBorder=BorderFactory.createBevelBorder(DEFAULT_CURSOR);
		btnUpdate.setBorder(bevelBorder);
		panel2.add(btnUpdate);

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
		btnEdit.addActionListener(e->{
			getSuplierDetails(supplierBeingEdited.getId());
		});
		
		btnDelete.addActionListener(e->{
			if(supplierBeingEdited!=null){
	            deleteSupplier(supplierBeingEdited);
	        }
		});
		
		 btnUpdate.addActionListener(e->{
			 if(ValidateFields()) {
			 supplierBeingEdited.setFirstName(tvFirstName.getText());
	         supplierBeingEdited.setEmail(tvEmail.getText());
	          supplierBeingEdited.setMobile(tvMobile.getText());
	          supplierBeingEdited.setAddress(tvAddress.getText()); 
	          supplierBeingEdited.setLastName(tvLastName.getText());
	          supplierBeingEdited.setDetails(tvDetails.getText());
	          supplierBeingEdited.setRemarks(tvRemarks.getText());
	        updateUser(supplierBeingEdited);
			 }
		 });
		 
		 btnSearch.addActionListener(e->{
			 if(!tvSearchBox.getText().isEmpty()){
		            searchSupplier(tvSearchBox.getText());
		        }else{
		            fetchAllSuppliers();
		        }
		             
		 });
	}

	public void setMenuListener() {
		menuAddProduct.addActionListener(e -> {
			menuAddProductActionPerformed(e);

		});
		menuAddCustomer.addActionListener(e -> {
			menuAddCustomerActionPerformed(e);
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
	}


	

	public void menuAddProductActionPerformed(ActionEvent evt) {
		new AddProduct().setVisible(true);
		dispose();
	}

	public void menuAddCustomerActionPerformed(ActionEvent evt) {

		new AddCustomer().setVisible(true);
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
		tvSearchBox.addActionListener(e->{
			searchSupplier(tvSearchBox.getText());
		});
		
		
	}
	public void setTable(String[][] suppliersList) {
		DefaultTableModel model = new MyTableModel();
		String[] columns = { "Id", "First Name", "Last Name", "Email", "Mobile", "Address", "Created At",
				"Last Updated At" };
		model.setRowCount(0);
		model.setDataVector(suppliersList, columns);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
		supplierTable.setDefaultRenderer(String.class, centerRenderer);
		supplierTable.setModel(model);

		supplierTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent me) {

				int row = supplierTable.getSelectedRow();
				String[] supplier = suppliersList[row];
				
				selectedSupplier = new Supplier();
				selectedSupplier.setSupplierDetails(Integer.valueOf(supplier[0]), supplier[1], supplier[2], supplier[3],
						supplier[4], supplier[5], supplier[6],"","");
				supplierBeingEdited =new Supplier();
				supplierBeingEdited.setSupplierDetails(Integer.valueOf(supplier[0]), supplier[1], supplier[2], supplier[3],
						supplier[4], supplier[5], supplier[6],"","");
			}

			@Override
			public void mousePressed(MouseEvent me) {
				// To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				// To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				// To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public void mouseExited(MouseEvent me) {
				// To change body of generated methods, choose Tools | Templates.
			}
		});
	}

	
	public void fetchAllSuppliers() {

		String query = "Select * from supplier";
		ResultSet rs = service.executeQuery(query);
		ArrayList<ArrayList<String>> suppliers = new ArrayList<>();

		try {
			while (rs.next()) {

				ArrayList<String> supplier = new ArrayList<>();
//         String[] supplier=new String[8];
				String id = String.valueOf(rs.getInt("id"));
				supplier.add(id);
				supplier.add(rs.getString("first_name"));
				supplier.add(rs.getString("last_name"));
				supplier.add(rs.getString("email"));
				supplier.add(rs.getString("mobile"));
				supplier.add(rs.getString("address"));
				supplier.add(rs.getString("created_date"));
				supplier.add(rs.getString("updated_date"));
//				supplier.add(rs.getString("details"));
//				supplier.add(rs.getString("remarks"));
				suppliers.add(supplier);

			}
			String[][] mSuppliers = new String[suppliers.size()][8];
			for (int i = 0; i < suppliers.size(); i++) {
				for (int j = 0; j < suppliers.get(i).size(); j++) {
					mSuppliers[i][j] = suppliers.get(i).get(j);
				}
			}

			setTable(mSuppliers);
		} catch (Exception e) {

		}
	}

	public void searchSupplier(String name) {

		String query = "SELECT * from supplier where first_name LIKE " + "\'" + name + "\'";
		ResultSet rs = service.executeQuery(query);
		ArrayList<ArrayList<String>> suppliers = new ArrayList<>();

		try {
			while (rs.next()) {

				ArrayList<String> supplier = new ArrayList<>();
//         String[] supplier=new String[8];
				String id = String.valueOf(rs.getInt("id"));
				supplier.add(id);
				supplier.add(rs.getString("first_name"));
				supplier.add(rs.getString("last_name"));
				supplier.add(rs.getString("email"));
				supplier.add(rs.getString("mobile"));
				supplier.add(rs.getString("address"));
				supplier.add(rs.getString("created_date"));
				supplier.add(rs.getString("updated_date"));
				suppliers.add(supplier);

			}
			String[][] mSuppliers = new String[suppliers.size()][8];
			for (int i = 0; i < suppliers.size(); i++) {
				for (int j = 0; j < suppliers.get(i).size(); j++) {
					mSuppliers[i][j] = suppliers.get(i).get(j);
				}
			}

			setTable(mSuppliers);
		} catch (Exception e) {

		}
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

	public void updateUser(Supplier supplier) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		String query = "UPDATE supplier set " + "first_name=" + "\'" + supplier.getFirstName()+ "\'" + ",last_name=" + "\'"
				+ supplier.getLastName() + "\'" + ",email=" + "\'" + supplier.getEmail()+ "\'" + ",mobile=" + "\'"
				+ supplier.getMobile() + "\'" + ",address=" + "\'" + supplier.getAddress() + "\'" + ",updated_date=" + "\'" + date
				+ "\'" +",details="+"\'"+supplier.getDetails()+"\'"+",remarks="+"\'"+supplier.getRemarks()+"\'"+ " WHERE id=" + "\'" + supplier.getId() + "\'";
		System.out.println(query);
		QueryResponse qs = service.executeUpdate(query);
		if (qs.statusCode == 1) {
			fetchAllSuppliers();
			clearUpdateDetails();
			JOptionPane.showMessageDialog(null, "Supplier Details Updated SuccessFully", "Info",
					JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			JOptionPane.showMessageDialog(null, qs.message, "Error", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	public void getSuplierDetails(int id) {
		try {
		String query="SELECT * FROM supplier where id ="+id;
		ResultSet rs=service.executeQuery(query);
		Supplier supplier =new Supplier();
		
		while(rs.next()) {
			supplier.setId(rs.getInt("id"));
			supplier.setFirstName(rs.getString("first_name"));
			supplier.setLastName(rs.getString("last_name"));
			supplier.setEmail(rs.getString("email"));
			supplier.setMobile(rs.getString("mobile"));
			supplier.setAddress(rs.getString("address"));
			supplier.setCreatedAt(rs.getString("created_date"));
			supplier.setDetails(rs.getString("details"));
			supplier.setRemarks(rs.getString("remarks"));
			
			 tvFirstName.setText(supplier.getFirstName());
             tvLastName.setText(supplier.getLastName());
             tvEmail.setText(supplier.getEmail());
             tvMobile.setText(supplier.getMobile());
             tvAddress.setText(supplier.getAddress());
             tvDetails.setText(supplier.getDetails() );
             tvRemarks.setText(supplier.getRemarks());
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteSupplier(Supplier supplier) {

		String query = "DELETE from supplier where id = " + "\'" + supplierBeingEdited.getId() + "\'";
		System.out.println(query);
		QueryResponse qs = service.executeUpdate(query);
		if (qs.statusCode == 1) {
			fetchAllSuppliers();
			supplierBeingEdited = null;
			selectedSupplier = null;
			JOptionPane.showMessageDialog(null, "Supplier Deleted SuccessFully", "Info",
					JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			JOptionPane.showMessageDialog(null, qs.message, "Error", JOptionPane.ERROR_MESSAGE, null);
		}
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
	private JPanel panel2;

//label
	private JLabel tvViewSupplier;
	private javax.swing.JLabel addressLabel;
	private javax.swing.JLabel emailLabel;
	private javax.swing.JLabel firstNameLabel;
	private javax.swing.JLabel lastNameLabel;
	private javax.swing.JLabel mobileLabel;;
	private JLabel detailsLabel ;
	private JLabel remarksLabel;
	private JLabel nameLabel;

	// Textfields
	private javax.swing.JTextArea tvAddress;
	private javax.swing.JTextField tvEmail;
	private javax.swing.JTextField tvFirstName;
	private javax.swing.JTextField tvLastName;
	private javax.swing.JTextField tvMobile;;
	private JTextField tvSearchBox;
	private javax.swing.JTextField tvDetails;
	private javax.swing.JTextField tvRemarks;

	// Buttons
	private JButton btnSearch;
	private JButton btnEdit;
	private JButton btnDelete;
	private javax.swing.JButton btnUpdate;

	// table
	private JTable supplierTable;

	// sql
	private SQLService service;

	private Supplier selectedSupplier = null;
	private Supplier supplierBeingEdited = null;

}
