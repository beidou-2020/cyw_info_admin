package com.cyw.info_admin.service.impl;

import com.cyw.info_admin.dao.UserMapper;
import com.cyw.info_admin.entity.User;
import com.cyw.info_admin.entity.common.PageParam;
import com.cyw.info_admin.entity.dto.AddUserDTO;
import com.cyw.info_admin.entity.dto.EditUserDTO;
import com.cyw.info_admin.entity.query.UserPageQuery;
import com.cyw.info_admin.service.UserService;
import com.cyw.info_admin.utils.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByName(String username) {
        if (StringUtils.isEmpty(username)){
            return null;
        }

        User userInfo = userMapper.findByName(username);
        if (Objects.isNull(userInfo)){
            return null;
        }
        return userInfo;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public PageInfo<User> pageList(PageParam pageParam, UserPageQuery query) {
        PageHelper.startPage(pageParam.getCurrentPageNumber(), pageParam.getPageSize());
        List<User> list = userMapper.pageList(query);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer saveUser(AddUserDTO addUserDTO) {
        User user = new User();
        BeanUtil.copyProperties(addUserDTO, user);
        int result = userMapper.insertSelective(user);
        if (1 == result){
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer updateUser(EditUserDTO editUserDTO){
        User user = new User();
        BeanUtil.copyProperties(editUserDTO, user);
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (1 == result){
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer deleteUserInfo(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer batchDeleteUser(String ids) {
        List<Long> idList = Arrays.stream(ids.split(",")).
                map(id -> Long.parseLong(id)).collect(Collectors.toList());
        return userMapper.batchDelete(idList);
    }
}
