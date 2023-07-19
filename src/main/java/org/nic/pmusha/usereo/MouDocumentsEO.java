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
@Table(name="mou_documents") 
public class MouDocumentsEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5292318515092014785L;

	@Id
	@GenericGenerator(name="moudocuments" , strategy="increment")
	@GeneratedValue(generator="moudocuments")
	@Column(name="id")
	private Integer Id;
	
	@Column(name="mou_title")
	private String mouTitle;
	
	@Column(name="document_type")
	private String documentType;
	
	@Column(name="document_file")
	private byte[] documentFile;
	
	@Column(name="uploaded_by")
	private String uploadedBy;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="state_code")
	private String stateCode;
	
}