package purchase;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.kerberos.KerberosTicket;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javax.swing.JComboBox;

import customer.AddCustomer;
import customer.ViewCustomer;
import model.Supplier;
import network.SQLService;
import supplier.AddSupplier;
import model.MyTableModel;
import model.Product;

public class CreatePurchase extends javax.swing.JFrame {
	
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

	private JLabel tvViewCreatePurchaseLabel;
	private JLabel tvViewFromFieldLabel;
	private JLabel productNameLabel;
	private JLabel productStockLabel;
	private JLabel kgLabel;
	private JLabel unitsLabel;
	private JLabel amountLabel;
	private JLabel amountLabel2;
	private JLabel paidLabel;
	private JLabel balanceLabel;
	private JLabel noteLabel;
	// Panels

	private JPanel panel1;
	private JPanel inPanel1;
	private JPanel inPanel2;
	private JPanel inPanel3;
	
	private TextField unitsTextField;
	private javax.swing.JTextField stocksTextField;
	private javax.swing.JTextField amountTextField;
	private javax.swing.JTextField amountTextField2;
	private TextField paidTextField;
	private javax.swing.JTextField balanceTextField;
	
	private javax.swing.JTextArea noteTextField;
	
	
	private JButton addBtn;
	private JButton doneBtn;
	private JButton deleteBtn;
	private JButton submitBtn;
	private JButton printBtn;
	
	private JComboBox fromSupplierDropDown;
	private JComboBox productNameDropDown;
	
	private JTable table;
	
	private SQLService service;
	
	private HashMap<Integer,Integer> supplierIdMap;
	private HashMap<Integer,String> supplierNamesMap;
	private ArrayList<Product> productNamesMap;
	private int supplierId;
	private ArrayList<Product> productsSelected;
	private String[][] mProductsSelected;
	
	private Float amount = 0f;
	
	public CreatePurchase(){
		service=new SQLService();
		service.getConnection();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		setLayout(null);
		setVisible(true);
		setMenuBar();
		initViews();
		setDropDownListener();
		setBtnListeners();
	}
	
	public void setTable(String[][] suppliersList) {
		DefaultTableModel model = new MyTableModel();
		String[] columns = { "ID","Product Name","Quantity","Amount" };
		model.setRowCount(0);
		model.setDataVector(suppliersList, columns);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setModel(model);

		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent me) {

				int row = table.getSelectedRow();
				String[] supplier = suppliersList[row];
				
//				selectedSupplier = new Supplier();
//				selectedSupplier.setSupplierDetails(Integer.valueOf(supplier[0]), supplier[1], supplier[2], supplier[3],
//						supplier[4], supplier[5], supplier[6],"","");
//				supplierBeingEdited =new Supplier();
//				supplierBeingEdited.setSupplierDetails(Integer.valueOf(supplier[0]), supplier[1], supplier[2], supplier[3],
//						supplier[4], supplier[5], supplier[6],"","");
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
	
	public void initViews() {
		
		
		
		tvViewCreatePurchaseLabel = new JLabel("Create Purchase");
		tvViewCreatePurchaseLabel.setBounds(30, 15, 300, 54);
		tvViewCreatePurchaseLabel.setFont(new Font("Times New Roman", 1, 36));
		tvViewCreatePurchaseLabel.setForeground(Color.RED);
		add(tvViewCreatePurchaseLabel);
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(10, 75, 1350, 585);
		Border redBorder = BorderFactory.createLineBorder(Color.red);
		panel1.setBorder(redBorder);
		
		tvViewFromFieldLabel = new JLabel("From:-");
		tvViewFromFieldLabel.setBounds(80, 40, 90, 30);
		tvViewFromFieldLabel.setFont(new Font("Times New Roman", 1, 16));

		
		fromSupplierDropDown = new JComboBox();
		fromSupplierDropDown.setBounds(150,40,120,30);
		setSuppliersDropDown();
		
		
		inPanel1=new JPanel();
		inPanel1.setLayout(null);
		inPanel1.setBounds(10,90,440,430);
		inPanel1.setBorder(redBorder);
		inPanel1.setEnabled(false);
		
		
		productNameLabel = new JLabel("Product name:-");
		productNameLabel.setBounds(20,50,140,30);
		productNameLabel.setFont(new Font("Times New Roman", 1, 16));
		
		productNameDropDown = new JComboBox();
		productNameDropDown.setBounds(130,50,150,30);
		setProductsDropDown();
		
		productStockLabel = new JLabel("Product Stock:-");
		productStockLabel.setBounds(20,120,140,30);
		productStockLabel.setFont(new Font("Times New Roman", 1, 16));
		
		stocksTextField = new JTextField();
		stocksTextField.setEditable(false);
		stocksTextField.setBounds(130,120,150,30);
		
		kgLabel = new JLabel("kg");
		kgLabel.setBounds(300,120,40,30);
		
		unitsLabel = new JLabel("Units:-");
		unitsLabel.setBounds(20,190,100,30);
		unitsLabel.setFont(new Font("Times New Roman", 1, 16));
		
		unitsTextField = new TextField();
		unitsTextField.setBounds(130,190,150,30);
		
		amountLabel = new JLabel("Amount:-");
		amountLabel.setBounds(20,260,140,30);
		amountLabel.setFont(new Font("Times New Roman", 1, 16));
		
		amountTextField= new JTextField();
		amountTextField.setBounds(130,260,150,30);
		
		addBtn = new JButton("Add");
		addBtn.setBounds(100,330,80,30);
		addBtn.setBackground(new Color(0, 255, 153));
		addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		
		doneBtn=new JButton("Done");
		doneBtn.setBounds(220,330,80,30);
		doneBtn.setBackground(new Color(0, 255, 153));
		doneBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		
		
		inPanel1.add(productNameLabel);
		inPanel1.add(productNameDropDown);
		inPanel1.add(productStockLabel);
		inPanel1.add(stocksTextField);
		inPanel1.add(kgLabel);
		inPanel1.add(unitsLabel);
		inPanel1.add(unitsTextField);
		inPanel1.add(amountLabel);
		inPanel1.add(amountTextField);
		inPanel1.add(addBtn);
		inPanel1.add(doneBtn);
		
		
		
		
		inPanel2=new JPanel();
		inPanel2.setLayout(null);
		inPanel2.setBounds(460,90,480,430);
		inPanel2.setBorder(redBorder);
		inPanel2.setEnabled(false);
		
		table = new JTable();
		jScrollPane1 = new javax.swing.JScrollPane();
		table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		table.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		table.setForeground(new java.awt.Color(255, 0, 0));
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "ID", "Product Name","Quantity","Amount" }));
		table.setRowHeight(30);
		table.setBounds(10, 20, 460, 340);
		jScrollPane1.setViewportView(table);
		jScrollPane1.setBounds(10,20 ,460 , 340);
		
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(200,370,80,30);
		deleteBtn.setBackground(new Color(0, 255, 153));
		

		inPanel2.add(jScrollPane1);
		inPanel2.add(deleteBtn);

		
		inPanel3=new JPanel();
		inPanel3.setLayout(null);
		inPanel3.setBounds(950,90,390,430);
		inPanel3.setBorder(redBorder);
		inPanel3.setEnabled(false);
		
		amountLabel2 = new JLabel("Amount:-");
		amountLabel2.setBounds(30,50,140,30);
		amountLabel2.setFont(new Font("Times New Roman", 1, 16));
		
		amountTextField2=new JTextField();
		amountTextField2.setBounds(170,50,150,30);
		
		
		paidLabel =  new JLabel("Paid:-");
		paidLabel.setBounds(30,120,140,30);
		paidLabel.setFont(new Font("Times New Roman", 1, 16));
		
		paidTextField = new TextField();
		paidTextField.setBounds(170,120,150,30);
		
		balanceLabel = new JLabel("Balance:-");
		balanceLabel.setBounds(30,190,140,30);
		balanceLabel.setFont(new Font("Times New Roman", 1, 16));
		
		
		balanceTextField = new JTextField();
		balanceTextField.setBounds(170,190,150,30);
		balanceTextField.setEditable(false);
		
		noteLabel = new JLabel("Note:-");
		noteLabel.setBounds(30,260,140,30);
		noteLabel.setFont(new Font("Times New Roman", 1, 16));
		
		noteTextField = new JTextArea();
		noteTextField.setBounds(170,260,150,80);
		noteTextField.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		inPanel3.add(amountLabel2);
		inPanel3.add(amountTextField2);
		inPanel3.add(paidLabel);
		inPanel3.add(paidTextField);
		inPanel3.add(balanceLabel);
		inPanel3.add(balanceTextField);
		inPanel3.add(noteLabel);
		inPanel3.add(noteTextField);
		
		
		panel1.add(tvViewFromFieldLabel);
		panel1.add(fromSupplierDropDown);
		panel1.add(inPanel1);
		panel1.add(inPanel2);
		panel1.add(inPanel3);
		setEnableRec(inPanel1, false);
		setEnableRec(inPanel2, false);
		setEnableRec(inPanel3, false);
//		inPanel1.setEnabled(false);
		submitBtn=new JButton("Submit");
		submitBtn.setBounds(575,540,80,30);
		submitBtn.setBackground(new Color(0, 255, 153));
		submitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panel1.add(submitBtn);
		
		printBtn=new JButton("Print");
		printBtn.setBounds(695,540,80,30);
		printBtn.setBackground(new Color(0, 255, 153));
		printBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panel1.add(printBtn);
		add(panel1);
		
		productsSelected=new ArrayList<>();
		
//		String[][] test=new String[][] {{"34252525","Phone","20","12"},};
//		setTable(test);
		
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

		menuBar.setBackground(new java.awt.Color(255, 0, 0));
		menuBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null,
				new java.awt.Color(255, 0, 0), null, null));
		menuBar.setForeground(new java.awt.Color(255, 0, 0));
		menuBar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		menuBar.setPreferredSize(new java.awt.Dimension(325, 30));
		menuBar.setRequestFocusEnabled(false);

		menuProduct.setForeground(new java.awt.Color(255, 255, 255));
		menuProduct.setText("Product");
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
	
	public void setMenuListener() {
		menuAddSupplier.addActionListener(e -> {
			menuAddSupplierActionPerformed(e);

		});
		menuAddCustomer.addActionListener(e -> {
			menuAddCustomerActionPerformed(e);
		});
		menuViewCustomer.addActionListener(e -> {
			menuViewCustomerActionPerformed(e);
		});
		menuCreatePurchase.addActionListener(e->{
			menuCreatePurchaseActionPerformed(e);
		});
	}
	
	
	public void setBtnListeners() {
		addBtn.addActionListener(e->{
			Product currentProduct=(Product)productNamesMap.get(productNameDropDown.getSelectedIndex()-1);
			Product product=new Product();
			product.setId(currentProduct.getId());
			product.setName(currentProduct.getName());
			product.setStock(Integer.valueOf(unitsTextField.getText()));
			product.setPrice(Float.valueOf(amountTextField.getText()));
			productsSelected.add(product);
			
			
			String[][] products = new String[productsSelected.size()][4];
			for (int i = 0; i < productsSelected.size(); i++) {
				Product p=productsSelected.get(i);
				products[i][0]=String.valueOf(p.getId());
				products[i][1]=p.getName();
				products[i][2]=String.valueOf(p.getStock());
				products[i][3]=String.valueOf(p.getPrice());
				
				amount+=p.getPrice();
				amountTextField2.setText(String.valueOf(amount));
			}
			setTable(products);
			clearPanel1(productNameDropDown.getSelectedIndex()-1);
			setEnableRec(inPanel2 ,true);

			
		});
		
		printBtn.addActionListener(e->{
			try {
						table.print();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});;
		
		doneBtn.addActionListener(e->{
			setEnableRec(inPanel3, true);
			setEnableRec(inPanel1,false);
			balanceTextField.setText(String.valueOf(amount));
		});
	
		
		unitsTextField.addTextListener(e->{
			if(unitsTextField.getText()!="") {
				Product currentProduct=(Product)productNamesMap.get(productNameDropDown.getSelectedIndex()-1);
				Float amount=Integer.valueOf(unitsTextField.getText()) * Float.valueOf(currentProduct.getPrice());
				amountTextField.setText(String.valueOf(amount));
			}
		});
		
		paidTextField.addTextListener(e->{
			if(unitsTextField.getText()!="") {
				Float balance=Float.valueOf(amount - Float.valueOf(paidTextField.getText()));
				balanceTextField.setText(String.valueOf(balance));
			}
		});
		
	
	}
	
	public void setDropDownListener() {
		fromSupplierDropDown.addActionListener(e->{
			if(fromSupplierDropDown.getSelectedIndex()!=0) {
			supplierId=supplierIdMap.get(fromSupplierDropDown.getSelectedIndex()-1);
			setEnableRec(inPanel1, true);
			}else {
				setEnableRec(inPanel1, false);
			}
		});
		
		productNameDropDown.addActionListener(e->{
			if(productNameDropDown.getSelectedIndex()!=0) {
			Product product=(Product)productNamesMap.get(productNameDropDown.getSelectedIndex()-1);
			stocksTextField.setText(String.valueOf(product.getStock()));
			kgLabel.setText(product.getUnit());
			unitsTextField.setText("1");
			amountTextField.setText(String.valueOf(product.getPrice()));
			}else {
				
			}
		});
	}
	
	public void setSuppliersDropDown() {
		try {
			supplierIdMap=new HashMap<>();
			supplierNamesMap=new HashMap<>();
		String query="SELECT * FROM supplier";
		java.sql.ResultSet rs=service.executeQuery(query);
		int count=0;
		fromSupplierDropDown.addItem("Select Supplier");
		while(rs.next()) {
			Integer id=rs.getInt("id");
			Supplier supplier=new Supplier();
			supplier.setId(id);
			supplier.setFirstName(rs.getString("first_name"));
			supplier.setLastName(rs.getString("last_name"));
			supplier.setEmail(rs.getString("email"));
			supplier.setMobile(rs.getString("mobile"));
			supplier.setAddress(rs.getString("address"));
			supplierIdMap.put(count, id);
			supplierNamesMap.put(id, supplier.getFirstName()+" "+supplier.getLastName());
			fromSupplierDropDown.addItem(supplier.getFirstName()+" "+supplier.getLastName());
			
			count++;
		}
		fromSupplierDropDown.setSelectedIndex(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void setEnableRec(Component container, boolean enable){
	    container.setEnabled(enable);

	    try {
	        Component[] components= ((Container) container).getComponents();
	        for (int i = 0; i < components.length; i++) {
	            setEnableRec(components[i], enable);
	        }
	    } catch (ClassCastException e) {

	    }
	}
	
	public void setProductsDropDown() {
		try {
			
			productNamesMap=new ArrayList<>();
		String query="SELECT * FROM products";
		java.sql.ResultSet rs=service.executeQuery(query);
		Product p=new Product();
		p.setId(1);
		productNameDropDown.addItem("Add Product");
		int count=0;
		while(rs.next()) {
			Integer id=rs.getInt("id");
			Product product=new Product();
			product.setId(id);
			product.setName(rs.getString("name"));
			product.setDescription(rs.getString("description"));
			product.setUnit(rs.getString("unit"));
			product.setStock(Integer.valueOf(rs.getString("stock")));
			product.setPrice(Float.valueOf(rs.getString("price")));
		
			productNamesMap.add(product);
			productNameDropDown.addItem(product.getName());
			count++;
		}
	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void clearPanel1(int position) {
		
		Product p=(Product)productNamesMap.get(position);
		productNamesMap.remove(position);
//		productNameDropDown.removeAllItems();
//		productNameDropDown = new JComboBox();
//		productNameDropDown.setBounds(130,50,150,30);
//		setProductsDropDown();
////		setProductsDropDown();
//		productNameDropDown.removeAllItems();;
		productNameDropDown.removeItem(p.getName());
		productNameDropDown.setSelectedIndex(0);;
		stocksTextField.setText(null);
		unitsTextField.setText(null);
		amountTextField.setText(null);
		kgLabel.setText(null);
	}
	
	public void menuAddSupplierActionPerformed(ActionEvent evt) {
		new AddSupplier().setVisible(true);
		dispose();
	}

	public void menuAddCustomerActionPerformed(ActionEvent evt) {

		new AddCustomer().setVisible(true);
		dispose();
	}

	private void menuViewCustomerActionPerformed(ActionEvent evt) {

		this.dispose();
		new ViewCustomer().setVisible(true);
	}
	
	private void menuCreatePurchaseActionPerformed(ActionEvent evt) {
		this.dispose();
		new CreatePurchase().setVisible(true);
	}
	
	public static void main(String[] args) {
		new CreatePurchase().setVisible(true);
		
	}
}
