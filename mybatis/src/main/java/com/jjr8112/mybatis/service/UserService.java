package com.jjr8112.mybatis.service;


import com.jjr8112.mybatis.domain.UserDO;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDO get(Integer id);

    List<UserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}

