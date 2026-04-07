package com.javaweb.springboot_web.mapper;

import com.javaweb.springboot_web.pojo.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    //查找

    public List<Note> findAll();


    //删除
    public Integer deleteById(Integer id);

    //增加

    public void insert(Note note);

    //更新

    public Integer updateById(Note note);

    //清空所有
    public void clear();


}
