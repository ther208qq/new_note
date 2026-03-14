package com.javaweb.springboot_web.mapper;

import com.javaweb.springboot_web.pojo.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    //查找
//    @Select("select * from note")

    public List<Note> findAll();

//    @Select("select * from note where id = #{id} ")
//    public List<Note> findById(@Param());

    //删除
//    @Delete("delete from note where id = #{id}")
    public Integer deleteById(Integer id);

    //增加
//    @Insert("insert into note(id,content) values(#{id},#{content})")
    public void insert(Note note);

    //更新
//    @Update("update note set content = #{content} where id = #{id}")
    public Integer updateById(Note note);

    //清空所有
    public void clear();


}
