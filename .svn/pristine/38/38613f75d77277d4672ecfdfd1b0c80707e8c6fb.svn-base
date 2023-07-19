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

@Entity
@Table(name = "focus_district_indicator_data")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FocusDistrictIndicatorData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "district_code")
    private String districtCode;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "focus_district_indicator_id", insertable = false, updatable = false)
    private RefDistrictIndicatorsEO focusDistrictIndicator;

    @Column(name = "indicator_value")
    private String indicatorValue;

    @Column(name = "state_code")
    private String stateCode;
    @JsonIgnore
    @Column(name = "focus_district_indicator_id")
    private Integer focusDistrictIndicatorId;
}
