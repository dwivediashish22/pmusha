package org.nic.pmusha.usereo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ref_component_activity") //ref_component_activity
public class RefActivityMasterEO {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="activity_name")
	private String activityName;
	
}