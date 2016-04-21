package com.springmvc4maven.dao.users;

import com.springmvc4maven.domain.users.AccountDO;
import org.springframework.stereotype.Repository;

/**
 * <p>Copyright© 2013-2016 AutoChina International Ltd. All rights reserved.</p>
 *
 * @Author yangzhibin@che001.com
 * @Date 2016/4/19
 */
@Repository("accountMapper")
public interface AccountMapper {

    /**
     * 更新账户
     * @param account
     */
    void update(AccountDO account);

    /**
     * 查询账户信息
     * @param id
     * @return
     */
    AccountDO select(Long id);

    /**
     * 创建账户
     * @param account
     */
    void create(AccountDO account);
}
