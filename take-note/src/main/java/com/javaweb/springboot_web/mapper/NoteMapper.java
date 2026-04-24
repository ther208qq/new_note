package com.javaweb.springboot_web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.springboot_web.pojo.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper extends BaseMapper<Note> {

    //查找

    public List<Note> findAll();


    //删除
    public Integer deleteById(Integer id);

    //增加


    //清空所有
    public void clear();


}
