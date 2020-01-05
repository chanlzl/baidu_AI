package com.baidu.ai.controller;

import com.baidu.ai.consts.PathConst;
import com.baidu.ai.enums.StatusEnum;
import com.baidu.ai.request.AIRequest;
import com.baidu.ai.response.AIResponse;
import com.baidu.ai.service.GroupService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConst.GROUP_PATH)
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;
    /**
     * 添加用户组
     * @param aiRequest
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AIResponse addGroup(@RequestBody AIRequest aiRequest){
        logger.info("begin to addGroup");
        if (null == aiRequest || StringUtils.isEmpty(aiRequest.getGroupId())){
            return new AIResponse(StatusEnum.INPUT_PARAMETER_INVALID);
        }
        return groupService.groupAdd(aiRequest.getGroupId());
    }

    /**
     * 删除用户组
     * @param aiRequest
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public AIResponse deleteGroup(@RequestBody AIRequest aiRequest){
        logger.info("begin to deleteGroup");
        if (null == aiRequest || StringUtils.isEmpty(aiRequest.getGroupId())){
            return new AIResponse(StatusEnum.INPUT_PARAMETER_INVALID);
        }
        return groupService.groupDelete(aiRequest.getGroupId());
    }


}
