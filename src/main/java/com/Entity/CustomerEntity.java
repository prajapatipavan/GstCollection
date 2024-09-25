package com.Entity;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import lombok.Data;

@Entity
@Table(name ="Customer")
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String contactNumber;
    private String email;
    private String gstNumber;
    private String address;
    
    
    @ManyToOne(cascade = CascadeType.REMOVE)  
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "transaction_id") 
    private GstTransaction transaction;
    
    private boolean active=true;
   
	
   
}
