package org.nic.pmusha.usereo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity 
@Table(name="user_master") 
public class UserMaster {
	
	//@Id
	//@Column(name="user_id")
	//private Integer userId;
	@Id
	@Column(name="user_id")
	private String userName;
	
	@Column(name = "user_type_id")
	private Integer userTypeId;
	
	@Column(name = "first_name")
    private String firstName;
	
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "address_line1")
    private String addressLine1;
    
    @Column(name = "address_line2")
    private String addressLine2;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "state_id")
    private String stateCode;
    
    @Column(name = "district_id")
    private String districtId;
    
    @Column(name = "phone_landline")
    private String phoneLandline;
    
    @Column(name = "mobile_no")
    private String mobile;
    
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="created_datetime")
	private LocalDateTime createdDateTime;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="status_id")
	private Boolean status;
			
	@Column(name="gender")
	private Integer gender;
	
	@Column(name="user_password")
	private String bcryptPassword;
	
	@Transient
	private String userType;
	@Transient
	private String stateName;
	@Transient
	private String districtName;
}