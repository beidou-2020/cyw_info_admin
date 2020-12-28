package com.cyw.info_admin.controller;

import com.cyw.info_admin.controller.common.Result;
import com.cyw.info_admin.entity.User;
import com.cyw.info_admin.entity.common.PageParam;
import com.cyw.info_admin.entity.dto.AddUserDTO;
import com.cyw.info_admin.entity.dto.EditUserDTO;
import com.cyw.info_admin.entity.query.UserPageQuery;
import com.cyw.info_admin.enumerate.ResultCode;
import com.cyw.info_admin.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 复合提交分页获取用户列表
     * @param pageParam
     * @param query
     * @return
     */
    @GetMapping("/list")
    public ModelAndView pageList(@Valid PageParam pageParam, UserPageQuery query){
        PageInfo<User> pageInfo = userService.pageList(pageParam, query);
        return new ModelAndView("/user/list").
                addObject("list", pageInfo.getList()).
                addObject("total", pageInfo.getTotal()).
                addObject("query", query).
                addObject("totalPage", pageInfo.getPages()).
                addObject("currentPageNumber", pageParam.getCurrentPageNumber());
    }

    /**
     * 跳转至添加用户页面
     * @return
     */
    @GetMapping("/toAddView")
    public ModelAndView toAddView(){
        return new ModelAndView("/user/add");
    }

    /**
     * 保存用户信息
     * @param addUserDTO
     * @return
     */
    @PostMapping("/saveUserInfo")
    @ResponseBody
    public Result saveUserInfo(@RequestBody @Valid AddUserDTO addUserDTO){
        Integer result = userService.saveUser(addUserDTO);
        if (1 == result.intValue()){
            return Result.ok(result);
        }
        return Result.fail(ResultCode.SERVER_ERROR.code(), ResultCode.SERVER_ERROR.msg());
    }

    /**
     * 跳转至编辑页面
     * @return
     */
    @GetMapping("/toEditView/{id}")
    public ModelAndView toEditView(@PathVariable("id") Long id){
        User userInfo = userService.selectByPrimaryKey(id);
        return new ModelAndView("/user/edit").
                addObject("userInfo", userInfo);
    }

    /**
     * 编辑用户信息
     * @param editUserDTO
     * @return
     */
    @PostMapping("/editUserInfo")
    @ResponseBody
    public Result editUserInfo(@RequestBody @Valid EditUserDTO editUserDTO){
        Integer result = userService.updateUser(editUserDTO);
        if (1 == result){
            return Result.ok(result);
        }
        return Result.fail(ResultCode.SERVER_ERROR.code(), ResultCode.SERVER_ERROR.msg());
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result deleteUser(@PathVariable("id") Long id){
        return Result.ok(userService.deleteUserInfo(id));
    }

    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    @PostMapping("/batchDelete/{ids}")
    @ResponseBody
    public Result batchDeleteUser(@PathVariable("ids") String ids){
        return Result.ok(userService.batchDeleteUser(ids));
    }
}
