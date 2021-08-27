package products;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import customer.AddCustomer;
import customer.ViewCustomer;
import model.Supplier;
import network.SQLService;
import network.response.QueryResponse;
import orders.CreateOrder;
import purchase.CreatePurchase;
import supplier.AddSupplier;
import supplier.ViewSupplier;
import view.ViewStocks;

public class AddProduct extends javax.swing.JFrame {

	public AddProduct() {
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
		new AddProduct().setVisible(true);

	}

	public void initViews() {
		setMenuBar();
		tvAddProduct = new JLabel("Add Product");
		tvAddProduct.setBounds(564, 5, 300, 54);
		tvAddProduct.setFont(new Font("Times New Roman", 1, 36));
		tvAddProduct.setForeground(Color.RED);
		add(tvAddProduct);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(185, 75, 997, 585);
		Border redBorder = BorderFactory.createLineBorder(Color.red);
		panel1.setBorder(redBorder);
		add(panel1);

		jScrollPane1 = new javax.swing.JScrollPane();
	

		productNameLabel = new JLabel("Product Name : ");
		imageLabel = new JLabel("Image : ");
		priceLabel = new JLabel("Price : ");
		unitLabel = new JLabel("Unit : ");
		descriptionLabel=new JLabel("Description : ");
		quantityLabel = new JLabel("Quantity : ");
		productImageLabel=new JLabel();

		Border bevelBorder=BorderFactory.createBevelBorder(DEFAULT_CURSOR);
		productNameLabel.setBounds(140, 140, 150, 30);
		productNameLabel.setFont(new Font("Times New Roman", 1, 20));
		imageLabel.setBounds(140, 210, 150, 30);
		imageLabel.setFont(new Font("Times New Roman", 1, 20));
		productImageLabel.setBounds(290, 210, 150, 150);
		productImageLabel.setBackground(Color.WHITE);
		productImageLabel.setBorder(redBorder);
//		productImageLabel.setBorder(bevelBorder);
		unitLabel.setBounds(540, 100, 150, 30);
		unitLabel.setFont(new Font("Times New Roman", 1, 20));
		priceLabel.setBounds(540, 170, 150, 30);
		priceLabel.setFont(new Font("Times New Roman", 1, 20));
		;
		
		descriptionLabel.setBounds(540, 240, 150, 30);
		descriptionLabel.setFont(new Font("Times New Roman", 1, 20));
		quantityLabel.setBounds(540, 360, 150, 30);
		quantityLabel.setFont(new Font("Times New Roman", 1, 20));
		panel1.add(productNameLabel);
		panel1.add(imageLabel);
		panel1.add(priceLabel);
		panel1.add(unitLabel);
		panel1.add(descriptionLabel);
		panel1.add(quantityLabel);
		panel1.add(productImageLabel);
		
		tvProductName = new JTextField();
	
		tvPrice = new JTextField();
		tvDescription = new JTextArea();
		tvQuantity=new JTextField();
		unitComboBox=new JComboBox<String>();
		unitComboBox.addItem("Kg");
		tvProductName.setBounds(290, 140, 150, 30);
		unitComboBox.setBounds(650, 100, 220, 30);;
		tvPrice.setBounds(650, 170, 150, 30);
		Border blackBorder = BorderFactory.createLineBorder(Color.gray);
		tvDescription.setBounds(650, 240, 220, 80);
		tvDescription.setBorder(blackBorder);
		tvQuantity.setBounds(650, 360, 220, 30);
		
		panel1.add(tvProductName);
	
		panel1.add(tvPrice);
		panel1.add(tvDescription);
		panel1.add(tvQuantity);
		panel1.add(unitComboBox);
		
		
		btnUpload = new JButton("Upload");
		btnUpload.setBounds(325, 390, 80, 30);
		btnUpload.setBackground(new Color(0, 255, 153));
		btnUpload.setFont(new Font("Times New Roman", 1, 14));
		btnUpload.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		
		btnUpload.setBorder(bevelBorder);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(457, 480, 100, 40);
		btnAdd.setBackground(new Color(0, 255, 153));
		btnAdd.setFont(new Font("Times New Roman", 1, 14));
		btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		
		btnAdd.setBorder(bevelBorder);
		panel1.add(btnAdd);
		panel1.add(btnUpload);

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
	
	public void setButtonLister() {
		btnUpload.addActionListener(e->{
			                                        
			        // TODO add your handling code here:
			       chooseImage();                              
			    
		});
		btnAdd.addActionListener(e->{
			if(ValidateFields()) {
			addProduct();
			}
		});
	
		 
	}

	public void chooseImage() {
		 JFileChooser file = new JFileChooser();
         file.setCurrentDirectory(new File(System.getProperty("user.home")));
         //filter the files
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
         file.addChoosableFileFilter(filter);
         int result = file.showSaveDialog(null);
          //if the user click on save in Jfilechooser
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = file.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
            
             imagePath=path;
             imagePath=imagePath.replace('\\', '/');
             System.out.println(imagePath);
             
             productImageLabel.setIcon(ResizeImage(path));
         }
          //if the user click on save in Jfilechooser


         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No File Select");
   }  
	}
	 public ImageIcon ResizeImage(String ImagePath)
	    {
	        ImageIcon MyImage = new ImageIcon(ImagePath);
	        Image img = MyImage.getImage();
	        Image newImg = img.getScaledInstance(productImageLabel.getWidth(), productImageLabel.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(newImg);
	        return image;
	    }
	 
	 public void addProduct() {
		 String name=tvProductName.getText();
		 String unit=(String)unitComboBox.getSelectedItem();
		 String price =tvPrice.getText();
		 String quantity=tvQuantity.getText();
		 String description=tvDescription.getText();
		 String image=imagePath;
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	        LocalDateTime now = LocalDateTime.now();
	        String date=dtf.format(now);
		 String query="INSERT INTO products(name,image,unit,stock,price,description,created_date,updated_date) values("+"\'"+name+"\'"+",\'"+image+"\'"+",\'"+unit+"\'"+",\'"+quantity+"\'"+",\'"+price+"\'"+",\'"+description+"\'"+",\'"+date+"\'"+",\'"+date+"\'"+")";
		 System.out.println(query);
		 QueryResponse q=service.executeUpdate(query);
		 if(q.statusCode==1) {
	    		JOptionPane.showMessageDialog(null, "Product Created SuccessFully","Info", JOptionPane.INFORMATION_MESSAGE, null);
	    		clearUpdateDetails();
	    	}else {
	    		JOptionPane.showMessageDialog(null, q.message,"Error", JOptionPane.ERROR_MESSAGE, null);
	    	}
	 }
		public void setTextFieldListener() {
//			
			
			
		}
	 
	 
	public void setMenuListener() {
		menuViewSupplier.addActionListener(e -> {
			menuViewSupplierActionPerformed(e);

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
	
	public void clearUpdateDetails() {
		tvProductName.setText("");
		tvPrice.setText("");
		tvDescription.setText("");
		tvQuantity.setText("");
		
	}

	public boolean ValidateFields() {

        if(tvProductName.getText()==null ||  tvProductName.getText()=="" || tvProductName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Product name should not be empty","Error", JOptionPane.ERROR_MESSAGE, null);
            return false;     
        }
    
        String regex = "[-+]?[0-9]*\\.?[0-9]+";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(tvPrice.getText());  
        if( matcher.matches()==false || tvPrice.getText()==null || tvPrice.getText()=="" || tvPrice.getText().isEmpty())  {
        	JOptionPane.showMessageDialog(null, "Price should be a numerical value","Error", JOptionPane.ERROR_MESSAGE, null);
        	return false;
        }
        
        String regexQuantity = "[+-]?[0-9]+";  
        Pattern patternQuantity = Pattern.compile(regexQuantity);  
        Matcher matcherQuantity = patternQuantity.matcher(tvPrice.getText());  
        if( matcher.matches()==false || tvQuantity.getText()==null || tvQuantity.getText()=="" || tvQuantity.getText().isEmpty())  {
        	JOptionPane.showMessageDialog(null, "Quantity should be a numerical value","Error", JOptionPane.ERROR_MESSAGE, null);
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

	// Panels

	private JPanel panel1;

//label
	private JLabel tvAddProduct;
	private javax.swing.JLabel addressLabel;
	private javax.swing.JLabel priceLabel;
	private javax.swing.JLabel productNameLabel;
	private javax.swing.JLabel imageLabel;
	private javax.swing.JLabel unitLabel;;
	private JLabel descriptionLabel ;
	private JLabel quantityLabel;
	private javax.swing.JLabel productImageLabel;

	// Textfields
	private javax.swing.JTextArea tvDescription;
	private javax.swing.JTextField tvProductName;
	private javax.swing.JTextField tvPrice;
	private javax.swing.JTextField tvQuantity;

	// Buttons
	private javax.swing.JButton btnAdd;
	private JButton btnUpload;
	
	//Combo box
	private JComboBox<String> unitComboBox;


	// sql
	private SQLService service;
	private String imagePath;


}
