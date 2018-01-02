package com.server.company.service.impl;


import com.common.system.entity.RcRole;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
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

    @Override
    public PageInfo<Company> listForPage(Integer pageNum, Integer pageSize, Company company) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<Company> companyList = companyMapper.selectList(company);//自定义查询方法
        return new PageInfo<>(companyList);
    }

    @Override
    public Result<Company> selectByPrimaryKey(String id) {
        Result<Company> result = new Result<>();
        Company company = companyMapper.selectByPrimaryKey(id);
        if (company == null) {
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("没有该公司");
            return result;
        }
        result.setData(company);
        result.setStatus(true);
        result.setCode(MsgCode.SUCCESS);
        return result;
    }
}
