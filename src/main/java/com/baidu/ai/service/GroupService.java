package com.baidu.ai.service;

import com.baidu.ai.response.AIResponse;
import org.springframework.transaction.annotation.Transactional;

public interface GroupService {

    /**
     * 创建用户组
     */
    public AIResponse groupAdd(String groupId);

    /**
     * 删除用户组
     */
    @Transactional
    public   AIResponse groupDelete(String groupId);
}
