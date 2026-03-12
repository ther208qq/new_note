package com.javaweb.springboot_web.service;

import com.javaweb.springboot_web.mapper.NoteMapper;
import com.javaweb.springboot_web.pojo.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//该层负责处理从Dao层获取地数据

@Service//表示交给容器管理 表示Service层地bean对象
public class NoteServiceImp implements NoteService{

    @Autowired //自动注入
//    private NoteDao noteDao;
    private NoteMapper noteMapper;

//    private NoteDao noteDao = new NoteDaoImp();

    @Override
    public List<Note> findAll(){

        List<Note> noteList = noteMapper.findAll();

        return noteList;
    }

    @Override
    public void insert(Note note){
        noteMapper.insert(note);
    }


    @Override
    public Integer deleteById(Integer id){
        noteMapper.deleteById(id);
        return 1;
    }

    @Override
    public Integer updateById(Note note){
        Integer lines = noteMapper.updateById(note);

        return lines;
    }

}
