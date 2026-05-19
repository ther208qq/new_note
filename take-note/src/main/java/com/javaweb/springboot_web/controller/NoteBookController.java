package com.javaweb.springboot_web.controller;


import com.javaweb.springboot_web.pojo.NoteBook;
import com.javaweb.springboot_web.service.NoteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notebook")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
public class NoteBookController {

    @Autowired
    private NoteBookService noteBookService;

    //展示所有book
    @GetMapping
    public List<NoteBook> showBook(){
        List<NoteBook> noteBookList = noteBookService.list();
        return noteBookList;
    }

    //插入book
    @PostMapping
    public String insert(@RequestBody NoteBook noteBook){
        System.out.println("正在调用");
        boolean res = noteBookService.insert(noteBook);
        if(res){
            return "success";
        }
        return "false";
    }

    //根据id删除单个book
    @DeleteMapping("/{id}")
    public String deletebyid(@PathVariable Long id){

        noteBookService.deleteById(id);

        return "success delete";
    }

//    //根据id更新
    @PutMapping
    public String updatebyid(@RequestBody NoteBook noteBook){

        noteBookService.updateById(noteBook);

        return "success update";
    }

    //删除所有book
    @DeleteMapping
    public String clear(){
        noteBookService.remove(null);
        return "success clear";
    }

    @GetMapping("/getnickname/{username}")
    private String getUserNickname(@PathVariable("username") String username){
        String res = noteBookService.getUserNickname(username);
        return res;
    }

}
