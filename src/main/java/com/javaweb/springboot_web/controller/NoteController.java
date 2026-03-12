package com.javaweb.springboot_web.controller;


import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.service.NoteService;
import com.javaweb.springboot_web.service.NoteServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//该层负责响应请求

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired //自动注入
    private NoteService noteService;

    @RequestMapping("/showNote")
    public List<Note> showNote(){

//        NoteService noteservice = new NoteServiceImp();

        List<Note> noteList = noteService.findAll();

        return noteList;
    }

    @RequestMapping("/insert")
    public String inset(Note note){

        System.out.println("正在调用");
        noteService.insert(note);

        return "sucess";
    }

}
