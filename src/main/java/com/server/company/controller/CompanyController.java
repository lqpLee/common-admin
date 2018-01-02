package com.server.company.controller;

import com.common.system.shiro.ShiroUser;
import com.common.system.util.PageBean;
import com.github.pagehelper.PageInfo;
import com.server.company.entity.Company;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.server.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 公司
 * <p>
 * Created by liqiangpeng on 2017/11/29.
 */
@Controller
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 跳转页
     *
     * @param modelAndView 模型视图
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/company/list");
        return modelAndView;
    }

    /**
     * 分页查询
     *
     * @param start    开始页
     * @param pageSize 数据条数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<Company> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
                                          @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                          Company company) {
        PageInfo<Company> pageInfo = companyService.listForPage((start / pageSize) + 1, pageSize, company);
        return new PageBean<>(pageInfo);
    }

    /**
     * 跳转到添加页面
     *
     * @param modelAndView 模型视图
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/company/add");
        return modelAndView;
    }

    /**
     * 保存数据
     *
     * @param company 模型
     * @param session 当前登陆
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Company company, HttpSession session) {
        ShiroUser user = (ShiroUser) session.getAttribute("user");//获取当前shiroUser。包含角色菜单权限
        String uuid = UUID.randomUUID().toString();
        company.setId(uuid);
        company.setCreateTime(new Date());
        company.setCreateUser("" + user.getId());
        company.setIsDelete(0);
        Result result = companyService.insert(company);
        return result;
    }

    /**
     * 查看
     *
     * @param id 主键
     * @param mv 模型视图
     * @return
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id, ModelAndView mv) {
        Result<Company> result = companyService.selectByPrimaryKey(id);
        mv.addObject("bean", result.getData());
        mv.setViewName("/system/company/view");
        return mv;
    }
}
