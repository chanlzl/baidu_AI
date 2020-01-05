package com.baidu.ai.controller;

import com.baidu.ai.consts.PathConst;
import com.baidu.ai.enums.StatusEnum;
import com.baidu.ai.service.UserService;
import com.baidu.ai.request.AIRequest;
import com.baidu.ai.response.AIResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(PathConst.USER_PATH)
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public AIResponse addFace(@RequestParam String groupId,
                              @RequestParam String image,
                              @RequestParam String userId,
                              @RequestParam String imageType){
        logger.info("begin to add user face");
        if ( StringUtils.isEmpty(groupId)
                || StringUtils.isEmpty(image)
                || StringUtils.isEmpty(userId)
                || StringUtils.isEmpty(imageType)){
            return new AIResponse(StatusEnum.INPUT_PARAMETER_INVALID);
        }
        AIRequest aiRequest = new AIRequest(groupId, userId, image, imageType);
        return userService.add(aiRequest);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public AIResponse deleteFace(@RequestBody AIRequest aiRequest){
        logger.info("begin to delete user face");
        if (null == aiRequest || StringUtils.isEmpty(aiRequest.getGroupId())
                || StringUtils.isEmpty(aiRequest.getFaceToken())
                || StringUtils.isEmpty(aiRequest.getUserId())){
            return new AIResponse(StatusEnum.INPUT_PARAMETER_INVALID);
        }
        return userService.delete(aiRequest);
    }
}
