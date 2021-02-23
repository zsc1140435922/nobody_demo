package com.zsc.example.nobody.dubbo;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-02-05 11:40
 **/
public class UserServiceImpl implements UserService {
    @Override
    public String getName(String str) {
        return str;
    }
}
