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
 * The persistent class for the state_hei_data_gpi_ptr_ratio database table.
 */
@Entity
@Table(name = "state_hei_data_gpi_ptr_ratio")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StateHeiDataGpiPtrRatio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "indicator_id", insertable = false, updatable = false)
    private RefIndicatorsEO indicator;

    private String ratio;

    @Column(name = "state_code")
    private String stateCode;

    private Integer year;

    @JsonIgnore
    @Column(name = "indicator_id")
    private Integer indicatorId;

}