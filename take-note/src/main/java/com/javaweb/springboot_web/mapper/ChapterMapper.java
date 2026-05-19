package com.javaweb.springboot_web.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.springboot_web.pojo.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {


    @Select("select c.* from chapter c inner join notebook n on n.id = c.notebook_id ")
    List<Chapter> queryChapterByWrapper(@Param("ew") QueryWrapper<Chapter> wrapper);

    @Select("select * from chapter"+"where id > #{cursor}"+"order by id"+"limit #{limit}")
    List<Chapter> getChapterByCursor(@Param("cursor") Long cursor,@Param("limit") int limit);

}
