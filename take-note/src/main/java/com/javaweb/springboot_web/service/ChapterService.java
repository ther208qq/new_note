package com.javaweb.springboot_web.service;

import com.javaweb.springboot_web.pojo.Chapter;

import java.util.List;

public interface ChapterService {

    public List<Chapter> findAll();

    public void insert(Chapter chapter);

    public Integer deleteById(Integer id);

    public Integer updateById(Chapter chapter);

    public void clear();

}
