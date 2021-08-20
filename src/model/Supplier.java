package model;

public class Supplier {
    
    private int id;
    private String firstName=null;
    private String lastName=null;
    private String email=null;
    private String mobile=null;
    private String address=null;
    private String createdAt=null;
    private String details=null;
    private String remarks=null;
    
    public int getId() {
		return id;
	}
    public String getFirstName() {
		return firstName;
	}
    public String getLastName() {
		return lastName;
	}
    public String getEmail() {
		return email;
	}
    
    public String getMobile() {
		return mobile;
	}
    
    public String getAddress() {
		return address;
	}
    
    public String getCreatedAt() {
		return createdAt;
	}
    public String getDetails() {
		return details;
	}
    
    public String getRemarks() {
		return remarks;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
    public void setAddress(String address) {
		this.address = address;
	}
    
    public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
    public void setDetails(String details) {
		this.details = details;
	}
    public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
    public void setSupplierDetails(int id,String firstName,String lastName,String email,String mobile,String address,String createdAt,String details,String remarks) {
    	setId(id);
    	setFirstName(firstName);
    	setLastName(lastName);
    	setEmail(email);
    	setMobile(mobile);
    	setAddress(address);
    	setCreatedAt(createdAt);
    	setDetails(details);
    	setRemarks(remarks);
    	
    }
}
