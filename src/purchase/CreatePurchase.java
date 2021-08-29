package purchase;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.kerberos.KerberosTicket;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import network.response.QueryResponse;
import orders.CreateOrder;
import supplier.AddSupplier;
import supplier.ViewSupplier;
import view.ViewStocks;
import model.Constants;
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
	
	private HashMap<Integer,Object> productsListMap=new HashMap<>();
	private HashMap<Integer,Integer> supplierIdMap;
	private HashMap<Integer,String> supplierNamesMap;
	private ArrayList<Product> productNamesMap;
	private int supplierId;
	private ArrayList<Product> productsSelected;
	private String[][] mProductsSelected;
	private  String purchaseId;
	private String date;
	
	private Float amount = 0f;
	Double bHeight=0.0;
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> quantity = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<String> subtotal = new ArrayList<>();
    ArrayList<String> productIds=new ArrayList<>();
    private static String userType;
    
	public CreatePurchase(String loginType){
		userType=loginType;
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
		
		submitBtn.setEnabled(false);
		printBtn.setEnabled(false);
		
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
//		menuBar.add(menuProduct);

		menuSupplier.setForeground(new java.awt.Color(255, 255, 255));
		menuSupplier.setText("Suppliers");

		menuAddSupplier.setText("Add Supplier");

//		menuSupplier.add(menuAddSupplier);

		menuViewSupplier.setText("View Supplier");
//		menuSupplier.add(menuViewSupplier);

//		menuBar.add(menuSupplier);

		menuCustomers.setForeground(new java.awt.Color(255, 255, 255));
		menuCustomers.setText("Customers");

		menuAddCustomer.setText("Add Customer");
//		menuCustomers.add(menuAddCustomer);

		menuViewCustomer.setText("View Customer");

//		menuCustomers.add(menuViewCustomer);

//		menuBar.add(menuCustomers);

		menuTransactions.setForeground(new java.awt.Color(255, 255, 255));
		menuTransactions.setText("New Purchase/Order");

		menuCreatePurchase.setText("Create Purchase");
		menuTransactions.add(menuCreatePurchase);

		menuCreaterOrder.setText("Create Order");
		menuTransactions.add(menuCreaterOrder);

//		menuBar.add(menuTransactions);

		menuView.setForeground(new java.awt.Color(255, 255, 255));
		menuView.setText("View");

		menuStocks.setText("Stocks");
		menuView.add(menuStocks);

//		menuBar.add(menuView);

		setJMenuBar(menuBar);

		setMenuListener();
		setMenuItems();

	}
	
	public void setMenuItems() {
		if(userType==Constants.ADMIN) {
			menuBar.add(menuProduct);
			
			menuSupplier.add(menuAddSupplier);
			menuSupplier.add(menuViewSupplier);
			
			menuCustomers.add(menuAddCustomer);
			menuCustomers.add(menuViewCustomer);
			menuBar.add(menuSupplier);
			menuBar.add(menuCustomers);
			menuBar.add(menuTransactions);
			menuBar.add(menuView);
		}else if(userType==Constants.WAREHOUSE) {
			menuSupplier.add(menuViewSupplier);
			menuCustomers.add(menuViewCustomer);
			menuBar.add(menuSupplier);
			menuBar.add(menuCustomers);
			menuBar.add(menuTransactions);
			menuBar.add(menuView);
		}else {
			menuBar.add(menuTransactions);
			menuBar.add(menuView);
		}
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
		menuViewSupplier.addActionListener(e -> {
			new ViewSupplier(userType).setVisible(true);
			dispose();
		});
		
		menuStocks.addActionListener(e->{
			new ViewStocks(userType).setVisible(true);
			dispose();
		});
		
		menuCreaterOrder.addActionListener(e->{
			new CreateOrder(userType).setVisible(true);
			dispose();
		});
	}
	
	
	public void setBtnListeners() {
		addBtn.addActionListener(e->{
			if( validateUnit() &&validateAmount()) {
			Product currentProduct=(Product)productNamesMap.get(productNameDropDown.getSelectedIndex()-1);
			Product product=new Product();
			product.setId(currentProduct.getId());
			product.setName(currentProduct.getName());
			product.setStock(Integer.valueOf(unitsTextField.getText()));
			product.setPrice(Float.valueOf(amountTextField.getText()));
			productIds.add(String.valueOf(product.getId()));
			itemName.add(product.getName());
			quantity.add(String.valueOf(product.getStock()));
			itemPrice.add(String.valueOf(product.getPrice()));
			subtotal.add(String.valueOf(product.getStock()*product.getPrice()));
			productsSelected.add(product);
			amount=0f;
			
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
			}
			
		});
		deleteBtn.addActionListener(e->{
			int row=table.getSelectedRow();
			Product product=productsSelected.get(row);
			productsSelected.remove(row);
			productIds.remove(row);
			itemName.remove(row);
			quantity.remove(row);
			itemPrice.remove(row);
			subtotal.remove(0);
			amount-=product.getPrice();
			amountTextField2.setText(String.valueOf(amount));
			
			Product p1=(Product)productsListMap.get(product.getId());
			productNamesMap.add(p1);
			productNameDropDown.addItem(p1.getName());
			String[][] products = new String[productsSelected.size()][4];
			for (int i = 0; i < productsSelected.size(); i++) {
				Product p=productsSelected.get(i);
				
				products[i][0]=String.valueOf(p.getId());
				products[i][1]=p.getName();
				products[i][2]=String.valueOf(p.getStock());
				products[i][3]=String.valueOf(p.getPrice());
				
			}
			setTable(products);
		});
		
		submitBtn.addActionListener(e->{
			if(ValidateFields())
			createPurchase();
		});
		printBtn.addActionListener(e->{
			try {
//						printActionPerformed(e);
				
				 bHeight = Double.valueOf(itemName.size());
			        //JOptionPane.showMessageDialog(rootPane, bHeight);
			        
			        PrinterJob pj = PrinterJob.getPrinterJob();        
			        pj.setPrintable(new BillPrintable(purchaseId,date),getPageFormat(pj));
			        try {
			             pj.print();
			             
			          
			        }
			         catch (PrinterException ex) {
			                 ex.printStackTrace();
			        }
			        
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});;
		
		doneBtn.addActionListener(e->{
			setEnableRec(inPanel3, true);
			setEnableRec(inPanel1,false);
			balanceTextField.setText(String.valueOf(amount));
			submitBtn.setEnabled(true);
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
	public void createPurchase() {
		try {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();
        String date=dtf.format(now);
        this.date=date;
        
        //create transaction
        
		String query="INSERT INTO purchases (supplier_id,created_date,updated_date,total_amount,paid,balance,description) values("+"\'"+supplierId+"\'"+",\'"+date+"\'"+",\'"+date+"\'"+",\'"+amountTextField2.getText()+"\'"+",\'"+paidTextField.getText()+"\'"+",\'"+balanceTextField.getText()+"\'"+",\'"+noteTextField.getText()+"\'"+")";
		System.out.println(query);
		QueryResponse qs=service.executeUpdate(query);
		if(qs.statusCode==1) {
			submitBtn.setEnabled(false);
			printBtn.setEnabled(true);
    		JOptionPane.showMessageDialog(null, "Purchase Created SuccessFully","Info", JOptionPane.INFORMATION_MESSAGE, null);
//    		clearUpdateDetails();
    	}else {
    		
    		JOptionPane.showMessageDialog(null, qs.message,"Error", JOptionPane.ERROR_MESSAGE, null);
    		return;
    	}
		
		//fetch last inserted id
		String fetchLastInsertedId="SELECT * FROM purchases WHERE ID = (SELECT MAX(ID) FROM purchases WHERE supplier_id = "+supplierId+")";
		java.sql.ResultSet rs1=service.executeQuery(fetchLastInsertedId);
		String purchaseId="";
		if(rs1.next()) {
			purchaseId=String.valueOf(rs1.getInt("id"));
		}
		this.purchaseId=purchaseId;
		
		//insert products into the purchase
		String insertProductsQuery="INSERT INTO purchase_products values";
		for(int i=0;i<productIds.size();i++) {
			String q="";
			if(i!=productIds.size()-1) {
			 q="("+"\'"+purchaseId+"\'"+",\'"+productIds.get(i)+"\'"+",\'"+itemPrice.get(i)+"\'"+",\'"+quantity.get(i)+"\'"+",\'"+date+"\'"+",\'"+date+"\'),";
			insertProductsQuery+=q;
			}else {
				 q="("+"\'"+purchaseId+"\'"+",\'"+productIds.get(i)+"\'"+",\'"+itemPrice.get(i)+"\'"+",\'"+quantity.get(i)+"\'"+",\'"+date+"\'"+",\'"+date+"\')";
					insertProductsQuery+=q;
			}
			
		}
		System.out.println(insertProductsQuery);
		service.executeUpdate(insertProductsQuery);
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 public PageFormat getPageFormat(PrinterJob pj)
	 {
	     
	     PageFormat pf = pj.defaultPage();
	     Paper paper = pf.getPaper();    

	     double bodyHeight = bHeight;  
	     double headerHeight = 4.5;                  
	     double footerHeight = 4.5;        
	     double width = cm_to_pp(21); 
	     double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
	     paper.setSize(width, height);
	     paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
	             
	     pf.setOrientation(PageFormat.PORTRAIT);  
	     pf.setPaper(paper);    

	     return pf;
	 }
	 protected static double cm_to_pp(double cm)
	    {            
		        return toPPI(cm * 0.393600787);            
	    }
	 
	protected static double toPPI(double inch)
	    {            
		        return inch * 72d;            
	    }
	private void printActionPerformed(java.awt.event.ActionEvent evt) {
	    // TODO add your handling code here:
	    Toolkit tkp = panel1.getToolkit();
	    PrintJob pjp = tkp.getPrintJob(this, null, null);
	    Graphics g = pjp.getGraphics();
	    panel1.print(g);
	    g.dispose();
	    pjp.end();
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
	public boolean ValidateFields() {

	    
        String regex = "[-+]?[0-9]*\\.?[0-9]+";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(amountTextField2.getText());  
        if( matcher.matches()==false || amountTextField2.getText()==null || amountTextField2.getText()=="" || amountTextField2.getText().isEmpty())  {
        	JOptionPane.showMessageDialog(null, "Amount should be a numerical value","Error", JOptionPane.ERROR_MESSAGE, null);
        	return false;
        }
        
        String regexQuantity = "[-+]?[0-9]*\\.?[0-9]+";  
        Pattern patternQuantity = Pattern.compile(regexQuantity);  
        Matcher matcherQuantity = patternQuantity.matcher(paidTextField.getText());  
        if( matcher.matches()==false || paidTextField.getText()==null || paidTextField.getText()=="" || paidTextField.getText().isEmpty())  {
        	JOptionPane.showMessageDialog(null, "Paid should be a numerical value","Error", JOptionPane.ERROR_MESSAGE, null);
        	return false;
        }

        return true;
	}  
	
	public boolean validateAmount() {
		  
        String regex = "[-+]?[0-9]*\\.?[0-9]+";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(amountTextField.getText());  
        if( matcher.matches()==false || amountTextField.getText()==null || amountTextField.getText()=="" || amountTextField.getText().isEmpty())  {
        	JOptionPane.showMessageDialog(null, "Amount should be a numerical value","Error", JOptionPane.ERROR_MESSAGE, null);
        	return false;
        }
        return true;
	}
	
	public boolean validateUnit() {
		  
		 String regexQuantity = "[+-]?[0-9]+";  
	        Pattern patternQuantity = Pattern.compile(regexQuantity);  
	        Matcher matcherQuantity = patternQuantity.matcher(unitsTextField.getText());  
	        if( matcherQuantity.matches()==false || unitsTextField.getText()==null || unitsTextField.getText()=="" || unitsTextField.getText().isEmpty())  {
	        	JOptionPane.showMessageDialog(null, "Unit should be a numerical value","Error", JOptionPane.ERROR_MESSAGE, null);
	        	return false;
	        }
	        return true;
		
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
			productsListMap.put(id, product);
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
		new AddSupplier(userType).setVisible(true);
		dispose();
	}

	public void menuAddCustomerActionPerformed(ActionEvent evt) {

		new AddCustomer(userType).setVisible(true);
		dispose();
	}

	private void menuViewCustomerActionPerformed(ActionEvent evt) {

		this.dispose();
		new ViewCustomer(userType).setVisible(true);
	}
	
	private void menuCreatePurchaseActionPerformed(ActionEvent evt) {
		this.dispose();
		new CreatePurchase(userType).setVisible(true);
	}
	
	public static void main(String[] args) {
		new CreatePurchase(userType).setVisible(true);
		
	}


class BillPrintable implements Printable {
    
	String purchaseId;
	String date;
	public BillPrintable(String id,String date) {
		purchaseId=id;
		this.date=date;
	}
    
    
	  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
	  throws PrinterException 
	  {    
	      
	      int r= itemName.size();
//	      ImageIcon icon=new ImageIcon("C:UsersccsDocumentsNetBeansProjectsvideo TestPOSInvoicesrcposinvoicemylogo.jpg"); 
	      int result = NO_SUCH_PAGE;    
	        if (pageIndex == 0) {                    
	        
	            Graphics2D g2d = (Graphics2D) graphics;                    
	            double width = pageFormat.getImageableWidth();                               
	            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



	          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
	        
	        try{
	            int y=20;
	            int yShift = 10;
	            int headerRectHeight=40;
	           // int headerRectHeighta=40;
	            
	                
	            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
//	            g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
	            g2d.drawString("-----------------------------------------------------------------",12,y);y+=yShift;
	            g2d.drawString("                            PURCHASE                             ",18,y);y+=yShift;
	            g2d.drawString("-----------------------------------------------------------------",12,y);y+=headerRectHeight;
	            g2d.drawString("                                         Transaction Id: "+purchaseId,12,y);y+=yShift;
	            g2d.drawString(" Supplier: "+supplierNamesMap.get(supplierId)+"                     Date:  "+date, 12, y);y+=30;
	            
	            
	            

	            g2d.drawString(" Item Id   Item Name        Price        Quantity      Amount   ",10,y);y+=yShift;
	            g2d.drawString("-----------------------------------------------------------------",10,y);y+=20;
	     
	            for(int s=0; s<r; s++)
	            {
	            g2d.drawString(" "+productIds.get(s)+"   ",10,y);
	            g2d.drawString(" "+itemName.get(s)+"       ",60,y);
	            g2d.drawString(" "+itemPrice.get(s)+"       ",160,y);
	            g2d.drawString(" "+quantity.get(s)+"       ",235,y);
	            g2d.drawString(subtotal.get(s),310,y);y+=yShift;

	            }
	          
	            g2d.drawString("----------------------------------------------------------------",10,y);y+=yShift;
	            g2d.drawString("                                          Total amount: "+amountTextField2.getText()+"   ",10,y);y+=yShift;
	           
	            g2d.drawString("                                          Paid  : "+paidTextField.getText()+"   ",10,y);y+=yShift;
	      
	            g2d.drawString("                                          Balance : "+balanceTextField.getText()+"   ",10,y);y+=40;
	  
	            g2d.drawString("****************************************************************",10,y);y+=yShift;
	            g2d.drawString("                           HAVE A GOOD DAY                        ",10,y);y+=yShift;
	            g2d.drawString("****************************************************************",10,y);y+=yShift;
	               
	           

	    }
	    catch(Exception e){
	    e.printStackTrace();
	    }

	              result = PAGE_EXISTS;    
	          }    
	          return result;    
	      }
	   }
}
