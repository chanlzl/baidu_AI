package com.baidu.ai.service.impl;

import com.baidu.ai.auth.AuthService;
import com.baidu.ai.bean.ConfigItem;
import com.baidu.ai.cache.ConfigCache;
import com.baidu.ai.consts.AIConsts;
import com.baidu.ai.dao.ConfigDao;
import com.baidu.ai.enums.StatusEnum;
import com.baidu.ai.response.AIResponse;
import com.baidu.ai.service.GroupService;
import com.baidu.ai.util.GsonUtils;
import com.baidu.ai.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private ConfigDao configDao;

    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
    /**
     * 创建用户组
     */
    public  AIResponse groupAdd(String groupId) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/add";
        try {
            Integer errorCode = (Integer) AddOrDeleteGroup(groupId, url);
            if (0 == errorCode){
                saveToDB(groupId);
                return new AIResponse(StatusEnum.SUCCESS);
            }else if(223101 == errorCode){
                return new AIResponse(StatusEnum.GROUP_EXIST);
            }else {
                return new AIResponse(StatusEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

        /**
         * 删除用户组
         */
        public   AIResponse groupDelete(String groupId) {
            // 请求url
            String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete";
            try {
                Integer errorCode = (Integer) AddOrDeleteGroup(groupId, url);
                if (0 == errorCode){
                    deleteFromDB(groupId);
                    return new AIResponse(StatusEnum.SUCCESS);
                }else if(223100 == errorCode){
                    return new AIResponse(StatusEnum.GROUP_NOT_EXIST);
                }else {
                    return new AIResponse(StatusEnum.UNKNOWN_ERROR);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

    public static Object AddOrDeleteGroup(String groupId, String url) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", groupId);
        logger.info("groupId：{}，url:{}",groupId,url);
        String param = GsonUtils.toJson(map);
        Map<String, String> cache = ConfigCache.configCache;
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String accessToken = cache.get(AIConsts.ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)){
            accessToken = new AuthService().getAuth();
        }
        String result = HttpUtil.post(url, accessToken, "application/json", param);
        logger.info(result);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.get("error_code");
    }


    /**
     * 保存group_id到数据库
     * @param groupId
     */
    public   void saveToDB(String groupId){
        ConfigItem configItem = new ConfigItem();
        configItem.setItemName(AIConsts.GROUP_ID);
        configItem.setItemValue(groupId);
        configItem.setItemDesc(AIConsts.GROUP_ID);
        configDao.save(configItem);
    }


    /**
     * 删除group_id从数据库
     * @param groupId
     */
    public  void deleteFromDB(String groupId){
        configDao.deleteByParams(AIConsts.GROUP_ID,groupId);
    }
}
