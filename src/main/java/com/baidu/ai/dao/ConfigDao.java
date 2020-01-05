package com.baidu.ai.dao;

import com.baidu.ai.bean.ConfigItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ConfigDao extends CrudRepository<ConfigItem,Integer> {
    @Modifying
    @Query(value = "delete from config_item where item_name = ?1 and item_value = ?2",nativeQuery = true)
    void deleteByParams(String itemName,String itemValue);
}
