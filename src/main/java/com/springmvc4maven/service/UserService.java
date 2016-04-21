package com.springmvc4maven.service;

import com.springmvc4maven.datasource.DataSource;
import com.springmvc4maven.domain.users.AccountDO;

/**
 * <p>Copyright© 2013-2016 AutoChina International Ltd. All rights reserved.</p>
 *
 * @Author yangzhibin@che001.com
 * @Date 2016/4/19
 */
public interface UserService {

    @DataSource("master")
    AccountDO queryUser(Long userId);

    @DataSource("master")
    void transfer(Long fromUserId,Long toUserId,Integer num);
}
