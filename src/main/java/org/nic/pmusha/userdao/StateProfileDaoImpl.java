package org.nic.pmusha.userdao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.nic.pmusha.enums.UserActionConstant;
import org.nic.pmusha.responseDto.StateBasicInfoDTO;
import org.nic.pmusha.usereo.FocusDistrictIndicatorData;
import org.nic.pmusha.usereo.StateBasicInfo;
import org.nic.pmusha.usereo.StateBasicInfoEO;
import org.nic.pmusha.usereo.StateExpenditureHe;
import org.nic.pmusha.usereo.StateHeiDataGerEnrollment;
import org.nic.pmusha.usereo.StateHeiDataGpiPtrRatio;
import org.nic.pmusha.usereo.StateHeiNumberCountEnrollment;
import org.nic.pmusha.usereo.StateNaacAccreditationDetail;
import org.nic.pmusha.usereo.StatePopulation;
import org.nic.pmusha.usereo.StateProfileDocument;
import org.nic.pmusha.usereo.UserActionLogEO;
import org.nic.pmusha.utility.DateUtils;
import org.nic.pmusha.utility.IpAddressProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Log4j2
public class StateProfileDaoImpl implements StateProfileDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDao;
    /*@Override
    public StateBasicInfo save(StateBasicInfo basicInfo) {
        Transaction tx = null;
        try (Session session = getCurrentSession()) {
            tx = session.beginTransaction();
            session.save(basicInfo);

            Set<StateProfileDocument> stateProfileDocuments = basicInfo.getStateProfileDocuments();
            if (null != stateProfileDocuments && !stateProfileDocuments.isEmpty()) {
                for (StateProfileDocument stateProfileDocument : stateProfileDocuments) {
                    saveObject(session, stateProfileDocument);
                }
            }

            Set<StatePopulation> statePopulations = basicInfo.getStatePopulations();
            if (null != statePopulations && !statePopulations.isEmpty()) {
                for (StatePopulation statePopulation : statePopulations) {
                    saveObject(session, statePopulation);
                }
            }

            Set<StateHeiDataGerEnrollment> stateHeiDataGerEnrollments = basicInfo.getStateHeiDataGerEnrollments();
            if (null != stateHeiDataGerEnrollments && !stateHeiDataGerEnrollments.isEmpty()) {
                for (StateHeiDataGerEnrollment stateHeiDataGerEnrollment : stateHeiDataGerEnrollments) {
                    saveObject(session, stateHeiDataGerEnrollment);
                }
            }

            Set<StateHeiDataGpiPtrRatio> stateHeiDataGpiPtrRatios = basicInfo.getStateHeiDataGpiPtrRatios();
            if (null != stateHeiDataGpiPtrRatios && !stateHeiDataGpiPtrRatios.isEmpty()) {
                for (StateHeiDataGpiPtrRatio stateHeiDataGpiPtrRatio : stateHeiDataGpiPtrRatios) {
                    saveObject(session, stateHeiDataGpiPtrRatio);
                }
            }

            Set<StateHeiNumberCountEnrollment> stateHeiNumberCountEnrollments = basicInfo.getStateHeiNumberCountEnrollments();
            if (null != stateHeiNumberCountEnrollments && !stateHeiNumberCountEnrollments.isEmpty()) {
                for (StateHeiNumberCountEnrollment StateHeiNumberCountEnrollment : stateHeiNumberCountEnrollments) {
                    saveObject(session, StateHeiNumberCountEnrollment);
                }
            }
            Set<StateNaacAccreditationDetail> stateNaacAccreditationDetails = basicInfo.getStateNaacAccreditationDetails();
            if (null != stateNaacAccreditationDetails && !stateNaacAccreditationDetails.isEmpty()) {
                for (StateNaacAccreditationDetail stateNaacAccreditationDetail : stateNaacAccreditationDetails) {
                    saveObject(session, stateNaacAccreditationDetail);
                }
            }


            Set<StateExpenditureHe> stateExpenditureOnHigherEducations = basicInfo.getStateExpenditureOnHigherEducations();
            if (null != stateExpenditureOnHigherEducations && !stateExpenditureOnHigherEducations.isEmpty()) {
                for (StateExpenditureHe stateExpenditureOnHigherEducation : stateExpenditureOnHigherEducations) {
                    saveObject(session, stateExpenditureOnHigherEducation);
                }
            }
            Set<FocusDistrictIndicatorData> focusDistrictIndicatorData = basicInfo.getFocusDistrictIndicatorData();
            if (null != focusDistrictIndicatorData && !focusDistrictIndicatorData.isEmpty()) {
                for (FocusDistrictIndicatorData stateExpenditureOnHigherEducation : focusDistrictIndicatorData) {
                    saveObject(session, stateExpenditureOnHigherEducation);
                }
            }

            tx.commit();
            return basicInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for Save output of {} due to this exception", ex.getMessage());
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        return null;
    }*/

    void saveObject(Session session, Object object) {
        session.save(object);
    }

    void updateObject(Session session, Object object) {
        session.update(object);
    }

    @Override
    public StateBasicInfoDTO update(StateBasicInfoDTO basicInfo) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        try {
            tx = session2.beginTransaction();
//            @Valid Set<StateProfileDocument> stateProfileDocuments = basicInfo.getStateProfileDocuments();
//            if (null != stateProfileDocuments && !stateProfileDocuments.isEmpty()) {
//                for (StateProfileDocument stateProfileDocument : stateProfileDocuments) {
//                    StateProfileDocument profileDocument = null;
//                    try {
//                        profileDocument = (StateProfileDocument) session.createQuery("from " +
//                                "StateProfileDocument where id =" + stateProfileDocument.getId()).getSingleResult();
//                    } catch (Exception exception) {
//                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
//                    }
//                    if (null == profileDocument) {
//                        saveObject(session1, stateProfileDocument);
//                    } else {
//                        stateProfileDocument.setId(profileDocument.getId());
//                        updateObject(session2, stateProfileDocument);
//                    }
//                }
//            }

            @Valid Set<StatePopulation> statePopulations = basicInfo.getStatePopulations();
            if (null != statePopulations && !statePopulations.isEmpty()) {
                for (StatePopulation statePopulation : statePopulations) {
                    StatePopulation profileDocument = null;
                    try {
                        profileDocument = (StatePopulation) session.createQuery("from  " + "StatePopulation  " +
                                "where stateCode='" + statePopulation.getStateCode() + "' and gender.id='" + statePopulation.getGender().getId() + "'and year ='" + statePopulation.getYear() + "'").getSingleResult();

                    } catch (Exception exception) {
                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
                    }
                    statePopulation.setGenderId(statePopulation.getGender().getId());
                    //  StatePopulation profileDocument = session.get(StatePopulation.class, statePopulation.getId());
                    if (null == profileDocument) {
                        saveObject(session1, statePopulation);
                    } else {
                        statePopulation.setId(profileDocument.getId());
                        updateObject(session2, statePopulation);
                    }
                }
            }

            @Valid Set<StateHeiDataGerEnrollment> stateHeiDataGerEnrollments = basicInfo.getStateHeiDataGerEnrollments();
            if (null != stateHeiDataGerEnrollments && !stateHeiDataGerEnrollments.isEmpty()) {
                for (StateHeiDataGerEnrollment stateHeiDataGerEnrollment : stateHeiDataGerEnrollments) {
                    StateHeiDataGerEnrollment profileDocument = null;
                    try {
                        profileDocument = (StateHeiDataGerEnrollment) session.createQuery("from  " +
                                "StateHeiDataGerEnrollment  where stateCode='" + stateHeiDataGerEnrollment.getStateCode() +
                                "' and indicator.id='" + stateHeiDataGerEnrollment.getIndicator().getId() + "'and " +
                                "year ='" + stateHeiDataGerEnrollment.getYear() + "'").getSingleResult();
                    } catch (Exception exception) {
                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
                    }
                    stateHeiDataGerEnrollment.setIndicatorId(stateHeiDataGerEnrollment.getIndicator().getId());
                    if (null == profileDocument) {
                        saveObject(session1, stateHeiDataGerEnrollment);
                    } else {
                        stateHeiDataGerEnrollment.setId(profileDocument.getId());
                        updateObject(session2, stateHeiDataGerEnrollment);
                    }
                }
            }

            @Valid Set<StateHeiDataGpiPtrRatio> stateHeiDataGpiPtrRatios = basicInfo.getStateHeiDataGpiPtrRatios();
            if (null != stateHeiDataGpiPtrRatios && !stateHeiDataGpiPtrRatios.isEmpty()) {
                for (StateHeiDataGpiPtrRatio stateHeiDataGpiPtrRatio : stateHeiDataGpiPtrRatios) {

                    StateHeiDataGpiPtrRatio profileDocument = null;
                    try {
                        profileDocument = (StateHeiDataGpiPtrRatio) session.createQuery(" from  " +
                                "StateHeiDataGpiPtrRatio  where stateCode='" + stateHeiDataGpiPtrRatio.getStateCode() +
                                "' and indicator.id='" + stateHeiDataGpiPtrRatio.getIndicator().getId() + "'and year" +
                                " " +
                                "='" + stateHeiDataGpiPtrRatio.getYear() + "'").getSingleResult();
                    } catch (Exception exception) {
                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
                    }
                    stateHeiDataGpiPtrRatio.setIndicatorId(stateHeiDataGpiPtrRatio.getIndicator().getId());
                    if (null == profileDocument) {
                        saveObject(session1, stateHeiDataGpiPtrRatio);
                    } else {
                        stateHeiDataGpiPtrRatio.setId(profileDocument.getId());
                        updateObject(session2, stateHeiDataGpiPtrRatio);
                    }
                }
            }

            @Valid Set<StateHeiNumberCountEnrollment> stateHeiNumberCountEnrollments = basicInfo.getStateHeiNumberCountEnrollments();
            if (null != stateHeiNumberCountEnrollments && !stateHeiNumberCountEnrollments.isEmpty()) {
                for (StateHeiNumberCountEnrollment stateHeiNumberCountEnrollment : stateHeiNumberCountEnrollments) {
                    StateHeiNumberCountEnrollment profileDocument = null;
                    try {
                        profileDocument = (StateHeiNumberCountEnrollment) session.createQuery("from " +
                                "StateHeiNumberCountEnrollment where stateCode ='" + stateHeiNumberCountEnrollment.getStateCode() + "'and year ='" + stateHeiNumberCountEnrollment.getYear() + "' and pmushaInstitutionType.id='" + stateHeiNumberCountEnrollment.getPmushaInstitutionType().getId() + "'").getSingleResult();
                    } catch (Exception exception) {
                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
                    }
                    stateHeiNumberCountEnrollment.setPmushaInstitutionTypeId(stateHeiNumberCountEnrollment.getPmushaInstitutionType().getId());
                    if (null == profileDocument) {
                        saveObject(session1, stateHeiNumberCountEnrollment);
                    } else {
                        stateHeiNumberCountEnrollment.setId(profileDocument.getId());
                        updateObject(session2, stateHeiNumberCountEnrollment);
                    }
                }
            }
            @Valid Set<StateNaacAccreditationDetail> stateNaacAccreditationDetails = basicInfo.getStateNaacAccreditationDetails();
            if (null != stateNaacAccreditationDetails && !stateNaacAccreditationDetails.isEmpty()) {
                for (StateNaacAccreditationDetail stateNaacAccreditationDetail : stateNaacAccreditationDetails) {
                    StateNaacAccreditationDetail profileDocument = null;
                    try {
                        profileDocument = (StateNaacAccreditationDetail) session.createQuery("from  " +
                                "StateNaacAccreditationDetail  where stateCode='" + stateNaacAccreditationDetail.getStateCode() +
                                "' and pmushaInstitutionType.id='" + stateNaacAccreditationDetail.getPmushaInstitutionType().getId() + "' and  naacAccreditation.id='" + stateNaacAccreditationDetail.getNaacAccreditation().getId() + "' ").getSingleResult();

                    } catch (Exception exception) {
                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
                    }
                    stateNaacAccreditationDetail.setNaacAccreditationId(stateNaacAccreditationDetail.getNaacAccreditation().getId());
                    stateNaacAccreditationDetail.setPmushaInstitutionTypeId(stateNaacAccreditationDetail.getPmushaInstitutionType().getId());
                    if (null == profileDocument) {
                        saveObject(session1, stateNaacAccreditationDetail);
                    } else {
                        stateNaacAccreditationDetail.setId(profileDocument.getId());
                        updateObject(session2, stateNaacAccreditationDetail);
                    }
                }
            }
            @Valid Set<StateExpenditureHe> stateExpenditureOnHigherEducations = basicInfo.getStateExpenditureOnHigherEducations();
            if (null != stateExpenditureOnHigherEducations && !stateExpenditureOnHigherEducations.isEmpty()) {
                for (StateExpenditureHe stateExpenditureOnHigherEducation : stateExpenditureOnHigherEducations) {
                    StateExpenditureHe profileDocument = null;
                    try {
                        profileDocument = (StateExpenditureHe) session.createQuery("from  " +
                                "StateExpenditureHe  where stateCode='" + stateExpenditureOnHigherEducation.getStateCode() +
                                "' and indicator.id='" + stateExpenditureOnHigherEducation.getIndicator().getId() +
                                "'").getSingleResult();
                    } catch (Exception exception) {
                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
                    }
                    stateExpenditureOnHigherEducation.setIndicatorId(stateExpenditureOnHigherEducation.getIndicator().getId());
                    if (null == profileDocument) {
                        saveObject(session1, stateExpenditureOnHigherEducation);
                    } else {
                        stateExpenditureOnHigherEducation.setId(profileDocument.getId());
                        updateObject(session2, stateExpenditureOnHigherEducation);
                    }
                }
            }
            @Valid Set<FocusDistrictIndicatorData> focusDistrictIndicatorData = basicInfo.getFocusDistrictIndicatorData();
            if (null != focusDistrictIndicatorData && !focusDistrictIndicatorData.isEmpty()) {
                for (FocusDistrictIndicatorData stateExpenditureOnHigherEducation : focusDistrictIndicatorData) {
                    FocusDistrictIndicatorData profileDocument = null;
                    try {
                        profileDocument = (FocusDistrictIndicatorData) session.createQuery("from  " +
                                "FocusDistrictIndicatorData  where stateCode='" + stateExpenditureOnHigherEducation.getStateCode() +
                                "' and focusDistrictIndicator.id='" + stateExpenditureOnHigherEducation.getFocusDistrictIndicator().getId() + "' and districtCode='" + stateExpenditureOnHigherEducation.getDistrictCode() + "'").getSingleResult();
                    } catch (Exception exception) {
                        log.error("StateProfileDocument Entity not Found " + exception.getMessage());
                    }
                    stateExpenditureOnHigherEducation.setFocusDistrictIndicatorId(stateExpenditureOnHigherEducation.getFocusDistrictIndicator().getId());
                    if (null == profileDocument) {
                        saveObject(session1, stateExpenditureOnHigherEducation);
                    } else {
                        stateExpenditureOnHigherEducation.setId(profileDocument.getId());
                        updateObject(session2, stateExpenditureOnHigherEducation);
                    }
                }
            }

            tx.commit();
            return basicInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for update output of {} due to this exception",
                    ex.getMessage());
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            session.close();
            session1.close();
            session2.close();
        }
        return null;
    }


    @Override
    public StateBasicInfo getAll(String stateCode) {
        log.info("StateProfileDaoImpl : get method invoked :");
        try (Session session = sessionFactory.openSession();) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<StateBasicInfo> query = builder.createQuery(StateBasicInfo.class);
            Root<StateBasicInfo> allData = query.from(StateBasicInfo.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (stateCode != null && (!"null".equals(stateCode)) && (!"ALL".equals(stateCode))) {
                predicates.add(builder.equal(allData.get("stateCode"), stateCode));
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            StateBasicInfo university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Generic Query can not be completed for get output of {} due to this exception",
                    e.getMessage());
        }
        return null;
    }


    @Override
    public StateBasicInfoEO get(String stateCode) {
        log.info("StateProfileDaoImpl : get method invoked :");
        try (Session session = sessionFactory.openSession();) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<StateBasicInfoEO> query = builder.createQuery(StateBasicInfoEO.class);
            Root<StateBasicInfoEO> allData = query.from(StateBasicInfoEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (stateCode != null && (!"null".equals(stateCode)) && (!"ALL".equals(stateCode))) {
                predicates.add(builder.equal(allData.get("stateCode"), stateCode));
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            StateBasicInfoEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Generic Query can not be completed for get output of {} due to this exception",
                    e.getMessage());
        }
        return null;
    }


    @Override
    public StateBasicInfoEO save(StateBasicInfoEO basicInfo, HttpServletRequest request) {
        Transaction tx = null;
        Session session2 = sessionFactory.openSession();
        ;
        try {
            tx = session2.beginTransaction();
            basicInfo.setNumberOfDistricts(getTotalDistrict(basicInfo.getStateCode()));
            Integer id = (Integer) session2.save(basicInfo.getStateProfileDocument());
            session2.save(basicInfo);
            userDao.saveUserActionLog(new UserActionLogEO(UserActionConstant.STATE_PROFILE_CREATED, "saa07",
                    DateUtils.obtainCurrentTimeStamp(), IpAddressProvider.getClientIpAddr(request), "State " +
                    "Profile Created"));
            tx.commit();
            return basicInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for update output of {} due to this exception",
                    ex.getMessage());
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            session2.close();
        }
        return null;
    }

    @Override
    public StateBasicInfoEO update(StateBasicInfoEO basicInfo, HttpServletRequest request) {
        Transaction tx = null;
        Session session2 = sessionFactory.openSession();
        ;
        try {
            tx = session2.beginTransaction();
            if (null != basicInfo.getStateProfileDocument().getId() && 0 != basicInfo.getStateProfileDocument().getId())
                session2.update(basicInfo.getStateProfileDocument());
            basicInfo.setNumberOfDistricts(getTotalDistrict(basicInfo.getStateCode()));
            basicInfo.setStateProfileDocumentId(basicInfo.getStateProfileDocument().getId());
            session2.update(basicInfo);
            userDao.saveUserActionLog(new UserActionLogEO(UserActionConstant.STATE_PROFILE_MODIFIED, "saa07",
                    DateUtils.obtainCurrentTimeStamp(), IpAddressProvider.getClientIpAddr(request), "State Profile Modified"));
            tx.commit();
            return basicInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for update output of {} due to this exception",
                    ex.getMessage());
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            session2.close();
        }
        return null;
    }

    @Override
    public BigInteger getTotalDistrict(String stateCode) {
        Session session = sessionFactory.openSession();
        ;
        try {
            Query query = session.createNativeQuery("Select count(*) from ref_district where st_code=:stateCode");
            query.setParameter("stateCode", stateCode);
            return (BigInteger) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for update output of {} due to this exception",
                    ex.getMessage());
        } finally {
            session.close();
        }
        return new BigInteger(String.valueOf(0));
    }


    @Override
    public StateProfileDocument getProfile(String stateCode) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from StateProfileDocument where stateCode=:stateCode");
            query.setParameter("stateCode", stateCode);
            return (StateProfileDocument) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for update output of {} due to this exception",
                    ex.getMessage());
        } finally {
            session.close();
        }
        return null;
    }


}
