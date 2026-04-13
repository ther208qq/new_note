package com.javaweb.springboot_web.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.springboot_web.mapper.NoteBookMapper;
import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.pojo.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Service 无需编写任何service代码
@Service
public class NoteBookServiceImp extends ServiceImpl<NoteBookMapper,NoteBook> implements NoteBookService {

}
