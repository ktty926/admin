package com.jk.service.fxlService;

import com.jk.model.fxlModel.Company;

import java.util.HashMap;

public interface workService {
    HashMap<String, Object> findCompany(Company company, Integer page, Integer limit);

    void cancelCooperation(Integer cooperation);
}
