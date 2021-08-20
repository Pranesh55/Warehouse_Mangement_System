package test;


import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;
import model.Supplier;
import javax.swing.table.DefaultTableModel;
import network.SQLService;
import network.response.QueryResponse;
import customer.AddCustomer;
import customer.ViewCustomer;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author pranesh
 */
public class Test extends javax.swing.JFrame {

    /**
     * Creates new form ViewSupplier
     */
    public Test() {
        initComponents();
        service=new SQLService();
	service.getConnection();
        fetchAllSuppliers();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    
    public void setMenuBar() {
    	jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuAddSupplier = new javax.swing.JMenuItem();
        menuViewSupplier = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuAddCustomer = new javax.swing.JMenuItem();
        menuViewCustomer = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuCreatePurchase = new javax.swing.JMenuItem();
        menuCreaterOrder = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        
        jMenuBar1.setBackground(new java.awt.Color(255, 0, 0));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 0, 0), null, null));
        jMenuBar1.setForeground(new java.awt.Color(255, 0, 0));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jMenuBar1.setName(""); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(325, 30));
        jMenuBar1.setRequestFocusEnabled(false);

        jMenu6.setForeground(new java.awt.Color(255, 255, 255));
        jMenu6.setText("Product");
        jMenuBar1.add(jMenu6);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Suppliers");

        menuAddSupplier.setText("Add Supplier");
        menuAddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddSupplierActionPerformed(evt);
            }
        });
        jMenu2.add(menuAddSupplier);

        menuViewSupplier.setText("View Supplier");
        jMenu2.add(menuViewSupplier);

        jMenuBar1.add(jMenu2);

        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Customers");

        menuAddCustomer.setText("Add Customer");
        jMenu3.add(menuAddCustomer);
        menuAddCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuAddCustomerActionPerformed(e);
			}
		});

        menuViewCustomer.setText("View Customer");
        menuViewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuViewCustomerActionPerformed(evt);
            }
        });
        jMenu3.add(menuViewCustomer);

        jMenuBar1.add(jMenu3);

        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("New Purchase/Order");

        menuCreatePurchase.setText("Create Purchase");
        jMenu4.add(menuCreatePurchase);

        menuCreaterOrder.setText("Create Order");
        jMenu4.add(menuCreaterOrder);

        jMenuBar1.add(jMenu4);

        jMenu5.setForeground(new java.awt.Color(255, 255, 255));
        jMenu5.setText("View");

        jMenuItem7.setText("Stocks");
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);
    }
    private void initComponents() {
    	setMenuBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tvSearchBox = new java.awt.TextField();
        searchBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        tvEdit = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tvLastName = new javax.swing.JTextField();
        tvEmail = new javax.swing.JTextField();
        tvMobile = new javax.swing.JTextField();
        tvFirstName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tvAddress = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        tvViewSupplier = new javax.swing.JLabel();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(2147483647, 1000));
        setPreferredSize(new java.awt.Dimension(697, 524));
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 557));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Name : ");

        tvSearchBox.setName(""); // NOI18N
        tvSearchBox.setPreferredSize(new java.awt.Dimension(10, 30));
        tvSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvSearchBoxActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(0, 255, 153));
        searchBtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchBtn.setText("Search");
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        searchBtn.setPreferredSize(new java.awt.Dimension(100, 30));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        supplierTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        supplierTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        supplierTable.setForeground(new java.awt.Color(255, 0, 0));
        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        supplierTable.setRowHeight(30);
        jScrollPane1.setViewportView(supplierTable);

        tvEdit.setBackground(new java.awt.Color(51, 255, 153));
        tvEdit.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tvEdit.setText("Edit");
        tvEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        tvEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvEditActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(51, 255, 153));
        deleteBtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.setPreferredSize(new java.awt.Dimension(100, 30));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(345, 345, 345)
                                .addComponent(tvEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(293, 293, 293)
                                .addComponent(jLabel1)
                                .addGap(33, 33, 33)
                                .addComponent(tvSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 290, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(360, 360, 360)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tvSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tvEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Mobile : ");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("First name: ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Last name: ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Email : ");

        tvLastName.setPreferredSize(new java.awt.Dimension(6, 30));
        tvLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvLastNameActionPerformed(evt);
            }
        });

        tvEmail.setPreferredSize(new java.awt.Dimension(6, 30));
        tvEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvEmailActionPerformed(evt);
            }
        });

        tvMobile.setPreferredSize(new java.awt.Dimension(6, 30));
        tvMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvMobileActionPerformed(evt);
            }
        });

        tvFirstName.setPreferredSize(new java.awt.Dimension(6, 30));
        tvFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvFirstNameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Address : ");

        tvAddress.setPreferredSize(new java.awt.Dimension(6, 30));
        tvAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvAddressActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(0, 255, 204));
        updateBtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        updateBtn.setText("Update");
        updateBtn.setPreferredSize(new java.awt.Dimension(100, 30));
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tvMobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tvEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tvLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tvFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(26, 26, 26)
                        .addComponent(tvAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tvFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tvLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tvEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tvMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tvAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tvViewSupplier.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        tvViewSupplier.setForeground(new java.awt.Color(255, 0, 0));
        tvViewSupplier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tvViewSupplier.setText("View Suppliers");
        tvViewSupplier.setAlignmentX(0.5F);

        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tvViewSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tvViewSupplier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void tvSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        if(!tvSearchBox.getText().isEmpty()){
            searchSupplier(tvSearchBox.getText());
        }else{
            fetchAllSuppliers();
        }
    }                                           

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        System.out.println("text is "+tvSearchBox.getText());
        if(!tvSearchBox.getText().isEmpty()){
            searchSupplier(tvSearchBox.getText());
        }else{
            fetchAllSuppliers();
        }
                            
        ;
    }                                         

    private void tvEditActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
       
               tvFirstName.setText(selectedSupplier.firstName);
               tvLastName.setText(selectedSupplier.lastName);
               tvEmail.setText(selectedSupplier.email);
               tvMobile.setText(selectedSupplier.mobile);
               tvAddress.setText(selectedSupplier.address);
    }                                      

    private void tvLastNameActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        supplierBeingEdited.lastName=tvLastName.getText();
        tvEmail.requestFocus();
    }                                          

    private void tvEmailActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        supplierBeingEdited.email=tvEmail.getText();
        tvMobile.requestFocus();
    }                                       

    private void tvMobileActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        supplierBeingEdited.mobile=tvMobile.getText();
        tvAddress.requestFocus();
          
    }                                        

    private void tvFirstNameActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        supplierBeingEdited.firstName=tvFirstName.getText();
        tvLastName.requestFocus();
    }                                           

    private void tvAddressActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        supplierBeingEdited.address=tvAddress.getText();
        updateUser(supplierBeingEdited);
    }                                         

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        supplierBeingEdited.firstName=tvFirstName.getText();
         supplierBeingEdited.email=tvEmail.getText();
          supplierBeingEdited.mobile=tvMobile.getText();
          supplierBeingEdited.address=tvAddress.getText(); 
          supplierBeingEdited.lastName=tvLastName.getText();
          
        updateUser(supplierBeingEdited);
        
        
        
    }                                         

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        
        if(supplierBeingEdited!=null){
            deleteSupplier(supplierBeingEdited);
        }
    }                                         

    private void menuAddSupplierActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	new Test().setVisible(true);
    	dispose();
    }    
    
    private void menuAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    	new AddCustomer().setVisible(true);
    	dispose();
    }             

    private void menuViewCustomerActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        this.dispose();
        new ViewCustomer().setVisible(true);
    }                                                

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
        //</editor-fold>

        /* Create and display the form */
      
        new Test().setVisible(true);
    }
    
    public  void setTable(String[][] suppliersList){
        DefaultTableModel model =new MyTableModel();
        String[] columns ={"id","First Name","Last Name","Email","Mobile","Address","Created At","Last Updated At"};
        model.setRowCount(0);
        model.setDataVector(suppliersList, columns);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment( javax.swing.JLabel.CENTER);
supplierTable.setDefaultRenderer(String.class, centerRenderer);
        supplierTable.setModel(model);
                
       
        
        
        supplierTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
             
               int row=supplierTable.getSelectedRow();
               String[] supplier=suppliersList[row];
               selectedSupplier=new Supplier(Integer.valueOf(supplier[0]), supplier[1], supplier[2], supplier[3], supplier[4], supplier[5],supplier[6]);
               supplierBeingEdited=new Supplier(Integer.valueOf(supplier[0]), supplier[1], supplier[2], supplier[3], supplier[4], supplier[5],supplier[6]);
               
              
               
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void fetchAllSuppliers(){
        
            String query="Select * from supplier";
        ResultSet rs=service.executeQuery(query);
        ArrayList<ArrayList<String>> suppliers=new ArrayList<>();
       
        try{
        while(rs.next()){
            
            ArrayList<String> supplier=new ArrayList<>();
//            String[] supplier=new String[8];
             String id=String.valueOf(rs.getInt("id"));
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
        String[][] mSuppliers=new String[suppliers.size()][8];
        for(int i=0;i<suppliers.size();i++){
            for(int j=0;j<suppliers.get(i).size();j++){
            mSuppliers[i][j]=suppliers.get(i).get(j);
                    }
        }
        
            setTable(mSuppliers);
        }catch(Exception e){
            
        }
    }
    
    public void searchSupplier(String name){
        
            String query="SELECT * from supplier where first_name LIKE "+"\'"+name+"\'";
        ResultSet rs=service.executeQuery(query);
        ArrayList<ArrayList<String>> suppliers=new ArrayList<>();
       
        try{
        while(rs.next()){
            
            ArrayList<String> supplier=new ArrayList<>();
//            String[] supplier=new String[8];
             String id=String.valueOf(rs.getInt("id"));
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
        String[][] mSuppliers=new String[suppliers.size()][8];
        for(int i=0;i<suppliers.size();i++){
            for(int j=0;j<suppliers.get(i).size();j++){
            mSuppliers[i][j]=suppliers.get(i).get(j);
                    }
        }
        
            setTable(mSuppliers);
        }catch(Exception e){
            
        }
    }
    
    public void updateUser(Supplier supplier){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();
        String date=dtf.format(now);
        	String query="UPDATE supplier set "+"first_name="+"\'"+supplier.firstName+"\'"+",last_name="+"\'"+supplier.lastName+"\'"+",email="+"\'"+supplier.email+"\'"+",mobile="+"\'"+supplier.mobile+"\'"+",address="+"\'"+supplier.address+"\'"+",updated_date="+"\'"+date+"\'"+" WHERE id="+"\'"+supplier.id+"\'";
                System.out.println(query);
                QueryResponse qs=service.executeUpdate(query);
                if(qs.statusCode==1){
                    fetchAllSuppliers();
                    JOptionPane.showMessageDialog(null, "Supplier Details Updated SuccessFully","Info", JOptionPane.INFORMATION_MESSAGE, null);
                }else{
                    JOptionPane.showMessageDialog(null, qs.message,"Error", JOptionPane.ERROR_MESSAGE, null);
                }
    }
    
    public void deleteSupplier(Supplier supplier){
      
       
        	String query="DELETE from supplier where id = "+"\'"+supplierBeingEdited.id+"\'";
                System.out.println(query);
                QueryResponse qs=service.executeUpdate(query);
                if(qs.statusCode==1){
                    fetchAllSuppliers();
                    supplierBeingEdited=null;
                    selectedSupplier=null;
                    JOptionPane.showMessageDialog(null, "Supplier Deleted SuccessFully","Info", JOptionPane.INFORMATION_MESSAGE, null);
                }else{
                    JOptionPane.showMessageDialog(null, qs.message,"Error", JOptionPane.ERROR_MESSAGE, null);
                }
    }
    
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuAddCustomer;
    private javax.swing.JMenuItem menuAddSupplier;
    private javax.swing.JMenuItem menuCreatePurchase;
    private javax.swing.JMenuItem menuCreaterOrder;
    private javax.swing.JMenuItem menuViewCustomer;
    private javax.swing.JMenuItem menuViewSupplier;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable supplierTable;
    private javax.swing.JTextField tvAddress;
    private javax.swing.JButton tvEdit;
    private javax.swing.JTextField tvEmail;
    private javax.swing.JTextField tvFirstName;
    private javax.swing.JTextField tvLastName;
    private javax.swing.JTextField tvMobile;
    private java.awt.TextField tvSearchBox;
    private javax.swing.JLabel tvViewSupplier;
    private javax.swing.JButton updateBtn;
    // End of variables declaration                   
    private Supplier selectedSupplier=null;
     private Supplier supplierBeingEdited=null;
    private static SQLService service=null;

}
 class MyTableModel extends DefaultTableModel {

      public boolean isCellEditable(int row, int column){  
          return false;  
      }

}

