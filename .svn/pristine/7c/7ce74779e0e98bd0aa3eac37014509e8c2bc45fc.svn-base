package org.nic.pmusha.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.nic.pmusha.usereo.FocusDistrictIndicatorData;
import org.nic.pmusha.usereo.StateBasicInfoEO;
import org.nic.pmusha.usereo.StateExpenditureHe;
import org.nic.pmusha.usereo.StateHeiDataGerEnrollment;
import org.nic.pmusha.usereo.StateHeiDataGpiPtrRatio;
import org.nic.pmusha.usereo.StateHeiNumberCountEnrollment;
import org.nic.pmusha.usereo.StateNaacAccreditationDetail;
import org.nic.pmusha.usereo.StatePopulation;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the state_basic_info database table.
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StateBasicInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private @Valid StateBasicInfoEO stateBasicInfo;

    private @Valid Set<StatePopulation> statePopulations;


    private @Valid Set<StateHeiDataGerEnrollment> stateHeiDataGerEnrollments;


    private @Valid Set<StateHeiDataGpiPtrRatio> stateHeiDataGpiPtrRatios;


    private @Valid Set<StateHeiNumberCountEnrollment> stateHeiNumberCountEnrollments;


    private @Valid Set<StateNaacAccreditationDetail> stateNaacAccreditationDetails;


    private @Valid Set<StateExpenditureHe> stateExpenditureOnHigherEducations;

    private @Valid Set<FocusDistrictIndicatorData> focusDistrictIndicatorData;

}