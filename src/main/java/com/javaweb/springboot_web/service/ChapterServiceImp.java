package com.javaweb.springboot_web.service;

import com.javaweb.springboot_web.mapper.ChapterMapper;
import com.javaweb.springboot_web.mapper.NoteBookMapper;
import com.javaweb.springboot_web.pojo.Chapter;
import com.javaweb.springboot_web.pojo.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImp implements ChapterService{

    @Autowired
    private ChapterMapper chapterMapper;
    //查
    @Override
    public List<Chapter> findAll(){
        List<Chapter> chapterList = chapterMapper.findAll();
        return chapterList;
    }
    //增
    @Override
    public void insert(Chapter chapter){
        System.out.println("接收到的ID: " + chapter.getNotebook_id());
        chapterMapper.insert(chapter);
    }
    //删
    @Override
    public Integer deleteById(Integer id){
        int result = chapterMapper.deleteById(id);
        return result;
    }
    //更新
    @Override
    public Integer updateById(Chapter chapter){
        return 1;
    }

    //清空
    @Override
    public void clear(){

    }
}
