package login;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


import model.Constants;
import network.SQLService;
import products.AddProduct;
import purchase.CreatePurchase;


public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        service=new SQLService();
        service.getConnection();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBtnListeners();
    }

                            
    private void initComponents() {

        btnAdmin = new javax.swing.JButton();
        btnWarehouse = new javax.swing.JButton();
        btnSales = new javax.swing.JButton();
        panel1 = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        tvMobile = new javax.swing.JTextField();
        tvPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        loginTypeLabel=new JLabel();
        setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnAdmin.setText("ADMIN LOGIN");
       
        btnAdmin.setBackground(new Color(255, 0, 0));
        btnAdmin.setForeground(Color.white);
        btnAdmin.setBounds(195, 50, 200, 50);
        add(btnAdmin);

        btnWarehouse.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnWarehouse.setText("Warehouse Login");
        btnWarehouse.setBounds(580, 50, 200, 50);
        btnWarehouse.setBackground(new Color(255, 0, 0));
        btnWarehouse.setForeground(Color.white);
        add(btnWarehouse);

        btnSales.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnSales.setText("Sales Login");
      
        btnSales.setBackground(new Color(255, 0, 0));
        btnSales.setForeground(Color.white);
        btnSales.setBounds(975, 50, 200, 50);
        add(btnSales);
        
        Border redBorder = BorderFactory.createLineBorder(Color.red);
        panel1.setBorder(redBorder);
        panel1.setBounds(340,150,700,500);
        panel1.setLayout(null);
        add(panel1);
        
        loginTypeLabel.setText("Admin Login");
        loginTypeLabel.setBounds(275,20,250,50);
        loginTypeLabel.setFont(new java.awt.Font("Times New Roman", 0, 25));
        loginTypeLabel.setForeground(Color.red);
        panel1.add(loginTypeLabel);
        
        

        userNameLabel.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        userNameLabel.setText("Mobile Number : ");
        userNameLabel.setBounds(200,130,150,40);
        panel1.add(userNameLabel);

        passwordLabel.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        passwordLabel.setText("Password : ");
        passwordLabel.setBounds(200,250,150,40);
        panel1.add(passwordLabel);
        
        tvMobile.setBounds(350,130,150,40);
        panel1.add(tvMobile);
        tvPassword.setBounds(350,250,150,40);
        panel1.add(tvPassword);


        tvMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        tvPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               
            }
        });

        

        btnLogin.setBackground(new Color(0, 255, 153));
        btnLogin.setText("Login");
        btnLogin.setBounds(310,420, 80, 30);
        panel1.add(btnLogin);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               login();
            }
        });

        setLocationRelativeTo(null);
    }// </editor-fold>                        

    
    public void setBtnListeners() {
    	btnAdmin.addActionListener(e->{
    		loginTypeLabel.setText("Admin Login");
    		loginType=Constants.ADMIN;
    	});
    	
    	btnWarehouse.addActionListener(e->{
    		loginTypeLabel.setText("Warehouse Manager");
    		loginType=Constants.WAREHOUSE;
    	});
    	
    	btnSales.addActionListener(e->{
    		loginTypeLabel.setText("Sales Manager");
    		loginType=Constants.SALES;
    	});
    	
    	
    }
    
    public void login() {
    	if(validateFields()) {
    	try {
    	String query="SELECT * FROM users where mobile ="+"\'"+tvMobile.getText()+"\'"+" AND password ="+"\'"+tvPassword.getText()+"\'"+" AND user_type ="+"\'"+loginType+"\'";
    	System.out.println(query);
    	ResultSet rs=service.executeQuery(query);
    	if(rs.next()) {
    		System.out.println(rs.getString("username"));
    		if(loginType==Constants.ADMIN) {
    			new AddProduct(loginType).setVisible(true);
    			dispose();
    		}else {
    			new CreatePurchase(loginType).setVisible(true);
    			dispose();
    		}
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}
    }

    
    public boolean validateFields() {
    	if(!validateMobileNumber(tvMobile.getText())) {
    		JOptionPane.showMessageDialog(null, "Enter a valid mobile number","Error", JOptionPane.ERROR_MESSAGE, null);
    		return false;
    	}
    	if(tvPassword.getText().length()<6 || tvPassword.getText().length()>8) {
    		JOptionPane.showMessageDialog(null, "Password should be of 6 - 8 digits","Error", JOptionPane.ERROR_MESSAGE, null);
    		return false;
    	}
    	return true;
    	
    }
    public boolean validateMobileNumber(String mobile) {
		Pattern p=Pattern.compile("^([0-9]{10})$");
		Boolean isMatches=p.matcher(mobile).matches();		
		return isMatches;
	}
    public static void main(String args[]) {
    
        
         new Login().setVisible(true);
    }
    
               
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnWarehouse;
    private javax.swing.JButton btnSales;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPasswordField tvPassword;
    private javax.swing.JTextField tvMobile;
    private JLabel loginTypeLabel;
    
    private String loginType=Constants.ADMIN;
    private SQLService service;
    
                 
}
