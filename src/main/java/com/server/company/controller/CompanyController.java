package com.server.company.controller;

import com.server.company.entity.Company;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liqiangpeng on 2017/11/29.
 */
@Controller
@RequestMapping("company")
public class CompanyController {

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/company/list");
        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/company/add");
        return modelAndView;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Company company) {
        System.out.println("========="+company);
        Result<Integer> result = new Result<>();
        result.setStatus(true);
        result.setMsg("OK");
        result.setCode(MsgCode.SUCCESS);
        return result;
    }
}
