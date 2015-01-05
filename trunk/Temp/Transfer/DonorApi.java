package com.posiba.button.api.v1.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.posiba.button.api.v1.ApiConstants;
import com.posiba.button.api.v1.base.BaseApi;
import com.posiba.button.api.v1.base.ErrorInfo;
import com.posiba.button.dto.DonationDto;
import com.posiba.button.dto.DonorProfileDto;
import com.posiba.button.dto.UserDonateHistoryDto;
import com.posiba.button.model.ChartZoomType;
import com.posiba.button.model.Response;
import com.posiba.button.model.UserDonateHistory;
import com.posiba.button.model.base.DonorProfile;
import com.posiba.button.model.base.IndividualDonor;
import com.posiba.button.service.DonorProfileService;
import com.posiba.button.service.DonorSocialNetworkMapService;
import com.posiba.button.util.DateUtil;

@RestController
@RequestMapping(BaseApi.DONOR_PREFIX)
public class DonorApi extends BaseApi {

    @Inject
    DonorProfileService donorProfileService;

    @Inject
    DonorSocialNetworkMapService donorSocialService;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "profile/{id}", method = RequestMethod.GET)
    public ResponseEntity getDonorProfile(@PathVariable String id, HttpServletRequest request) {
        ResponseEntity response = null;
        DonorProfile profile = null;
        try {
            response = validateDonorId(id);
            if (response != null) {
                return response;
            }

            profile = donorProfileService.getByUserId(new BigInteger(id));
            if (isNullOrEmpty(profile)) {
                ErrorInfo error = new ErrorInfo(ApiConstants.ITEM_NOT_FOUND, String.format(ApiConstants.ITEM_NOT_FOUND_MSG, "User(ID=" + id + ")"));
                return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
            } else {
                DonorProfileDto dto = mapper.map(profile, DonorProfileDto.class);
                dto.setSavedProfile(true);
                DateTime birthday = profile.getBirthdate();
                if (birthday != null) {
                    dto.setBirthdayAsLong(birthday.getMillis());
                }

                IndividualDonor individualDonor = profile.getIndividualDonor();
                if (individualDonor != null) {
                    dto.setEmail(individualDonor.getEmail());
                    String socialImageUrl = donorSocialService.getSocialImage(individualDonor.getId());

                    if (!DonorApi.isFullURL(socialImageUrl)) {
                        socialImageUrl = baseUrl + socialImageUrl;
                    }
                    dto.setProfilePictureUrl(socialImageUrl);

                    if (isNullOrEmpty(profile.getFirstName())) {
                        dto.setFirstName(individualDonor.getFirstName());
                    }
                    if (isNullOrEmpty(profile.getLastName())) {
                        dto.setLastName(individualDonor.getLastName());
                    }
                }

                DonorProfileDto.handlleNullProperties(dto);
                return new ResponseEntity(dto, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    private static boolean isFullURL(String socialImageUrl) {
        if (isNullOrEmpty(socialImageUrl)) {
            return false;
        }

        socialImageUrl = socialImageUrl.toUpperCase();
        return (socialImageUrl.startsWith("HTTP:") || socialImageUrl.startsWith("HTTPS:"));
    }

    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value = "profile/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateDonorProfile(@PathVariable String id, @RequestBody DonorProfileDto dto) {
        ResponseEntity response = null;
        DonorProfile profile = null;
        try {
            response = validateDonorId(id);
            if (response != null) {
                return response;
            } else {
                profile = donorProfileService.getByUserId(new BigInteger(id));
                BeanUtils.copyProperties(profile, dto);
                donorProfileService.save(profile);

                Map<String, BigInteger> m = new java.util.HashMap<String, BigInteger>();
                m.put("id", new BigInteger(id));
                response = new ResponseEntity<Map>(m, HttpStatus.OK);
                return response;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "{donorId}/portfolios", method = RequestMethod.GET)
    public ResponseEntity getPortfolios(@PathVariable String donorId, String month) {
        if (isNullOrEmpty(month)) {
            month = "-1";
        }
        ResponseEntity response = null;
        ChartZoomType chartZoomType = null;
        try {
            switch (month) {
            case "3":
                chartZoomType = ChartZoomType.THREE_MONTH;
                break;
            case "6":
                chartZoomType = ChartZoomType.SIX_MONTH;
                break;
            case "12":
                chartZoomType = ChartZoomType.ONE_YEAR;
                break;
            case "-1":
                chartZoomType = ChartZoomType.ALL;
                break;
            default:
                chartZoomType = ChartZoomType.THREE_MONTH;
                break;
            }

            List<UserDonateHistory> donateHistory = donorProfileService.getDonateHistory(new BigInteger(donorId), chartZoomType);
            double totalMoney = 0; // USD
            double totalTime = 0; // hour

            Date startDate = null;
            Date endDate = null;
            if (chartZoomType != ChartZoomType.ALL) {
                startDate = DateUtil.getStartDateOfPreviousMonths(chartZoomType.getValue()).toDate();
                endDate = new DateTime().toDate();
            }

            List<UserDonateHistoryDto> dtoList = new ArrayList<UserDonateHistoryDto>();
            if (!isNullOrEmpty(donateHistory)) {
                for (UserDonateHistory userDonateHistory : donateHistory) {
                    if (userDonateHistory == null) {
                        continue;
                    }

                    Double money = userDonateHistory.getMoney();
                    if (money != null) {
                        totalMoney += money;
                    }

                    Double time = userDonateHistory.getTime();
                    if (time != null) {
                        totalTime += time;
                    }

                    // Only collect Donations on specific Dates
                    Date donationOn = userDonateHistory.getMadeDonateOn();
                    if (donationOn == null) {
                        continue;
                    }

                    if (startDate != null && donationOn.before(startDate)) {
                        continue;
                    }
                    if (endDate != null && donationOn.after(endDate)) {
                        continue;
                    }

                    dtoList.add(mapper.map(userDonateHistory, UserDonateHistoryDto.class));
                }
            }

            Map m = new java.util.HashMap();
            m.put("totalMoney", totalMoney);
            m.put("totalTime", totalTime);
            m.put("itemList", dtoList);

            response = new ResponseEntity(m, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "{donorId}/donations", method = RequestMethod.GET)
    public ResponseEntity getDonations(@PathVariable String donorId) {
        ResponseEntity response = null;
        try {
            response = validateDonorId(donorId);
            if (response != null) {
                return response;
            } else {
                List<UserDonateHistory> donateHistory = donorProfileService.getDonateEntityHistory(new BigInteger(donorId), null);
                List<UserDonateHistoryDto> dtoList = new ArrayList<UserDonateHistoryDto>();
                if (!isNullOrEmpty(donateHistory)) {
                    for (UserDonateHistory userDonateHistory : donateHistory) {
                        if (userDonateHistory == null) {
                            continue;
                        }

                        dtoList.add(mapper.map(userDonateHistory, UserDonateHistoryDto.class));
                    }
                }

                response = new ResponseEntity(dtoList, HttpStatus.OK);
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    /**
     * Add New Donation into a new Organization (Entity)
     * 
     * @param donorId
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "{donorId}/organizations/{orgName}/donations", method = RequestMethod.POST)
    public ResponseEntity addDonationToNewOrg(@PathVariable String donorId, @PathVariable String orgName, @RequestBody DonationDto donationDto) {
        String oldOrgName = "";
        try {
            DonorProfile profile = donorProfileService.getByUserId(new BigInteger(donorId));
            String profileName = profile.getProfileName();

            return saveDonation(donationDto, profileName, donorId, oldOrgName, orgName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    /**
     * Add New Donation into an existing Organization (Entity)
     * 
     * @param donorId
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "{donorId}/organizations/{orgName}/{newOrgName}/donations", method = RequestMethod.POST)
    public ResponseEntity addDonationToExistingOrg(@PathVariable String donorId, @PathVariable String orgName, @PathVariable String newOrgName,
            @RequestBody DonationDto donationDto) {
        ResponseEntity response = null;

        try {
            response = validateDonorId(donorId);
            if (response != null) {
                return response;
            } else {
                DonorProfile profile = donorProfileService.getByUserId(new BigInteger(donorId));
                String profileName = profile.getProfileName();
                if (!donorProfileService.isExistedEntityName(profileName, "", orgName)) {
                    ErrorInfo error = new ErrorInfo(ApiConstants.CLIENT_ERROR, "Organization (name=" + orgName + ") doesnot exist");
                    response = new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
                    return response;
                }
                return saveDonation(donationDto, profileName, donorId, orgName, newOrgName);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private ResponseEntity saveDonation(DonationDto donationDto, String profileName, String donorId, String orgName, String newOrgName) {
        ResponseEntity response = null;
        BigInteger orgId = new BigInteger("0");
        try {

            Response saveResult = donorProfileService.saveEntityName(profileName, orgId, orgName, newOrgName);
            if (saveResult != null && Boolean.FALSE.equals(saveResult.getSuccess())) {
                ErrorInfo error = new ErrorInfo(ApiConstants.CLIENT_ERROR, saveResult.getMessage());
                response = new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
                return response;
            }

            BigDecimal money = new BigDecimal(donationDto.getAmountMoney());
            BigDecimal time = new BigDecimal(donationDto.getAmountTime());
            UserDonateHistory result = donorProfileService.saveManualDonateEntity(BigInteger.ZERO, orgId, newOrgName, profileName,
                    donationDto.getDonationMadeOn(), money, time);

            Map m = new java.util.HashMap();
            m.put("id", result.getDonateId());
            response = new ResponseEntity<Map>(m, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "{donorId}/organizations/{organizationName}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrganization(@PathVariable("donorId") String donorId, @PathVariable("organizationName") String orgName) {
        ResponseEntity response = null;
        try {
            response = validateDonorId(donorId);
            if (response != null) {
                return response;
            } else {
                DonorProfile profile = donorProfileService.getByUserId(new BigInteger(donorId));
                String profileName = profile.getProfileName();
                if (!donorProfileService.isExistedEntityName(profileName, "", orgName)) {
                    ErrorInfo error = new ErrorInfo(ApiConstants.CLIENT_ERROR, "Organization (name=" + orgName + ") doesnot exist");
                    response = new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
                    return response;
                }

                donorProfileService.delEntityManual(orgName);
                Map m = new java.util.HashMap();
                m.put("orgName", orgName);
                response = new ResponseEntity<Map>(m, HttpStatus.OK);
                return response;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "{donorId}/donations/{donationId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteDonation(@PathVariable("donationId") String id) {
        ResponseEntity response = null;
        try {
            response = validateDonorId(id);
            if (response != null) {
                return response;
            } else {
                donorProfileService.delManualEntityDonate(new BigInteger(id));
                Map m = new java.util.HashMap();
                m.put("id", id);
                response = new ResponseEntity<Map>(m, HttpStatus.OK);
                return response;
            }
        } catch (NullPointerException e) {
            ErrorInfo error = new ErrorInfo(ApiConstants.CLIENT_ERROR, "Donation (id=" + id + ") doesnot exist");
            response = new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    // Template method
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "templateMethod/{id}", method = RequestMethod.GET)
    public ResponseEntity templateMethod(@PathVariable String id) {
        ResponseEntity response = null;
        try {
            response = validateDonorId(id);
            if (response != null) {
                return response;
            } else {
                Map m = new java.util.HashMap();
                m.put("id", id);
                response = new ResponseEntity<Map>(m, HttpStatus.OK);
                return response;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return internalError(e.getMessage());
        }
    }

    private static ResponseEntity<ErrorInfo> validateDonorId(String id) {
        ResponseEntity<ErrorInfo> response = null;
        boolean invalidUserId = false;
        try {
            BigInteger userId = new BigInteger(id);
            if (userId.intValue() <= 0) {
                invalidUserId = true;
            }
        } catch (NumberFormatException e) {
            invalidUserId = true;
        }
        if (invalidUserId) {
            ErrorInfo error = new ErrorInfo(ApiConstants.CLIENT_ERROR, "User Id must be a positive integer");
            response = new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
