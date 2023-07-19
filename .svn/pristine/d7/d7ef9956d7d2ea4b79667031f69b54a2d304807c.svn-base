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
 * The persistent class for the state_hei_number_count_enrollment database table.
 */
@Entity
@Table(name = "state_hei_number_count_enrollment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StateHeiNumberCountEnrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer enrollment;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "pmusha_institution_type_id", insertable = false, updatable = false)
    private RefPmUshaInstitutionTypeEO pmushaInstitutionType;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "total_no")
    private Integer totalNo;

    private Integer year;
    @JsonIgnore
    @Column(name = "pmusha_institution_type_id")
    private Integer pmushaInstitutionTypeId;

}