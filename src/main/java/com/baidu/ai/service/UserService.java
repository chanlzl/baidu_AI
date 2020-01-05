package com.baidu.ai.service;

import com.baidu.ai.request.AIRequest;
import com.baidu.ai.response.AIResponse;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    AIResponse add(AIRequest aiRequest);

    @Transactional
    AIResponse delete(AIRequest aiRequest);

    AIResponse faceSearch(AIRequest aiRequest);
}
