package com.baidu.ai.cache;

import com.baidu.ai.bean.ConfigItem;
import com.baidu.ai.dao.ConfigDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration("myScheduled")
@EnableScheduling
@Component
public class ConfigCache {

    private static final Logger logger = LoggerFactory.getLogger(ConfigCache.class);
    @Autowired
    private ConfigDao configDao;

    public static Map<String,String> configCache = new HashMap<String,String>();

    @Scheduled(cron="0/60 * * * * ?")
    public void cacheConfig(){
        List<ConfigItem> configItems = (List<ConfigItem>) this.configDao.findAll();
        logger.info(configItems.toString());
        logger.info("=====================");
        for (ConfigItem configItem:configItems) {
            configCache.put(configItem.getItemName(),configItem.getItemValue());
        }
        logger.info(configCache.size()+"");
        for (String key:configCache.keySet()) {
                logger.info("key:{},value:{}",key,configCache.get(key));
        }
    }


}
