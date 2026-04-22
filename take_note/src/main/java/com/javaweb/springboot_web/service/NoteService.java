package com.javaweb.springboot_web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.pojo.NoteBook;

import java.util.List;

public interface NoteService extends IService<Note> {

    public List<Note> findAll();

    public void insert(Note note);

    public Integer deleteById(Integer id);

    public void clear();
}
