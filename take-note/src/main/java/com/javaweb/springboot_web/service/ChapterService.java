package com.javaweb.springboot_web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.springboot_web.pojo.Chapter;
import com.javaweb.springboot_web.pojo.NoteBook;

import java.util.List;

public interface ChapterService extends IService<Chapter> {

    public List<Chapter> list();

    public Chapter insert(Chapter chapter);

    public boolean deleteById(Long id);

    public boolean updateById(Chapter chapter);

    public void clear();

}
