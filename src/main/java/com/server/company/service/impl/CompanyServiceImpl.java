package com.server.company.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.company.entity.Company;
import com.server.company.mapper.CompanyMapper;
import com.server.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by liqiangpeng on 2017/11/29.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int insert(Company company) {
        return companyMapper.insert(company);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param company
     * @return
     */
    @Override
    public PageInfo<Company> listForPage(Integer pageNum, Integer pageSize, Company company) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<Company> companyList = companyMapper.selectList(company);//自定义查询方法
        return new PageInfo<>(companyList);
    }
}
