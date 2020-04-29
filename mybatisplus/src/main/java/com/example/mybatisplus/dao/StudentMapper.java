package com.example.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生Mapper层
 *
 * @author lz
 * @date 2019/9/27
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
