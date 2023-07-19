package org.nic.pmusha.userdao;

import org.nic.pmusha.responseDto.StateBasicInfoDTO;
import org.nic.pmusha.usereo.StateBasicInfo;
import org.nic.pmusha.usereo.StateBasicInfoEO;
import org.nic.pmusha.usereo.StateProfileDocument;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

public interface StateProfileDao {
    StateBasicInfoDTO update(StateBasicInfoDTO basicInfo);

    StateBasicInfo getAll(String stateCode);

    StateBasicInfoEO get(String stateCode);

    StateBasicInfoEO save(StateBasicInfoEO basicInfo, HttpServletRequest request);

    StateBasicInfoEO update(StateBasicInfoEO basicInfo, HttpServletRequest request);

    BigInteger getTotalDistrict(String stateCode);

    StateProfileDocument getProfile(String stateCode);
}
