package org.nic.pmusha.usereo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="focus_district_indicator_data") 
public class FocusDistrictDataEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5292318515092014785L;

	@Id
	@GenericGenerator(name="focusdistrictdata" , strategy="increment")
	@GeneratedValue(generator="focusdistrictdata")
	@Column(name="id")
	private Integer Id;
	
	@Column(name="state_code")
	private String stateId;
	
	@Column(name="district_code")
	private String districtCode;
	
	@Column(name="focus_district_indicator_id")
	private Integer focusDistrictIndicatorId;
	
	@Column(name="indicator_value")
	private String indicatorValue;
}