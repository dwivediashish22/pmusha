package org.nic.pmusha.usereo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="focus_district") 
public class FocusDistrictEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5292318515092014785L;

	@Id
	@GenericGenerator(name="focusdistrict" , strategy="increment")
	@GeneratedValue(generator="focusdistrict")
	@Column(name="id")
	private Integer Id;
	
	@Column(name="state_id")
	private String stateId;
	
	@Column(name="focus_district")
	private String focusDistrict;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="created_by_user_id")
	private String createdByUserId;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="modified_by_user_id")
	private String modifiedByUserId;
	
	@Column(name="modified_on")
	private LocalDateTime modifiedOn;
	
	@Column(name="deleted_by_user_id")
	private String deletedByUserId;
	
	@Column(name="deleted_on")
	private LocalDateTime deletedOn;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Transient
	private String createdOnString;
	@Transient
	private String modifiedOnString;
}