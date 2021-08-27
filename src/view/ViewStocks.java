package view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
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
import products.AddProduct;
import purchase.CreatePurchase;
import supplier.AddSupplier;
import supplier.ViewSupplier;
import model.MyTableModel;
import model.Product;

public class ViewStocks extends JFrame{

	



	

		public ViewStocks() {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setUndecorated(false);
			setLayout(null);
			initViews();
			setButtonLister();
			setTextFieldListener();
			service = new SQLService();
			service.getConnection();
			fetchAllProducts();

		}

		public static void main(String[] args) {
			new ViewStocks().setVisible(true);

		}

		public void initViews() {
			setMenuBar();
			tvViewStockr = new JLabel("Stocks");
			tvViewStockr.setBounds(564, 5, 300, 54);
			tvViewStockr.setFont(new Font("Times New Roman", 1, 36));
			tvViewStockr.setForeground(Color.RED);
			add(tvViewStockr);

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

			productNameLabel1 = new JLabel("Name  : ");
			tvSearchBox = new JTextField();
			btnSearch = new JButton("Search");
			productNameLabel1.setBounds(300, 30, 80, 30);
			productNameLabel1.setFont(new Font("Times New Roman", 1, 18));
			panel1.add(productNameLabel1);

			tvSearchBox.setBounds(390, 30, 150, 30);
			panel1.add(tvSearchBox);

			btnSearch.setBounds(416, 70, 80, 30);
			btnSearch.setBackground(new Color(0, 255, 153));
			btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			panel1.add(btnSearch);

			productsTable = new JTable();
			jScrollPane1 = new javax.swing.JScrollPane();
			productsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			productsTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
			productsTable.setForeground(new java.awt.Color(255, 0, 0));
			productsTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

			}, new String[] { "Id", "Firstname" }));
			productsTable.setRowHeight(30);
			productsTable.setBounds(10, 120, 900, 400);
			jScrollPane1.setViewportView(productsTable);
			jScrollPane1.setBounds(10, 120, 900, 400);

			panel1.add(jScrollPane1);
//	    panel1.add();
	//
//			String[][] datas = new String[][] {
//					{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//					{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//					{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//					{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//					{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//					{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" },
//					{ "1", "Pranesh", "S", "pranesh@gmail.com", "6374861765", "karur", "19--8-2002", "19-08-2002" } };
//			setTable(datas);

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

			productNameLabel2 = new JLabel("Name : ");
			stockLabel = new JLabel("Stock : ");
			priceLabel = new JLabel("Price : ");
			unitLabel = new JLabel("Unit : ");
			imageLabel = new JLabel("Image : ");
			descriptionLabel=new JLabel("Description : ");
			

			productNameLabel2.setBounds(80, 50, 90, 30);
			productNameLabel2.setFont(new Font("Times New Roman", 1, 16));
			stockLabel.setBounds(80, 120, 90, 30);
			stockLabel.setFont(new Font("Times New Roman", 1, 16));
			priceLabel.setBounds(80, 190, 90, 30);
			priceLabel.setFont(new Font("Times New Roman", 1, 16));
			unitLabel.setBounds(80, 260, 90, 30);
			unitLabel.setFont(new Font("Times New Roman", 1, 16));
			imageLabel.setBounds(80, 320, 90, 30);
			imageLabel.setFont(new Font("Times New Roman", 1, 16));
			descriptionLabel.setBounds(80, 450, 90, 30);
			descriptionLabel.setFont(new Font("Times New Roman", 1, 16));
		
			panel2.add(productNameLabel2);
			panel2.add(stockLabel);
			panel2.add(priceLabel);
			panel2.add(unitLabel);
			panel2.add(imageLabel);
			panel2.add(descriptionLabel);
			
			
			tvProductName = new JTextField();
			tvStock = new JTextField();
			tvPrice = new JTextField();
			tvUnit = new JTextField();
			productImage = new JLabel();
			tvDescription=new JTextField();
	
			tvProductName.setBounds(170, 50, 150, 30);
			tvStock.setBounds(170, 120, 150, 30);
			tvPrice.setBounds(170, 190, 150, 30);
			tvUnit.setBounds(170, 260, 150, 30);
			Border blackBorder = BorderFactory.createLineBorder(Color.gray);
			productImage.setBounds(170, 320, 90, 90);
			productImage.setBorder(blackBorder);
			tvDescription.setBounds(170, 450, 150, 30);
			panel2.add(tvProductName);
			panel2.add(tvStock);
			panel2.add(tvPrice);
			panel2.add(tvUnit);
			panel2.add(productImage);
			panel2.add(tvDescription);
			
			btnUpload=new JButton("Choose");
			btnUpload.setBounds(290, 350, 80, 30);
			btnUpload.setBackground(new Color(0, 255, 153));
			btnUpload.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			btnUpdate = new JButton("Update");
			btnUpdate.setBounds(170, 520, 80, 30);
			btnUpdate.setBackground(new Color(0, 255, 153));
			btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			Border bevelBorder=BorderFactory.createBevelBorder(DEFAULT_CURSOR);
			btnUpdate.setBorder(bevelBorder);
			panel2.add(btnUpdate);
			panel2.add(btnUpload);

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
				getProductDetails(productBeingEdited.getId());
			});
			
			btnDelete.addActionListener(e->{
				if(productBeingEdited!=null){
		            deleteSupplier(productBeingEdited);
		        }
			});
			
			 btnUpdate.addActionListener(e->{
				 if(ValidateFields()) {
				 productBeingEdited.setName(tvProductName.getText());
		         productBeingEdited.setDescription(tvDescription.getText());
		          productBeingEdited.setUnit(tvUnit.getText());
		          productBeingEdited.setImage(imagePath); 
		          productBeingEdited.setStock(Integer.valueOf(tvStock.getText()));
		          productBeingEdited.setPrice(Float.valueOf(tvPrice.getText()));
		        updateProduct(productBeingEdited);
				 }
			 });
			 
			 btnSearch.addActionListener(e->{
				 if(!tvSearchBox.getText().isEmpty()){
			            searchProduct(tvSearchBox.getText());
			        }else{
			            fetchAllProducts();
			        }
			             
			 });
			 btnUpload.addActionListener(e->{
				 chooseImage();
			 });
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
			menuAddProduct.addActionListener(e->{
				menuAddProductsActionPerformed(e);
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
		private void menuAddProductsActionPerformed(ActionEvent evt) {

			
			new AddProduct().setVisible(true);
			this.dispose();
		}
		
		public void setTextFieldListener() {
			tvSearchBox.addActionListener(e->{
				searchProduct(tvSearchBox.getText());
			});
			
			
		}
		public void setTable(String[][] productsList) {
			try {
			DefaultTableModel model = new MyTableModel();
			String[] columns = { "Id", "Product Name", "Description", "Stock", "Unit","Price", "Image", "Created At",
					"Last Updated At" };
			model.setRowCount(0);
			model.setDataVector(productsList, columns);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
			productsTable.setModel(model);
			productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			supplierTable=new JTable(model) {
//				public Class getColumnClass(int column) {
//			        return (column == 6) ? Icon.class : Object.class;
//			      }
//			};
			productsTable.setDefaultRenderer(String.class, centerRenderer);
			productsTable.getColumn("Image").setCellRenderer(new ImageCellRenderer());;
			productsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			

			productsTable.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent me) {

					int row = productsTable.getSelectedRow();
					String[] supplier = productsList[row];
					
					selectedProduct = new Product();
					selectedProduct.setDetails(Integer.valueOf(supplier[0]), supplier[1], supplier[2], Integer.valueOf(supplier[3]),
							supplier[4], Float.valueOf(supplier[5]), supplier[6]);
					imagePath=selectedProduct.getImage();
					productBeingEdited =new Product();
					productBeingEdited.setDetails(Integer.valueOf(supplier[0]), supplier[1], supplier[2], Integer.valueOf(supplier[3]),
							supplier[4], Float.valueOf(supplier[5]), supplier[6]);
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
			}catch(Exception e) {
				e.printStackTrace();
			}
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
	             
	             productImage.setIcon(ResizeImage(path));
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
		        Image newImg = img.getScaledInstance(productImage.getWidth(), productImage.getHeight(), Image.SCALE_SMOOTH);
		        ImageIcon image = new ImageIcon(newImg);
		        return image;
		    }

		

		public void fetchAllProducts() {

			String query = "Select * from products";
			ResultSet rs = service.executeQuery(query);
			ArrayList<ArrayList<String>> products = new ArrayList<>();

			try {
				while (rs.next()) {

					ArrayList<String> product = new ArrayList<>();
//	         String[] supplier=new String[8];
					String id = String.valueOf(rs.getInt("id"));
					product.add(id);
					product.add(rs.getString("name"));
					product.add(rs.getString("description"));
					product.add(rs.getString("stock"));
					product.add(rs.getString("unit"));
					product.add(rs.getString("price"));
					product.add(rs.getString("image"));
					product.add(rs.getString("created_date"));
					product.add(rs.getString("updated_date"));
//					supplier.add(rs.getString("details"));
//					supplier.add(rs.getString("remarks"));
					products.add(product);

				}
				String[][] mSuppliers = new String[products.size()][9];
				for (int i = 0; i < products.size(); i++) {
					for (int j = 0; j < products.get(i).size(); j++) {
						mSuppliers[i][j] = products.get(i).get(j);
					}
				}

				setTable(mSuppliers);
			} catch (Exception e) {

			}
		}

		public void searchProduct(String name) {
		

			String query = "SELECT * from products where name LIKE " + "\'" + name + "\'";
			ResultSet rs = service.executeQuery(query);
			ArrayList<ArrayList<String>> products = new ArrayList<>();

			try {
				while (rs.next()) {

					ArrayList<String> product = new ArrayList<>();
//	         String[] supplier=new String[8];
					String id = String.valueOf(rs.getInt("id"));
					String stock = String.valueOf(rs.getInt("stock"));
					String price= String.valueOf(rs.getFloat("price"));
					product.add(id);
					product.add(rs.getString("name"));
					product.add(rs.getString("description"));
					product.add(stock);
					product.add(rs.getString("unit"));
					product.add(price);
					product.add(rs.getString("image"));
					product.add(rs.getString("created_date"));
					product.add(rs.getString("updated_date"));
					products.add(product);

				}
				String[][] mProducts = new String[products.size()][9];
				for (int i = 0; i < products.size(); i++) {
					for (int j = 0; j < products.get(i).size(); j++) {
						mProducts[i][j] = products.get(i).get(j);
					}
				}

				setTable(mProducts);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void clearUpdateDetails() {
			tvProductName.setText("");
			tvStock.setText("");
			tvPrice.setText("");
			tvUnit.setText("");
			tvDescription.setText("");
			productImage.setIcon(null);
		}

public boolean ValidateFields() {
			
			if(tvProductName.getText()==null ||  tvProductName.getText()=="" || tvProductName.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Name should not be empty","Error", JOptionPane.ERROR_MESSAGE, null);
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
	        Matcher matcherQuantity = patternQuantity.matcher(tvUnit.getText());  
	        if( matcher.matches()==false || tvUnit.getText()==null || tvUnit.getText()=="" || tvUnit.getText().isEmpty())  {
	        	JOptionPane.showMessageDialog(null, "Unit should be a numerical value","Error", JOptionPane.ERROR_MESSAGE, null);
	        	return false;
	        }

	        return true;
		}  
		public void updateProduct(Product product) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String date = dtf.format(now);
			String query = "UPDATE products set " + "name=" + "\'" + product.getName()+ "\'" + ",description=" + "\'"
					+ product.getDescription() + "\'" + ",stock=" + "\'" + product.getStock()+ "\'" + ",unit=" + "\'"
					+ product.getUnit() + "\'" + ",updated_date=" + "\'" + date
					+ "\'" +",price="+"\'"+product.getPrice()+"\'"+",image="+"\'"+imagePath+"\'"+ " WHERE id=" + "\'" + product.getId() + "\'";
			System.out.println(query);
			QueryResponse qs = service.executeUpdate(query);
			if (qs.statusCode == 1) {
				fetchAllProducts();
				clearUpdateDetails();
				JOptionPane.showMessageDialog(null, "Supplier Details Updated SuccessFully", "Info",
						JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				JOptionPane.showMessageDialog(null, qs.message, "Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}
		
		public void getProductDetails(int id) {
			try {
			String query="SELECT * FROM products where id ="+id;
			ResultSet rs=service.executeQuery(query);
			Product product =new Product();
			
			while(rs.next()) {
				product.setId(rs.getInt("id"));
				
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setStock(Integer.valueOf(rs.getString("stock")));
				product.setUnit(rs.getString("unit"));
				product.setPrice(Float.valueOf(rs.getString("price")));
				product.setImage(rs.getString("image"));
				imagePath=product.getImage();
				
				
				 tvProductName.setText(product.getName());
	             tvStock.setText(String.valueOf(product.getStock()));
	             tvPrice.setText(String.valueOf(product.getPrice()));
	             tvUnit.setText(product.getUnit());
	             productImage.setIcon(new ImageIcon(new ImageIcon(product.getImage()).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
	             tvDescription.setText(product.getDescription() );
	            
			}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		public void deleteSupplier(Product supplier) {

			String query = "DELETE from products where id = " + "\'" + productBeingEdited.getId() + "\'";
			System.out.println(query);
			QueryResponse qs = service.executeUpdate(query);
			if (qs.statusCode == 1) {
				fetchAllProducts();
				productBeingEdited = null;
				selectedProduct = null;
				JOptionPane.showMessageDialog(null, "Supplier Deleted SuccessFully", "Info",
						JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				JOptionPane.showMessageDialog(null, qs.message, "Error", JOptionPane.ERROR_MESSAGE, null);
			}
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
		private JLabel tvViewStockr;
		private javax.swing.JLabel imageLabel;
		private javax.swing.JLabel priceLabel;
		private javax.swing.JLabel productNameLabel2;
		private javax.swing.JLabel stockLabel;
		private javax.swing.JLabel unitLabel;;
		private JLabel descriptionLabel ;
		private JLabel productNameLabel1;

		// Textfields
		private javax.swing.JLabel productImage;
		private javax.swing.JTextField tvPrice;
		private javax.swing.JTextField tvProductName;
		private javax.swing.JTextField tvStock;
		private javax.swing.JTextField tvUnit;;
		private JTextField tvSearchBox;
		private javax.swing.JTextField tvDescription;

		// Buttons
		private JButton btnSearch;
		private JButton btnEdit;
		private JButton btnDelete;
		private javax.swing.JButton btnUpload;
		private javax.swing.JButton btnUpdate;

		// table
		private JTable productsTable;

		// sql
		private SQLService service;

		private Product productBeingEdited;
		private Product selectedProduct;
		private String imagePath;

	

}

class ImageCellRenderer implements TableCellRenderer {
	 
    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        
        String photo=(String) value;
        TableColumn tb=table.getColumn("Image");
        ImageIcon icon=new ImageIcon(new ImageIcon(photo).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        tb.setMaxWidth(60);
        tb.setMinWidth(60);
//
        table.setRowHeight(60);

        return new JLabel(icon);
    }

}