package com.baidu.ai.service.impl;

import com.baidu.ai.bean.Location;
import com.baidu.ai.bean.User;
import com.baidu.ai.cache.ConfigCache;
import com.baidu.ai.consts.AIConsts;
import com.baidu.ai.dao.ConfigDao;
import com.baidu.ai.dao.UserDao;
import com.baidu.ai.enums.StatusEnum;
import com.baidu.ai.request.AIRequest;
import com.baidu.ai.response.AIResponse;
import com.baidu.ai.response.BaiduResponse;
import com.baidu.ai.service.UserService;
import com.baidu.ai.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.sf.json.JSONObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public AIResponse add(AIRequest aiRequest) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            logger.info("begin to add face,url:{}",url);
            Map<String, Object> map = new HashMap<>();
            map.put("image", aiRequest.getImage());
            map.put("group_id", aiRequest.getGroupId());
            map.put("user_id", aiRequest.getUserId());
            map.put("user_info", aiRequest.getUserInfo());
            map.put("liveness_control", "NORMAL");
            map.put("quality_control", "LOW");
            map.put("image_type", aiRequest.getImageType());
            JSONObject jsonObject = addOrDeleteFace(map, url);
            Integer errorCode = (Integer) jsonObject.get("error_code");
            if (0 == errorCode){
                BaiduResponse response = parseAIResults(jsonObject);
                saveToDB(aiRequest.getUserId(),response);
                return new AIResponse<>(StatusEnum.SUCCESS,response);
            }else if(223105 == errorCode){
                return new AIResponse(StatusEnum.FACE_EXIST);
            }else {
                return new AIResponse(StatusEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public  AIResponse delete(AIRequest aiRequest) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete";
        try {
            logger.info("begin to delete face,url:{}",url);
            Map<String, Object> map = new HashMap<>();
            map.put("group_id", aiRequest.getGroupId());
            map.put("user_id", aiRequest.getUserId());
            map.put("face_token", aiRequest.getFaceToken());
            JSONObject jsonObject = addOrDeleteFace(map, url);
            Integer errorCode = (Integer) jsonObject.get("error_code");
            if (0 == errorCode){
                deleteFromDB(aiRequest.getUserId(), aiRequest.getFaceToken());
                return new AIResponse<>(StatusEnum.SUCCESS);
            }else if(223103 == errorCode){
                return new AIResponse(StatusEnum.FACE_NOT_EXIST);
            }else {
                return new AIResponse(StatusEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject addOrDeleteFace(Map map,String url) throws Exception {
        String param = new Gson().toJson(map);
        Map<String, String> cache = ConfigCache.configCache;
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String accessToken = cache.get(AIConsts.ACCESS_TOKEN);
        String result = HttpUtil.post(url, accessToken, "application/json", param);
        System.out.println(result);
        logger.info(result);
        return JSONObject.fromObject(result);
    }

    //{"error_code":0,"error_msg":"SUCCESS","log_id":1599001151010,"timestamp":1578199462,"cached":0,
    // "result":{"face_token":"19819b8a93208d0d5c19f93082f7f4b3",
    // "location":{"left":184.16,"top":227.39,"width":216,"height":213,"rotation":1}}}
    public static BaiduResponse parseAIResults(JSONObject jsonObject)
    {
        String faceToken = null;
        Location locationBean = null;
        try {
            JSONObject result = jsonObject.getJSONObject("result");
            faceToken = result.getString("face_token");
            JSONObject location = result.getJSONObject("location");
            locationBean = (Location) JSONObject.toBean(location, Location.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaiduResponse(faceToken, locationBean);
    }


    public void saveToDB(String userId,BaiduResponse response){
        Location location = response.getLocation();
        User user = new User();
        BeanUtils.copyProperties(location,user);
        user.setUserId(userId);
        user.setAccessToken(response.getAccessToken());
        userDao.insertToDB(userId,response.getAccessToken(),location.getLeft(),location.getTop(),location.getWidth(),location.getHeight(),location.getRotation());
    }

    public void deleteFromDB(String userId,String accessToken){
        userDao.deleteByParams(userId,accessToken);
    }
}
