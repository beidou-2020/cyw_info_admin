package com.cyw.info_admin.service.impl;

import com.cyw.info_admin.dao.TupMapper;
import com.cyw.info_admin.entity.Tup;
import com.cyw.info_admin.entity.common.PageParam;
import com.cyw.info_admin.entity.query.TupPageQuery;
import com.cyw.info_admin.service.TupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TupServiceImpl implements TupService {

    @Resource
    private TupMapper tupMapper;

    @Override
    public Tup selectByPrimaryKey(Long id) {
        return tupMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Tup> pageList(PageParam pageParam, TupPageQuery query) {
        PageHelper.startPage(pageParam.getCurrentPageNumber(), pageParam.getPageSize());
        List<Tup> list = tupMapper.pageListByQuery(query);
        PageInfo<Tup> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer updateStatusById(Long id, Integer status) {
        Integer result = tupMapper.updateStatusById(id, status);
        if (1 == result.intValue()){
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer batchUpdateStatusById(String ids, Integer status) {
        List<Long> idList = Arrays.stream(ids.split(",")).
                map(id -> Long.parseLong(id)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(idList)){
            return 0;
        }

        Integer result = tupMapper.batchUpdateStatusById(idList, status);
        if (0 == result.intValue()){
            return 0;
        }
        return result;
    }
}
