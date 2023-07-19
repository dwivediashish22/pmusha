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
 * The persistent class for the state_hei_data_ger_enrollment database table.
 */
@Entity
@Table(name = "state_hei_data_ger_enrollment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StateHeiDataGerEnrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer female;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "indicator_id", referencedColumnName = "id", insertable = false, updatable = false)
    private RefIndicatorsEO indicator;

    private Integer male;

    @Column(name = "obc_female")
    private Integer obcFemale;

    @Column(name = "obc_male")
    private Integer obcMale;

    @Column(name = "obc_total")
    private Integer obcTotal;

    @Column(name = "sc_female")
    private Integer scFemale;

    @Column(name = "sc_male")
    private Integer scMale;

    @Column(name = "sc_total")
    private Integer scTotal;

    @Column(name = "st_female")
    private Integer stFemale;

    @Column(name = "st_male")
    private Integer stMale;

    @Column(name = "st_total")
    private Integer stTotal;

    @Column(name = "state_code")
    private String stateCode;

    private Integer total;

    private Integer year;

    @JsonIgnore
    @Column(name = "indicator_id")
    private Integer indicatorId;

}