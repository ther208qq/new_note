package com.javaweb.springboot_web.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.springboot_web.pojo.Chapter;
import com.javaweb.springboot_web.pojo.NoteBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChapterMapper {

    public List<Chapter> findAll();

    //删除
    public Integer deleteById(Integer id);

    //增加

    public void insert(Chapter chapter);

    //更新

    public Integer updateById(Chapter chapter);

    //清空所有
    public void clear();

    @Select("select c.* from chapter c inner join notebook n on n.id = c.notebook_id ")
    List<Chapter> queryChapterByWrapper(@Param("ew") QueryWrapper<Chapter> wrapper);

}
