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
@Table(name="ref_indicator") // ref_indicator
public class RefIndicatorsEO {

	@Id
	@Column(name="id")
	private int id;

	
	@Column(name="indicator_name")
	private String indicatorName;
}