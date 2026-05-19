package com.javaweb.springboot_web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.springboot_web.pojo.Note;

import java.util.List;

public interface NoteService extends IService<Note> {


    public void insert(Note note);

    public boolean deleteById(Integer id);

    public void clear();

    public String summary(String content);

    public String check(String id);

}
