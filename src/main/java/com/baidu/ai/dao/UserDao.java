package com.baidu.ai.dao;

import com.baidu.ai.bean.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface UserDao extends CrudRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from user where user_id = ?1 and access_token = ?2",nativeQuery = true)
    void deleteByParams(String userId,String accessToken);

    @Transactional
    @Modifying
    @Query(value = "insert into user(user_id,access_token,`left`,top,width,height, rotation) values " +
            "(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void insertToDB(String userId,String accessToken,Double left,Double top,Integer width,
                    Integer height,Integer rotation);
}
