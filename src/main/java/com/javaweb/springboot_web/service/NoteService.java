package com.javaweb.springboot_web.service;

import com.javaweb.springboot_web.pojo.Note;

import java.util.List;

public interface NoteService {

    public List<Note> findAll();


    public void insert(Note note);


    public Integer deleteById(Integer id);

    public Integer updateById(Note note);
}
