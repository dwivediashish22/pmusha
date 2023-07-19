package org.nic.pmusha.service;

import org.nic.pmusha.usereo.ActivityDetailsEO;
import org.nic.pmusha.usereo.ComponentInstituteMappingEO;
import org.nic.pmusha.usereo.FocusDistrictEO;
import org.nic.pmusha.usereo.MouDocumentsEO;
import org.nic.pmusha.usereo.RefActivityMasterEO;
import org.nic.pmusha.usereo.RefComponentsEO;
import org.nic.pmusha.usereo.RefDistrictEO;
import org.nic.pmusha.usereo.RefDistrictIndicatorsEO;
import org.nic.pmusha.usereo.RefDocumentEO;
import org.nic.pmusha.usereo.RefGenderEO;
import org.nic.pmusha.usereo.RefIndicatorsEO;
import org.nic.pmusha.usereo.RefNaacAccreditation;
import org.nic.pmusha.usereo.RefPmUshaInstitutionTypeEO;
import org.nic.pmusha.usereo.RefStateCategoryEO;
import org.nic.pmusha.usereo.RefStateEO;
import org.nic.pmusha.usereo.RefUserRoleMasterEO;
import org.nic.pmusha.usereo.RefUserTypeEO;
import org.nic.pmusha.usereo.UserMaster;
import org.nic.pmusha.uservo.FoucsDistrictIndicatorVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    Boolean hasURLAccess(String username, String url);

    Boolean isUserNameExist(String username);

    Long fetchUserDetailsFromUsername(String username);

    List<UserMaster> fetchUserDetailsFromUserId(String userId);

    List<FocusDistrictEO> saveOrUpdateFocusDistrict(List<FocusDistrictEO> userMaster, HttpServletRequest request);

    List<FocusDistrictEO> fetchAllFocusDistrictFromUserId(String userId);

    List<RefComponentsEO> fetchAllComponent();

    List<RefIndicatorsEO> fetchAllIndicators();

    List<RefActivityMasterEO> fetchAllActivity();

    List<RefUserRoleMasterEO> fetchAllUserRole();

    List<RefUserTypeEO> fetchAllUserType();

    MouDocumentsEO saveOrUpdateMou(MouDocumentsEO letter);

    List<MouDocumentsEO> fetchAllMouDocuments(String userId);

    List<ActivityDetailsEO> saveOrUpdateActivityDetails(List<ActivityDetailsEO> activityDetails,
                                                        HttpServletRequest request);

    List<ActivityDetailsEO> fetchAllActivityDetails(String userId);

    List<ComponentInstituteMappingEO> saveOrUpdateComponentInstituteMapping(
            List<ComponentInstituteMappingEO> componentInstituteMapping, HttpServletRequest request);

    List<ComponentInstituteMappingEO> fetchAllComponentInstituteMapping(String userId);

    List<RefDistrictEO> saveOrUpdateFocusDistrictData(List<RefDistrictEO> userMaster, HttpServletRequest request);

    List<FoucsDistrictIndicatorVO> fetchAllFocusDistrict(String stateId);

    List<RefDocumentEO> fetchAllDocumentType();

    List<RefGenderEO> fetchAllGender();

    List<RefStateEO> fetchAllState();

    List<RefStateCategoryEO> fetchAllStateCategory();

    List<RefPmUshaInstitutionTypeEO> fetchAllPmUshaInstitutionType();


    List<RefDistrictIndicatorsEO> fetchAllDistrictIndicators();

    List<RefNaacAccreditation> fetchAllNaacAccreditation();

}