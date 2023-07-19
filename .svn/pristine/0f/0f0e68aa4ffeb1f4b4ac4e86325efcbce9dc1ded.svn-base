package org.nic.pmusha.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nic.pmusha.userdao.UserDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesimpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public Boolean hasURLAccess(String username, String url) {
		return userDao.hasURLAccess(username, url);
	}

	@Override
	public Boolean isUserNameExist(String username) {
		return userDao.isUserNameExist(username.toLowerCase());
	}

	@Override
	public Long fetchUserDetailsFromUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserMaster> fetchUserDetailsFromUserId(String userId) {
		return userDao.fetchUserDetailsFromUserId(userId);
	}

	@Override
	public List<FocusDistrictEO> saveOrUpdateFocusDistrict(List<FocusDistrictEO> focusDistrict,
			HttpServletRequest request) {
		return userDao.saveOrUpdateFocusDistrict(focusDistrict, request);
	}

	@Override
	public List<FocusDistrictEO> fetchAllFocusDistrictFromUserId(String userId) {
		return userDao.fetchAllFocusDistrictFromUserId(userId);
	}

	@Override
	public List<RefComponentsEO> fetchAllComponent() {
		return userDao.fetchAllComponent();
	}

	@Override
	public List<RefIndicatorsEO> fetchAllIndicators() {
		return userDao.fetchAllIndicators();
	}

	@Override
	public List<RefActivityMasterEO> fetchAllActivity() {
		return userDao.fetchAllActivity();
	}

	@Override
	public List<RefUserRoleMasterEO> fetchAllUserRole() {
		return userDao.fetchAllUserRole();
	}

	@Override
	public List<RefUserTypeEO> fetchAllUserType() {
		return userDao.fetchAllUserType();
	}

	@Override
	public MouDocumentsEO saveOrUpdateMou(MouDocumentsEO letter) {
		return userDao.saveOrUpdateMou(letter);
	}

	@Override
	public List<MouDocumentsEO> fetchAllMouDocuments(String userId) {
		return userDao.fetchAllMouDocuments(userId);
	}

	@Override
	public List<ActivityDetailsEO> saveOrUpdateActivityDetails(List<ActivityDetailsEO> activityDetails,
			HttpServletRequest request) {
		return userDao.saveOrUpdateActivityDetails(activityDetails,request);
	}

	@Override
	public List<ActivityDetailsEO> fetchAllActivityDetails(String userId) {
		return userDao.fetchAllActivityDetails(userId);
	}

	@Override
	public List<ComponentInstituteMappingEO> saveOrUpdateComponentInstituteMapping(
			List<ComponentInstituteMappingEO> componentInstituteMapping, HttpServletRequest request) {
		return userDao.saveOrUpdateComponentInstituteMapping(componentInstituteMapping,request);
	}

	@Override
	public List<ComponentInstituteMappingEO> fetchAllComponentInstituteMapping(String userId) {
		return userDao.fetchAllComponentInstituteMapping(userId);
	}

	@Override
	public List<RefDistrictEO> saveOrUpdateFocusDistrictData(List<RefDistrictEO> userMaster,
			HttpServletRequest request) {
		return userDao.saveOrUpdateFocusDistrictData(userMaster, request);
	}

	@Override
	public List<FoucsDistrictIndicatorVO> fetchAllFocusDistrict(String stateId) {
		return userDao.fetchAllFocusDistrict(stateId);
	}

	@Override
	public List<RefDocumentEO> fetchAllDocumentType() {
		return userDao.fetchAllDocumentType();
	}

	@Override
	public List<RefGenderEO> fetchAllGender() {
		return userDao.fetchAllGender();
	}

	@Override
	public List<RefStateEO> fetchAllState() {
		return userDao.fetchAllState();
	}

	@Override
	public List<RefStateCategoryEO> fetchAllStateCategory() {
		return userDao.fetchAllStateCategory();
	}

	@Override
	public List<RefPmUshaInstitutionTypeEO> fetchAllPmUshaInstitutionType() {
		return userDao.fetchAllPmUshaInstitutionType();
	}

	@Override
	public List<RefDistrictIndicatorsEO> fetchAllDistrictIndicators() {
		return userDao.fetchAllDistrictIndicators();
	}

	@Override
	public List<RefNaacAccreditation> fetchAllNaacAccreditation() {
		return userDao.fetchAllNaacAccreditation();
	}
}