package com.yqx.service.impl;

import com.yqx.dao.SysLogDao;
import com.yqx.pojo.SysLog;
import com.yqx.service.SysLogService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * Day05 查询所有日志信息
     * @return
     * @Auto 于清晰
     */
    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    /**
     * Day05 日志记录添加功能
     * @param sysLog
     * @Auto 于清晰
     */
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save( sysLog );
    }
}
