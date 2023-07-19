package org.nic.pmusha.userdao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.nic.pmusha.enums.UserActionConstant;
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
import org.nic.pmusha.usereo.UserActionLogEO;
import org.nic.pmusha.usereo.UserMaster;
import org.nic.pmusha.uservo.FoucsDistrictIndicatorVO;
import org.nic.pmusha.uservo.UsersVO;
import org.nic.pmusha.utility.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	BCryptPasswordEncoder bcrypt;

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public UserMaster fetchUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		try {
			// String userid = username.toLowerCase();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" from UserMaster where lower(userName)=:userId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("userId", username.toLowerCase());
			List<UserMaster> list = query.getResultList();
			if (list.size() > 0) {
				return list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	/*
	 * try (Session session = sessionFactory.openSession()) { CriteriaBuilder cb =
	 * session.getCriteriaBuilder(); CriteriaQuery<UsersEO> cQuery =
	 * cb.createQuery(UsersEO.class); Root<UsersEO> root =
	 * cQuery.from(UsersEO.class); cQuery.where(cb.and(cb.equal(root.get("loginId"),
	 * username.toLowerCase())), (cb.equal(root.get("isApproved"), 1)));
	 * TypedQuery<UsersEO> query = session.createQuery(cQuery); return
	 * query.getSingleResult(); } catch (Exception e) {
	 * logger.info("Error In fetchUserByUsername" + e.getMessage()); } return null;
	 * }
	 */

	@Override
	public Boolean hasURLAccess(String username, String url) {

		try (Session session = sessionFactory.openSession()) {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select exists( ");
			queryBuilder.append("select u.email from user_master u where u.email=:loginId)");
			// ,urm.roles_record_id,urmpm.fk_submenu_id,mmum.menu_url
			// queryBuilder.append("join user_roles_mapping urm on
			// u.record_id=urm.user_record_id ");
			// queryBuilder
			// .append("join user_roles_menu_privileges_mapping urmpm on
			// urmpm.fk_role_id=urm.roles_record_id ");
			// queryBuilder.append("join m_menu_url_mapping mmum on
			// mmum.record_id=urmpm.fk_submenu_id ");
			// queryBuilder.append("where u.login_id=:loginId and mmum.menu_url=:menuUrl )
			// ");
			return (Boolean) session.createNativeQuery(queryBuilder.toString()).setParameter("loginId", username)
					// .setParameter("menuUrl", url)
					.getSingleResult();
		}
	}

	@Override
	public Boolean isUserNameExist(String username) {
		logger.info("UserDaoImpl : isUserNameExist Method Invoked");
		Session session = sessionFactory.openSession();
		try {
			Long count = (Long) session
					.createQuery("select count(*) from UserMaster where lower(emailId)='" + username + "'")
					.uniqueResult();
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.info("isUserNameExist Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public UsersVO fetchUserDetailsFromUsername(String username) {
		logger.info("UserDaoImpl : fetchUserDetailsFromUsername Method Invoked");
		Session session = sessionFactory.openSession();
		UsersVO usersVO = new UsersVO();
		try {
			// change needed
			BigInteger recordId = (BigInteger) session
					.createQuery("select count(*) from UserMaster where emailId='" + username + "'").uniqueResult();
			Long idret = null;
			if (recordId != null) {
				idret = Long.valueOf(recordId.toString());
			}
			usersVO.setId(idret);
			return usersVO;
		} catch (Exception e) {
			logger.info("isUserNameExist Error" + e.getMessage());
		} finally {
			session.close();
		}
		return usersVO;
	}

	@Override
	public List<UserMaster> fetchUserDetailsFromUserId(String userId) {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<UserMaster> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(
					"select user_id,user_type_id,first_name,middle_name,last_name,address_line1,address_line2,city,state_id,phone_landline,mobile_no,email_id,\r\n"
							+ "ip_address,status_id,gender,rut.user_type,district_id,rs.name as statename,rd.name as distname from user_master um left join ref_user_type rut on rut.id = um.user_type_id"
							+ " left join ref_state rs on rs.st_code =state_id left join ref_district rd on rd.dist_code = district_id  ");
			if (userId != null) {
				queryBuilder.append(" where LOWER(user_id) ='" + userId.toLowerCase() + "'");
			}
			queryBuilder.append(" order by user_id asc");
			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				UserMaster examinationResult = new UserMaster();
//				if (object[0] != null) {
//					examinationResult.setUserId(Integer.parseInt(object[0].toString()));
//				}
				if (object[0] != null) {
					examinationResult.setUserName(object[0].toString());
				}
				if (object[1] != null) {
					examinationResult.setUserTypeId(Integer.parseInt(object[1].toString()));
				}
				if (object[2] != null) {
					examinationResult.setFirstName(object[2].toString());
				}
				if (object[3] != null) {
					examinationResult.setMiddleName(object[3].toString());
				}
				if (object[4] != null) {
					examinationResult.setLastName(object[4].toString());
				}
				if (object[5] != null) {
					examinationResult.setAddressLine1(object[5].toString());
				}
				if (object[6] != null) {
					examinationResult.setAddressLine2(object[6].toString());
				}
				if (object[7] != null) {
					examinationResult.setCity(object[7].toString());
				}
				if (object[8] != null) {
					examinationResult.setStateCode(object[8].toString());
				}
				if (object[9] != null) {
					examinationResult.setPhoneLandline(object[9].toString());
				}
				if (object[10] != null) {
					examinationResult.setMobile(object[10].toString());
				}
				if (object[11] != null) {
					examinationResult.setEmailId(object[11].toString());
				}
				if (object[12] != null) {
					examinationResult.setIpAddress(object[12].toString());
				}
				if (object[13] != null) {
					examinationResult.setStatus(Boolean.parseBoolean(object[13].toString()));
				}
				if (object[14] != null) {
					examinationResult.setGender(Integer.parseInt(object[14].toString()));
				}
				if (object[15] != null) {
					examinationResult.setUserType(object[15].toString());
				}
				if (object[16] != null) {
					examinationResult.setDistrictId(object[16].toString());
				}
				if (object[17] != null) {
					examinationResult.setStateName(object[17].toString());
				}
				if (object[18] != null) {
					examinationResult.setDistrictName(object[18].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<FocusDistrictEO> saveOrUpdateFocusDistrict(List<FocusDistrictEO> focusDistrict,
			HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			 List<Integer> focud = new ArrayList<>();
			for (FocusDistrictEO data : focusDistrict) {
				if (data.getId() == 0) {
					data.setId(null);
				}
				data.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
				if (request != null) {
					data.setIpAddress(request.getHeader("X-Forwarded-For"));
					if (data.getIpAddress() == null || "".equals(data.getIpAddress())) {
						data.setIpAddress(request.getRemoteAddr());
					}
				}
                
				session.saveOrUpdate(data);
				focud.add(data.getId());
			}
          
		  List<Integer> ids = fetchIdFromStateId(focusDistrict.get(0).getStateId());
		  if(ids!=null) {
		   for(Integer idsss : ids) {
			   if(!focud.contains(idsss)) {
				   FocusDistrictEO focusd = (FocusDistrictEO)session.get(FocusDistrictEO.class, idsss);
					session.delete(focusd);
				}
		   }
		  }
			tx.commit();
			return focusDistrict;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return null;
	}

	private List<Integer> fetchIdFromStateId(String stateId) {
		Session session = sessionFactory.openSession();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select id from public.focus_district where state_id ='" + stateId + "'");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Integer> countryListData = (List<Integer>) query.getResultList();
			return countryListData;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<FocusDistrictEO> fetchAllFocusDistrictFromUserId(String userId) {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<FocusDistrictEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(
					"select id,state_id,focus_district,remarks,created_by_user_id,created_on,modified_by_user_id,modified_on,deleted_by_user_id,deleted_on,is_active,\r\n"
							+ "ip_address from focus_district ");
			if (userId != null) {
				queryBuilder.append(" where created_by_user_id ='" + userId + "'");
			}
			queryBuilder.append(" order by created_by_user_id asc");
			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				FocusDistrictEO examinationResult = new FocusDistrictEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setStateId(object[1].toString());
				}
				if (object[2] != null) {
					examinationResult.setFocusDistrict(object[2].toString());
				}
				if (object[3] != null) {
					examinationResult.setRemarks(object[3].toString());
				}
				if (object[4] != null) {
					examinationResult.setCreatedByUserId(object[4].toString());
				}
				if (object[5] != null) {
					examinationResult.setCreatedOnString(object[5].toString());
					Timestamp ts = Timestamp.valueOf(examinationResult.getCreatedOnString());
					LocalDateTime dob = ts.toLocalDateTime();
					examinationResult.setCreatedOnString(DateUtils.convertDBDateTimeToStringNew(dob));
				}
				if (object[6] != null) {
					examinationResult.setModifiedByUserId(object[6].toString());
				}
				if (object[7] != null) {
					examinationResult.setModifiedOnString(object[7].toString());
					Timestamp ts = Timestamp.valueOf(examinationResult.getModifiedOnString());
					LocalDateTime dob = ts.toLocalDateTime();
					examinationResult.setModifiedOnString(DateUtils.convertDBDateTimeToStringNew(dob));
				}
				if (object[10] != null) {
					examinationResult.setIsActive(Boolean.parseBoolean(object[10].toString()));
				}
				if (object[11] != null) {
					examinationResult.setIpAddress(object[11].toString());
				}

				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefComponentsEO> fetchAllComponent() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefComponentsEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,component_name FROM ref_pm_usha_component ORDER BY id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefComponentsEO examinationResult = new RefComponentsEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setComponentName(object[1].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefIndicatorsEO> fetchAllIndicators() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefIndicatorsEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,indicator_name FROM ref_indicator ORDER BY id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefIndicatorsEO examinationResult = new RefIndicatorsEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setIndicatorName(object[1].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefActivityMasterEO> fetchAllActivity() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefActivityMasterEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,activity_name FROM ref_component_activity ORDER BY id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefActivityMasterEO examinationResult = new RefActivityMasterEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setActivityName(object[1].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefUserRoleMasterEO> fetchAllUserRole() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefUserRoleMasterEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT role_id,role_name,level FROM ref_user_role_master ORDER BY role_id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefUserRoleMasterEO examinationResult = new RefUserRoleMasterEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setRoleName(object[1].toString());
				}
				if (object[2] != null) {
					examinationResult.setLevel(object[2].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefUserTypeEO> fetchAllUserType() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefUserTypeEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,user_type FROM ref_user_type ORDER BY id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefUserTypeEO examinationResult = new RefUserTypeEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setUserType(object[1].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public MouDocumentsEO saveOrUpdateMou(MouDocumentsEO letter) {
		logger.info("CisoDaoImpl : saveCiso method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        UserActionLogEO userActionLog = new UserActionLogEO();
        try {
            tx = session.beginTransaction();
            if(letter.getId()==0) {
            	session.save(letter);
            	userActionLog.setId(null);
            	userActionLog.setActionId(9);
            	userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
            	userActionLog.setIpAddress(letter.getIpAddress());
            	userActionLog.setUserId(null);
            	
            }else {
            	session.update(letter);
            }
            tx.commit();
            return letter;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

	@Override
	public List<MouDocumentsEO> fetchAllMouDocuments(String userId) {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<MouDocumentsEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(
					"select id,mou_title,document_type,document_file,file_name,uploaded_by,is_active,\r\n"
							+ "ip_address,state_code from mou_documents ");
			if (userId != null) {
				queryBuilder.append(" where lower(uploaded_by) ='" + userId.toLowerCase() + "'");
			}
			queryBuilder.append(" order by uploaded_by asc");
			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				MouDocumentsEO examinationResult = new MouDocumentsEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setMouTitle(object[1].toString());
				}
				if (object[2] != null) {
					examinationResult.setDocumentType(object[2].toString());
				}
				if (object[3] != null) {
					examinationResult.setDocumentFile((byte[]) object[3]);
				}
				if (object[4] != null) {
					examinationResult.setFileName(object[4].toString());
				}
				if (object[5] != null) {
					examinationResult.setUploadedBy(object[5].toString());
				}
				if (object[6] != null) {
					examinationResult.setIsActive(Boolean.parseBoolean(object[6].toString()));
				}
				if (object[7] != null) {
					examinationResult.setIpAddress(object[7].toString());
				}
				if (object[8] != null) {
					examinationResult.setStateCode(object[8].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<ActivityDetailsEO> saveOrUpdateActivityDetails(List<ActivityDetailsEO> activityDetails,
			HttpServletRequest request) {
		logger.info("CisoDaoImpl : saveCiso method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (ActivityDetailsEO data : activityDetails) {
				if (data.getId() == 0) {
					data.setId(null);
				}
				data.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
				if (request != null) {
					data.setIpAddress(request.getHeader("X-Forwarded-For"));
					if (data.getIpAddress() == null || "".equals(data.getIpAddress())) {
						data.setIpAddress(request.getRemoteAddr());
					}
				}

				session.saveOrUpdate(data);
			}
			tx.commit();
			return activityDetails;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<ActivityDetailsEO> fetchAllActivityDetails(String userId) {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<ActivityDetailsEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(
					"select activity_details.id,activity_id,component_id,aishe_code,answer_provided,remarks,created_by_user_id,created_on,modified_by_user_id,modified_on,deleted_by_user_id,deleted_on,is_active,\r\n"
							+ "ip_address,ram.activity_name,rc.component_name from activity_details left join ref_component_activity ram on ram.id = activity_details.id\r\n"
							+ "left join ref_pm_usha_component rc on rc.id = activity_details.component_id ");
			if (userId != null) {
				queryBuilder.append(" where LOWER(created_by_user_id) ='" + userId.toLowerCase() + "'");
			}
			queryBuilder.append(" order by created_by_user_id asc");
			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				ActivityDetailsEO examinationResult = new ActivityDetailsEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setActivityId(Integer.parseInt(object[1].toString()));
				}
				if (object[2] != null) {
					examinationResult.setComponentId(Integer.parseInt(object[2].toString()));
				}
				if (object[3] != null) {
					examinationResult.setInstituteId(object[3].toString());
				}
				if (object[4] != null) {
					examinationResult.setAnswerProvided(object[4].toString());
				}
				if (object[5] != null) {
					examinationResult.setRemarks(object[5].toString());
				}
				if (object[6] != null) {
					examinationResult.setCreatedByUserId(object[6].toString());
				}
				if (object[7] != null) {
					examinationResult.setCreatedOnString(object[7].toString());
					Timestamp ts = Timestamp.valueOf(examinationResult.getCreatedOnString());
					LocalDateTime dob = ts.toLocalDateTime();
					examinationResult.setCreatedOnString(DateUtils.convertDBDateTimeToStringNew(dob));
				}
				if (object[8] != null) {
					examinationResult.setModifiedByUserId(object[8].toString());
				}
				if (object[9] != null) {
					examinationResult.setModifiedOnString(object[9].toString());
					Timestamp ts = Timestamp.valueOf(examinationResult.getModifiedOnString());
					LocalDateTime dob = ts.toLocalDateTime();
					examinationResult.setModifiedOnString(DateUtils.convertDBDateTimeToStringNew(dob));
				}
				if (object[12] != null) {
					examinationResult.setIsActive(Boolean.parseBoolean(object[12].toString()));
				}
				if (object[13] != null) {
					examinationResult.setIpAddress(object[13].toString());
				}
				if (object[14] != null) {
					examinationResult.setActivityName(object[14].toString());
				}
				if (object[15] != null) {
					examinationResult.setComponentName(object[15].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<ComponentInstituteMappingEO> saveOrUpdateComponentInstituteMapping(
			List<ComponentInstituteMappingEO> componentInstituteMapping, HttpServletRequest request) {
		logger.info("CisoDaoImpl : saveCiso method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			 List<Integer> focud = new ArrayList<>();
			for (ComponentInstituteMappingEO data : componentInstituteMapping) {
				if (data.getId() == 0) {
					data.setId(null);
				}
				data.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
				if (request != null) {
					data.setIpAddress(request.getHeader("X-Forwarded-For"));
					if (data.getIpAddress() == null || "".equals(data.getIpAddress())) {
						data.setIpAddress(request.getRemoteAddr());
					}
				}

				session.saveOrUpdate(data);
				focud.add(data.getId());
				
			}
			  List<Integer> ids = fetchIdFromComponentInstituteMapping(componentInstituteMapping.get(0).getComponentId(),componentInstituteMapping.get(0).getInstituteId());
			  if(ids!=null) {
			   for(Integer idsss : ids) {
				   if(!focud.contains(idsss)) {
					   ComponentInstituteMappingEO focusd = (ComponentInstituteMappingEO)session.get(ComponentInstituteMappingEO.class, idsss);
						session.delete(focusd);
					}
			   }
			  }
			tx.commit();
			return componentInstituteMapping;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return null;
	}

	private List<Integer> fetchIdFromComponentInstituteMapping(Integer componentId, String instituteId) {
		Session session = sessionFactory.openSession();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select id from public.component_institute_mapping where component_id ='" + componentId + "' and aishe_code='"+instituteId+"'");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Integer> countryListData = (List<Integer>) query.getResultList();
			return countryListData;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<ComponentInstituteMappingEO> fetchAllComponentInstituteMapping(String userId) {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<ComponentInstituteMappingEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(
					"select rcm.id,component_id,focus_district,aishe_code,created_by_user_id,created_on,modified_by_user_id,modified_on,is_active,ip_address,rd.name,rc.component_name\r\n"
					+ "from component_institute_mapping rcm left join ref_district rd on rd.dist_code = rcm.focus_district\r\n"
					+ "left join ref_pm_usha_component rc on rc.id = rcm.component_id ");
			if (userId != null) {
				queryBuilder.append(" where LOWER(created_by_user_id) ='" + userId.toLowerCase() + "'");
			}
			queryBuilder.append(" order by created_by_user_id asc");
			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				ComponentInstituteMappingEO examinationResult = new ComponentInstituteMappingEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setComponentId(Integer.parseInt(object[1].toString()));
				}
				if (object[2] != null) {
					examinationResult.setFocusDistrict(object[2].toString());
				}
				if (object[3] != null) {
					examinationResult.setInstituteId(object[3].toString());
				}
				if (object[4] != null) {
					examinationResult.setCreatedByUserId(object[4].toString());
				}
				if (object[5] != null) {
					examinationResult.setCreatedOnString(object[5].toString());
					Timestamp ts = Timestamp.valueOf(examinationResult.getCreatedOnString());
					LocalDateTime dob = ts.toLocalDateTime();
					examinationResult.setCreatedOnString(DateUtils.convertDBDateTimeToStringNew(dob));
				}
				if (object[6] != null) {
					examinationResult.setModifiedByUserId(object[6].toString());
				}
				if (object[7] != null) {
					examinationResult.setModifiedOnString(object[7].toString());
					Timestamp ts = Timestamp.valueOf(examinationResult.getModifiedOnString());
					LocalDateTime dob = ts.toLocalDateTime();
					examinationResult.setModifiedOnString(DateUtils.convertDBDateTimeToStringNew(dob));
				}
				if (object[8] != null) {
					examinationResult.setIsActive(Boolean.parseBoolean(object[8].toString()));
				}
				if (object[9] != null) {
					examinationResult.setIpAddress(object[9].toString());
				}
				if (object[10] != null) {
					examinationResult.setDistrictName(object[10].toString());
				}
				if (object[11] != null) {
					examinationResult.setComponentName(object[11].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefDistrictEO> saveOrUpdateFocusDistrictData(List<RefDistrictEO> focusDistrict,
			HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (RefDistrictEO data : focusDistrict) {
				session.saveOrUpdate(data);
			}
			if (request != null) {
				UserActionLogEO useraction = new UserActionLogEO();
				useraction.setId(null);
				useraction.setActionId(UserActionConstant.ADD_FOCUS_DISTRICT);
				useraction.setActionTime(DateUtils.obtainCurrentTimeStamp());
				useraction.setIpAddress(request.getHeader("X-Forwarded-For"));
				useraction.setUserId("saa07");
				saveUserActionLog(useraction);
			}
			tx.commit();
			return focusDistrict;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return null;
	}
	@Override
	public void saveUserActionLog(UserActionLogEO useraction) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(useraction);
			tx.commit();
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public List<FoucsDistrictIndicatorVO> fetchAllFocusDistrict(String stateId) {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<FoucsDistrictIndicatorVO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(
					"select dist_code,st_code,name,lgd_district_code,is_focus, fid.id,fid.focus_district_indicator_id,fid.indicator_value from ref_district\r\n"
					+ "	left join focus_district_indicator_data fid on fid.district_code = ref_district.dist_code ");
			if (stateId != null) {
				queryBuilder.append(" where st_code ='" + stateId + "'");
			}
			queryBuilder.append(" order by dist_code asc");
			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				FoucsDistrictIndicatorVO examinationResult = new FoucsDistrictIndicatorVO();
				if (object[0] != null) {
					examinationResult.setDistCode(object[0].toString());
				}
				if (object[1] != null) {
					examinationResult.setStCode(object[1].toString());
				}
				if (object[2] != null) {
					examinationResult.setName(object[2].toString());
				}
				if (object[3] != null) {
					examinationResult.setLgdDistrictCode(Integer.parseInt(object[3].toString()));
				}
				if (object[4] != null) {
					examinationResult.setFocus(Boolean.parseBoolean(object[4].toString()));
				}
				if (object[5] != null) {
					examinationResult.setId(Integer.parseInt(object[5].toString()));
				}
				if (object[6] != null) {
					examinationResult.setFocusDistrictIndicatorId(Integer.parseInt(object[6].toString()));
				}
				if (object[7] != null) {
					examinationResult.setIndicatorValue(object[7].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefDocumentEO> fetchAllDocumentType() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefDocumentEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,document_name FROM ref_document_type ORDER BY id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefDocumentEO examinationResult = new RefDocumentEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setDocumentName(object[1].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefGenderEO> fetchAllGender() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefGenderEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,gender_name FROM ref_gender ORDER BY id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefGenderEO examinationResult = new RefGenderEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setGenderName(object[1].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefStateEO> fetchAllState() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefStateEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT st_code,name,lgd_code FROM ref_state ORDER BY st_code asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefStateEO examinationResult = new RefStateEO();
				if (object[0] != null) {
					examinationResult.setStCode(object[0].toString());
				}
				if (object[1] != null) {
					examinationResult.setName(object[1].toString());
				}
				if (object[2] != null) {
					examinationResult.setLgdCode(Integer.parseInt(object[2].toString()));
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
			logger.info("fetchCourseAllList Error" + e.getMessage());
		} finally {
			session.close();
		}
		return examinationResultList;
	}

	@Override
	public List<RefStateCategoryEO> fetchAllStateCategory() {
		logger.info("MasterDaoImpl : RefMonitoringScheduleEO Method Invoked");
		Session session = sessionFactory.openSession();
		List<RefStateCategoryEO> examinationResultList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,category_name FROM ref_state_category ORDER BY id asc");

			Query query = session.createNativeQuery(queryBuilder.toString());

			List<Object[]> countryListData = (List<Object[]>) query.getResultList();
			for (Object[] object : countryListData) {
				RefStateCategoryEO examinationResult = new RefStateCategoryEO();
				if (object[0] != null) {
					examinationResult.setId(Integer.parseInt(object[0].toString()));
				}
				if (object[1] != null) {
					examinationResult.setCategoryName(object[1].toString());
				}
				examinationResultList.add(examinationResult);
			}
			return examinationResultList;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return examinationResultList;
	}


	@Override
	public List<RefPmUshaInstitutionTypeEO> fetchAllPmUshaInstitutionType() {
		logger.info("UserDaoImpl : fetchAllPmUshaInstitutionType Method Invoked");
		Session session = sessionFactory.openSession();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT id,type FROM ref_pmusha_institution_type ORDER BY id asc");
			Query query = session.createNativeQuery(queryBuilder.toString(), RefPmUshaInstitutionTypeEO.class);
			return query.getResultList();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<RefDistrictIndicatorsEO> fetchAllDistrictIndicators() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from RefDistrictIndicatorsEO  ORDER BY id asc", RefDistrictIndicatorsEO.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RefNaacAccreditation> fetchAllNaacAccreditation() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from RefNaacAccreditation  ORDER BY id asc", RefNaacAccreditation.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}