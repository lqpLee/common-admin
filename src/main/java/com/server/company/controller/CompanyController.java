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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by liqiangpeng on 2017/11/29.
 */
@Controller
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/company/list");
        return modelAndView;
    }

    /**
     * 分页查询
     *
     * @param start
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<Company> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
                                          @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                          Company company) {
        PageInfo<Company> pageInfo = companyService.listForPage((start / pageSize) + 1, pageSize, company);
        return new PageBean<Company>(pageInfo);
    }

    /**
     * 跳转到添加页面
     *
     * @param modelAndView
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
     * @param company
     * @param session
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
        Result<Integer> result = new Result<>();
        try {
            int i = companyService.insert(company);
            if (i > 0) {
                result.setStatus(true);
                result.setMsg("OK");
                result.setCode(MsgCode.SUCCESS);
            } else {
                result.setMsg("保存失败");
            }
        } catch (Exception e) {
            result.setMsg("保存失败");
        }

        return result;
    }
}
