package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.util.BeanConverter;
import com.xuxinpei.blog.dao.BaseDao;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T, DAO extends BaseDao<T, PK>, PK extends Serializable> {
    protected abstract DAO getDao();

    public int deleteByPrimaryKey(PK id) {
        return getDao().deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return getDao().insert(record);
    }

    public int insertSelective(T record) {
        return getDao().insertSelective(record);
    }

    public T selectByPrimaryKey(PK id) {
        return getDao().selectByPrimaryKey(id);
    }

    public T getByCondition(T record) {
        Map params = new HashMap();
        params.put("condition", record);
        return (T) getDao().getByCondition(params);
    }

    public T getByCondition(T record, VO vo) {
        Map params = new HashMap();
        params.put("condition", record);
        params.put("vo", vo);
        return (T) getDao().getByCondition(params);
    }

    public T getByCondition(T record, VO vo, String orderBy) {
        Map params = new HashMap();
        params.put("condition", record);
        params.put("vo", vo);
        params.put("orderBy", orderBy);
        return (T) getDao().getByCondition(params);
    }

    public int updateByPrimaryKeySelective(T record) {
        return getDao().updateByPrimaryKeySelective(record);
    }

    public int updateByCondition(T record, T condition) {
        Map params = new HashMap();
        params.put("entity", record);
        params.put("condition", condition);
        return getDao().updateByCondition(params);
    }

    public int updateByPrimaryKey(T record) {
        return getDao().updateByPrimaryKey(record);
    }

    public int getCount(Map<String, ?> map) {
        return getDao().getCount(map);
    }

    public Page<T> getPage(int pageNumber, int maxRows, String url, Map<String, Object> map) {
        String params = Method.getQuery(map);
        int totalRow = getDao().getCount(map);
        Page page = new Page(maxRows, url, params);
        page.setCurPage(pageNumber);
        page = PageUtil.createPage(page, totalRow);
        map.put("pageBeginIndex", Integer.valueOf(page.getBeginIndex()));
        map.put("pageSize", Integer.valueOf(page.getPageSize()));
        page.setResult(getDao().getList(map));
        return page;
    }
}