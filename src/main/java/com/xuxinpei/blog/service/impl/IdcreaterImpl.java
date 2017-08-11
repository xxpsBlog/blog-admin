package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.IdcreaterMapper;
import com.xuxinpei.blog.pojo.Idcreater;
import com.xuxinpei.blog.service.IIdcreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IdcreaterImpl implements IIdcreater {

    @Autowired
    private IdcreaterMapper idcreaterMapper;

    public int updateValues(Map<String, Object> map) {
        return idcreaterMapper.updateValues(map);
    }

    public Idcreater getByName(String name) {
        return idcreaterMapper.getByName(name);
    }

    public List<Idcreater> getList() {
        return idcreaterMapper.getList();
    }

    public Idcreater selectByPrimaryKey(Integer id) {
        return idcreaterMapper.selectByPrimaryKey(id);
    }

    public void insert(Idcreater bean) {
        idcreaterMapper.insert(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        idcreaterMapper.deleteByPrimaryKey(id);
    }

}