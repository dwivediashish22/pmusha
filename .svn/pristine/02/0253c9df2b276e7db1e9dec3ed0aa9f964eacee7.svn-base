package org.nic.pmusha.service;

import org.nic.pmusha.enums.UserActionConstant;
import org.nic.pmusha.handler.BadRequestException;
import org.nic.pmusha.responseDto.StateBasicInfoDTO;
import org.nic.pmusha.userdao.StateProfileDao;
import org.nic.pmusha.userdao.UserDao;
import org.nic.pmusha.usereo.RefDocumentEO;
import org.nic.pmusha.usereo.StateBasicInfo;
import org.nic.pmusha.usereo.StateBasicInfoEO;
import org.nic.pmusha.usereo.StateProfileDocument;
import org.nic.pmusha.usereo.UserActionLogEO;
import org.nic.pmusha.utility.DateUtils;
import org.nic.pmusha.utility.IpAddressProvider;
import org.nic.pmusha.utility.PdfOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class StateProfileServiceImpl implements StateProfileService {
    @Autowired
    private StateProfileDao stateProfileDao;
    @Autowired
    private PdfOperation pdfOperation;



    @Override
    public Object save(StateBasicInfoDTO basicInfo, MultipartFile file, HttpServletRequest request) {
        StateBasicInfoEO basicInfoEO = basicInfo.getStateBasicInfo();
        if (null != basicInfoEO) {
            StateBasicInfoEO stateBasicInfo = stateProfileDao.get(basicInfoEO.getStateCode());
            if (null != stateBasicInfo) {
                basicInfoEO.setId(stateBasicInfo.getId());
                StateProfileDocument stateProfileDocument = stateProfileDao.getProfile(basicInfoEO.getStateCode());
                if(null !=stateProfileDocument)
                return stateProfileDao.update(getBytesFromMultipartFile(basicInfoEO, file,
                        stateProfileDocument.getId()),request);
            } else {
                return stateProfileDao.save(getBytesFromMultipartFile(basicInfoEO, file, null),request);
            }
        }
        return stateProfileDao.update(basicInfo);
    }

    @Override
    public StateBasicInfo get(String stateCode) {
        return stateProfileDao.getAll(stateCode);
    }


    StateBasicInfoEO getBytesFromMultipartFile(StateBasicInfoEO basicInfo, MultipartFile file, Integer documentId) {
        StateProfileDocument stateProfileDocument = new StateProfileDocument();
        if (null != file) {
            if (null != stateProfileDocument) {
                byte[] bytes = new byte[0];
                try {
                    bytes = file.getBytes();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                if (!pdfOperation.isPdf(bytes))
                    throw new BadRequestException("Incorrect file type, PDF required.");
                stateProfileDocument.setDocumentFile(bytes);
            }

        }

        stateProfileDocument.setStateCode(basicInfo.getStateCode());
        stateProfileDocument.setDocumentType( new RefDocumentEO(1,"Constitution and composition of the SHEC"));
        stateProfileDocument.setId(documentId);
        basicInfo.setStateProfileDocument(stateProfileDocument);

        return basicInfo;
    }
}
