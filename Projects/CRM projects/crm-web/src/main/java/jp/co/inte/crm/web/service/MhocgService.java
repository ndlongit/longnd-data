package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.MhocgDto;

public interface MhocgService {

    List<MhocgDto> getMhocgList(String hoid);
}