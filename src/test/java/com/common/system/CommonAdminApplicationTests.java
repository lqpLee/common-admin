package com.common.system;

import com.common.system.entity.TreeGridNode;
import com.common.system.entity.TreeGridWrapper;
import com.common.system.service.RcBaseAreaService;
import com.common.system.service.TreeGridService;
import com.common.system.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.server.company.entity.Company;
import com.server.company.mapper.CompanyMapper;
import com.server.company.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonAdminApplicationTests {

    Logger LOG = LoggerFactory.getLogger("CommonAdminApplicationTests");

    @Autowired
    private RcBaseAreaService baseAreaService;
    @Autowired
    private TreeGridService treeGridService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @Test
    public void contextLoads() {
        List<TreeGridNode> list = treeGridService.getMenuTreeGridNodes();
        TreeGridWrapper wrapper = new TreeGridWrapper();
        wrapper.setRows(list);
        wrapper.setTotal(list.size());
        LOG.info(wrapper.toString());
    }

    @Test
    public void testInsertCompany() {
        Company company = new Company();
        company.setId("123");
        company.setName("测试公司");
        company.setSimpleName("公司");
        company.setCode("code");
        company.setCreateTime(new Date());
        company.setCreateUser("123");
        companyMapper.insert(company);
    }

    @Test
    public void testSelectCompany() {
        Company company = companyMapper.selectByPrimaryKey("123");
        System.out.println(company);
    }

    @Test
    public void testSelectList(){
        Company company = new Company();
        company.setIsDelete(0);
        company.setName("测试");
        List<Company> companyList = companyMapper.selectList(company);
        System.out.println(companyList);
    }

    @Test
    public void testCompanyPageList(){
        Company company = new Company();
        company.setIsDelete(0);
        company.setName("测试");
        PageInfo<Company> page = companyService.listForPage(1, 10, company);
        System.out.println(page);
        List<Company> companyList = page.getList();
        System.out.println(companyList);
        for(Company company1:companyList){
            System.out.println(company1);
        }
    }

    @Test
    public void testSelectCompanyByPrimaryKey(){
        Result<Company> companyResult =
                companyService.selectByPrimaryKey("93cdf19a-c0e4-4e7e-9afe-0342c5d09de4");
        System.out.println(companyResult);
        System.out.println(companyResult.getData());
        System.out.println(companyResult.getCode());
        System.out.println(companyResult.getMsg());
    }

}
