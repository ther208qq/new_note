package com.javaweb.springboot_web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.springboot_web.pojo.NoteBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteBookMapper extends BaseMapper<NoteBook> {
    //无需自己实现crud
}
