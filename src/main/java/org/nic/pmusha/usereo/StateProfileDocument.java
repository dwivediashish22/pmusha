package org.nic.pmusha.usereo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * The persistent class for the state_profile_document database table.
 */
@Entity
@Table(name = "state_profile_document")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StateProfileDocument implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "document_file")
    private byte[] documentFile;


    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "document_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private RefDocumentEO documentType;

    @Column(name = "state_code")
    private String stateCode;

}