package com.niuma.easyexceldemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niuma.easyexceldemo.mapper.UserMapper;
import com.niuma.easyexceldemo.model.domain.User;
import com.niuma.easyexceldemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author niumazlb
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-01-19 20:31:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




