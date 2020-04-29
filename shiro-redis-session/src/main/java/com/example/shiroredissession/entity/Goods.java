package com.example.shiroredissession.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {

    public Integer id;

    public String name;

    public double price;

    public int num;
}
