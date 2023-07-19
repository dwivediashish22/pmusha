package org.nic.pmusha.usereo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the state_naac_accreditation_details database table.
 */
@Entity
@Table(name = "state_naac_accreditation_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StateNaacAccreditationDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "naac_accreditation_id", insertable = false, updatable = false)
    private RefNaacAccreditation naacAccreditation;

    @Column(name = "no_institution")
    private Integer noInstitution;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "pmusha_institution_type_id", insertable = false, updatable = false)
    private RefPmUshaInstitutionTypeEO pmushaInstitutionType;

    @Column(name = "state_code")
    private String stateCode;

    @JsonIgnore
    @Column(name = "naac_accreditation_id")
    private Integer naacAccreditationId;

    @JsonIgnore
    @Column(name = "pmusha_institution_type_id")
    private Integer pmushaInstitutionTypeId;


}