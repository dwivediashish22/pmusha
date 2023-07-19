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
 * The persistent class for the state_population database table.
 */
@Entity
@Table(name = "state_population")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatePopulation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "gender_id", referencedColumnName = "id", insertable = false, updatable = false)
    private RefGenderEO gender;

    @Column(name = "grand_total")
    private Integer grandTotal;

    @Column(name = "obc_percentage_gtotal")
    private Integer obcPercentageGtotal;

    @Column(name = "obc_total")
    private Integer obcTotal;

    @Column(name = "other_percentage_gtotal")
    private Integer otherPercentageGtotal;

    @Column(name = "other_total")
    private Integer otherTotal;

    @Column(name = "sc_percentage_gtotal")
    private Integer scPercentageGtotal;

    @Column(name = "sc_total")
    private Integer scTotal;

    private String source;

    @Column(name = "st_percentage_gtotal")
    private Integer stPercentageGtotal;

    @Column(name = "st_total")
    private Integer stTotal;

    @Column(name = "state_code")
    private String stateCode;

    private Integer year;
    @JsonIgnore
    @Column(name = "gender_id")
    private Integer genderId;


}