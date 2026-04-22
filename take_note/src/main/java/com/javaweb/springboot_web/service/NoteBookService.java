package com.javaweb.springboot_web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.springboot_web.pojo.NoteBook;

public interface NoteBookService extends IService<NoteBook> {

    public String getUserNickname(String username);

}
