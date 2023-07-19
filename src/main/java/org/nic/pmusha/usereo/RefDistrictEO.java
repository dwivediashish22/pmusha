package org.nic.pmusha.usereo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.nic.pmusha.jsonbutility.JsonUserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@TypeDef(name = "JsonUserType", typeClass = JsonUserType.class)
@Table(schema="public",name="ref_district") 
public class RefDistrictEO {

	@Id
	@Column(name="dist_code")
	private String distCode;
	
	@Column(name="st_code")
	private String stCode;

	@Column(name="name")
	private String name;

	@Column(name="lgd_district_code")
	private Integer lgdDistrictCode;

	@Column(name="is_focus")
	private boolean isFocus;
	
	//@Transient
	//public List<FocusDistrictDataEO> focusDistrictIndicator;
	//@Type(type = "JsonUserType")
	//@Column(name = "indicator_id", columnDefinition = "jsonb")
	//private Map<String,IndicatorDataVO> indicatorData;
//	select jsonb_object_keys(indicator_id) as ipnew,st_code,dist_code,indicator_id
//	,jsonb_each_text(cast(coalesce(jsonb_extract_path_text(indicator_id,'1'),'{}')as jsonb)) as excluded, jsonb_object_keys((indicator_id)) as excluded2
//	,excluded.value
//	from public.ref_district, jsonb_each_text(cast(coalesce(jsonb_extract_path_text(indicator_id,'1'),'{}')as jsonb)) as excluded
//	, jsonb_object_keys((indicator_id)) as excluded2
//	where st_code ='09' and dist_code in('175','132') and excluded.value= '10'
//	and excluded2='1';
}