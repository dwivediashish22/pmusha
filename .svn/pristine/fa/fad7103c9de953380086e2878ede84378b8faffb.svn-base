package org.nic.pmusha.service;

import org.nic.pmusha.responseDto.StateBasicInfoDTO;
import org.nic.pmusha.usereo.StateBasicInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface StateProfileService {
    Object save(StateBasicInfoDTO basicInfo, MultipartFile file, HttpServletRequest request);
    StateBasicInfo get(String stateCode);
}
