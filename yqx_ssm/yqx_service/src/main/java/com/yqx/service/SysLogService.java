package com.yqx.service;

import com.yqx.pojo.SysLog;

import java.util.List;

public interface SysLogService {

    /**
     * Day05 查询所有日志信息
     * @return
     * @Auto 于清晰
     */
    List<SysLog> findAll();

    /**
     * Day05 日志记录添加功能
     * @param sysLog
     * @Auto 于清晰
     */
    void save(SysLog sysLog);
}
