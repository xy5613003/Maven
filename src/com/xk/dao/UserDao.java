package com.xk.dao;

import com.xk.vo.User;

public interface UserDao {
boolean login(User user);
boolean register(User user);
}
