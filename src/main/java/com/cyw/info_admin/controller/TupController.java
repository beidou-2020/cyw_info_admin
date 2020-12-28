package com.cyw.info_admin.controller;

import com.cyw.info_admin.controller.common.Result;
import com.cyw.info_admin.entity.Tup;
import com.cyw.info_admin.entity.common.PageParam;
import com.cyw.info_admin.entity.query.TupPageQuery;
import com.cyw.info_admin.enumerate.ResultCode;
import com.cyw.info_admin.service.TupService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/tup")
@Slf4j
public class TupController {

    @Resource
    private TupService tupService;

    /**
     * 待审核列表
     * @param pageParam
     * @param query
     * @return
     */
    @GetMapping("/pendingList")
    public ModelAndView pendingList(@Valid PageParam pageParam, TupPageQuery query){
        PageInfo<Tup> pageInfo = tupService.pageList(pageParam, query);
        return new ModelAndView("tup/pending_list").
                addObject("query", query).
                addObject("list", pageInfo.getList()).
                addObject("total", pageInfo.getTotal()).
                addObject("totalPage", pageInfo.getPages()).
                addObject("currentPageNumber", pageParam.getCurrentPageNumber());
    }

    /**
     * 审核通过列表
     * @param pageParam
     * @param query
     * @return
     */
    @GetMapping("/auditedList")
    public ModelAndView auditedList(@Valid PageParam pageParam, TupPageQuery query){
        PageInfo<Tup> pageInfo = tupService.pageList(pageParam, query);
        return new ModelAndView("tup/audited_list").
                addObject("query", query).
                addObject("list", pageInfo.getList()).
                addObject("total", pageInfo.getTotal()).
                addObject("totalPage", pageInfo.getPages()).
                addObject("currentPageNumber", pageParam.getCurrentPageNumber());
    }

    /**
     * 驳回列表
     * @param pageParam
     * @param query
     * @return
     */
    @GetMapping("/dowmList")
    public ModelAndView dowmList(@Valid PageParam pageParam, TupPageQuery query){
        PageInfo<Tup> pageInfo = tupService.pageList(pageParam, query);
        return new ModelAndView("tup/down_list").
                addObject("query", query).
                addObject("list", pageInfo.getList()).
                addObject("total", pageInfo.getTotal()).
                addObject("totalPage", pageInfo.getPages()).
                addObject("currentPageNumber", pageParam.getCurrentPageNumber());
    }

    /**
     * 审核/驳回提交数据
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/updateStatusById")
    @ResponseBody
    public Result updateStatusById(@RequestParam("id") Long id, @RequestParam("status") Integer status){
        Integer result = tupService.updateStatusById(id, status);
        if (1 == result.intValue()){
            return Result.ok(result);
        }
        return Result.fail(ResultCode.SERVER_ERROR.code(), ResultCode.SERVER_ERROR.msg());
    }

    /**
     * 批量审核/驳回提交数据
     * @param ids
     * @param status
     * @return
     */
    @PostMapping("/batchUpdateStatusById")
    @ResponseBody
    public Result batchUpdateStatusById(@RequestParam("ids") String ids, @RequestParam("status") Integer status){
        if (StringUtils.isEmpty(ids)){
            return Result.fail(ResultCode.REQUEST_PARAM.code(), ResultCode.REQUEST_PARAM.msg());
        }
        Integer result = tupService.batchUpdateStatusById(ids, status);
        if (0 == result.intValue()){
            return Result.fail(ResultCode.SERVER_ERROR.code(), ResultCode.SERVER_ERROR.msg());
        }
        return Result.ok(result);
    }
}
