package com.xxp.blog.service.impl;

import com.xxp.blog.dao.IdcreaterMapper;
import com.xxp.blog.pojo.Idcreater;
import com.xxp.blog.service.IIdcreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IdcreaterImpl extends BaseServiceImpl<Idcreater, IdcreaterMapper, Integer>
        implements IIdcreater {

    @Autowired
    private IdcreaterMapper idcreaterDao;

    public int updateValues(Map<String, Object> map) {
        return this.idcreaterDao.updateValues(map);
    }

    public Idcreater getByName(String name) {
        return this.idcreaterDao.getByName(name);
    }

    protected IdcreaterMapper getDao() {
        return this.idcreaterDao;
    }
}