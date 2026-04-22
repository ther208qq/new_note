package com.jlu.auth.mapper;

import com.jlu.auth.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ther
* @description 针对表【user】的数据库操作Mapper
* @createDate 2026-04-13 21:15:04
* @Entity com.jlu.auth.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




