package com.javaweb.springboot_web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javaweb.springboot_web.mapper.ChapterMapper;
import com.javaweb.springboot_web.mapper.NoteBookMapper;
import com.javaweb.springboot_web.pojo.Chapter;
import com.javaweb.springboot_web.pojo.NoteBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.query.ExampleQueryMapper;

import java.util.List;

@SpringBootTest
class SpringbootWebApplicationTests {

//	@Test
//	void contextLoads() {
//	}

//	自动注入
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private NoteBookMapper noteBookMapper;

	@Autowired
	private ChapterMapper chapterMapper;

//	@Test
//	void redisConntionText(){
////		redis操作
////		key:value形式
//		redisTemplate.opsForValue().set("test_content","hello_redis");
//
//		String value = redisTemplate.opsForValue().get("test_content");
//		System.out.println("测试结果为:"+value);
//	}

	@Test
	void testQueryWrapper(){
		QueryWrapper <NoteBook> wrapper = new QueryWrapper<NoteBook> ()
				.select("id","title","createDateTime")
				.like("title","二")
				.ge("id",28);

		List<NoteBook> list = noteBookMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

//	@Test
//	void testUpdateWrapper(){
//		UpdateWrapper<NoteBook> wrapper = new UpdateWrapper<NoteBook>()
//				.setSql("title = Test")
//				.in("id",29);
//		noteBookMapper.update(null,wrapper);
//	}
	@Test
	void testChapter(){
		QueryWrapper<Chapter> wrapper = new QueryWrapper<Chapter>();

		List<Chapter> list = chapterMapper.queryChapterByWrapper(wrapper);
		list.forEach(System.out::println);
	}





}
