package jp.co.inte.crm.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.dto.MhocgDto;
import jp.co.inte.crm.web.service.MhocgService;

public class MhocgServiceImpl extends CrmBaseService implements MhocgService {

    // TODO スタブ
    public List<MhocgDto> getMhocgList(String hoid) {
        List<MhocgDto> resultList = new ArrayList<MhocgDto>();
        MhocgDto mhocdDto1 = new MhocgDto();
        mhocdDto1.hocgid = "HO0000000001";
        mhocdDto1.hocgnm = "てすと法人担当者１";
        mhocdDto1.hocgkana = "テストホウジンタントウシャイチ";
        mhocdDto1.adr01 = "なは";
        resultList.add(mhocdDto1);
        MhocgDto mhocdDto2 = new MhocgDto();
        mhocdDto2.hocgid = "HO0000000002";
        mhocdDto2.hocgnm = "てすと法人担当者２";
        mhocdDto2.hocgkana = "テストホウジンタントウシャニ";
        mhocdDto2.adr01 = "泉崎";
        mhocdDto2.adr02 = "カフーナ";
        resultList.add(mhocdDto2);

        return resultList;
    }
}
