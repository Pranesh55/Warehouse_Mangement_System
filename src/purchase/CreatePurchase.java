package purchase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JComboBox;

import customer.AddCustomer;
import customer.ViewCustomer;
import supplier.AddSupplier;

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
	
	private javax.swing.JTextField unitsTextField;
	private javax.swing.JTextField stocksTextField;
	private javax.swing.JTextField amountTextField;
	private javax.swing.JTextField amountTextField2;
	private javax.swing.JTextField paidTextField;
	private javax.swing.JTextField balanceTextField;
	
	private javax.swing.JTextArea noteTextField;
	
	
	private JButton addBtn;
	private JButton doneBtn;
	
	private JComboBox fromSupplierDropDown;
	private JComboBox productNameDropDown;
	
	public CreatePurchase(){
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		setLayout(null);
		setVisible(true);
		setMenuBar();
		initViews();
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
		fromSupplierDropDown.addItem("Jon");
		fromSupplierDropDown.addItem("Rob");
		
		
		inPanel1=new JPanel();
		inPanel1.setLayout(null);
		inPanel1.setBounds(10,90,440,450);
		inPanel1.setBorder(redBorder);
		
		productNameLabel = new JLabel("Product name:-");
		productNameLabel.setBounds(20,50,140,30);
		productNameLabel.setFont(new Font("Times New Roman", 1, 16));
		
		productNameDropDown = new JComboBox();
		productNameDropDown.setBounds(130,50,150,30);
		productNameDropDown.addItem("Jon");
		productNameDropDown.addItem("Rob");
		
		productStockLabel = new JLabel("Product Stock:-");
		productStockLabel.setBounds(20,120,140,30);
		productStockLabel.setFont(new Font("Times New Roman", 1, 16));
		
		stocksTextField = new JTextField();
		stocksTextField.setEditable(false);
		stocksTextField.setBounds(130,120,150,30);
		
		kgLabel = new JLabel("kg");
		kgLabel.setBounds(300,120,40,30);
		
		unitsLabel = new JLabel("Units:-");
		unitsLabel.setBounds(20,190,140,30);
		unitsLabel.setFont(new Font("Times New Roman", 1, 16));
		
		unitsTextField = new JTextField();
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
		inPanel2.setBounds(460,90,480,450);
		inPanel2.setBorder(redBorder);

		
		inPanel3=new JPanel();
		inPanel3.setLayout(null);
		inPanel3.setBounds(950,90,390,450);
		inPanel3.setBorder(redBorder);
		
		amountLabel2 = new JLabel("Amount:-");
		amountLabel2.setBounds(30,50,140,30);
		amountLabel2.setFont(new Font("Times New Roman", 1, 16));
		
		amountTextField2=new JTextField();
		amountTextField2.setBounds(170,50,150,30);
		
		paidLabel =  new JLabel("Paid:-");
		paidLabel.setBounds(30,120,140,30);
		paidLabel.setFont(new Font("Times New Roman", 1, 16));
		
		paidTextField = new JTextField();
		paidTextField.setBounds(170,120,150,30);
		
		balanceLabel = new JLabel("Balance:-");
		balanceLabel.setBounds(30,190,140,30);
		balanceLabel.setFont(new Font("Times New Roman", 1, 16));
		
		balanceTextField = new JTextField();
		balanceTextField.setBounds(170,190,150,30);
		
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
		
		add(panel1);
		
		
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
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatePurchase().setVisible(true);
            }
        });
	}
}
