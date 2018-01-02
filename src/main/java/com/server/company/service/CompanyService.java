package com.server.company.service;

import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.server.company.entity.Company;

/**
 * Created by liqiangpeng on 2017/11/29.
 */
public interface CompanyService {
    /**
     * 保存数据
     *
     * @param company 实体类
     * @return result结果，包含数据
     */
    Result<Integer> insert(Company company);

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param company
     * @return
     */
    PageInfo<Company> listForPage(Integer pageNum, Integer pageSize, Company company);

    /**
     * 根据主键查询数据
     *
     * @param id
     * @return
     */
    Result<Company> selectByPrimaryKey(String id);
}
