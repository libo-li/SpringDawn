package com.reedl.common.sys.service.impl;

import com.reedl.common.sys.entity.SysResource;
import com.reedl.common.sys.mapper.SysResourceMapper;
import com.reedl.common.sys.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Li Libo on 2016/12/22.
 */
@Service
public class SysResourceServiceImpl implements ISysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> getResourceList() {
        return sysResourceMapper.selectResourceList();
    }
}
