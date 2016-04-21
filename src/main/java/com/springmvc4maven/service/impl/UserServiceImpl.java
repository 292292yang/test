package com.springmvc4maven.service.impl;

import com.springmvc4maven.dao.users.AccountMapper;
import com.springmvc4maven.domain.users.AccountDO;
import com.springmvc4maven.domain.users.User;
import com.springmvc4maven.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Copyright© 2013-2016 AutoChina International Ltd. All rights reserved.</p>
 *
 * @Author yangzhibin@che001.com
 * @Date 2016/4/19
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private AccountMapper accountMapper;

    @Override
    public AccountDO queryUser(Long userId) {
        return accountMapper.select(userId);
    }

    @Override
    public void transfer(Long fromUserId, Long toUserId, Integer num) {
//        try{
            AccountDO fromAccount=accountMapper.select(fromUserId);
            AccountDO toAccount=accountMapper.select(toUserId);
            int n=fromAccount.getNum()-num;
            int n2=toAccount.getNum()+num;
            fromAccount.setNum(n);
            toAccount.setNum(n2);
            accountMapper.update(fromAccount);
            accountMapper.update(toAccount);
//            int s=2/0;
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        throw new RuntimeException("事务测试");
    }


}
