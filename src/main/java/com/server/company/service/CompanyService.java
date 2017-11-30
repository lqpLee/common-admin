package com.server.company.service;

import com.github.pagehelper.PageInfo;
import com.server.company.entity.Company;

/**
 * Created by liqiangpeng on 2017/11/29.
 */
public interface CompanyService {

    int insert(Company company);

    PageInfo<Company> listForPage(Integer pageNum, Integer pageSize, Company company);
}
