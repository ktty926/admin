package com.jk.service;

import com.jk.bean.PhoneCount;

import java.util.List;

public interface PoiService {
    List<PhoneCount> findUserListByIds(String ids);

    void savePoi(PhoneCount phoneCount);
}
