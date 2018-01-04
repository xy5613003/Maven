package com.xk.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.dao.BaseMapper;

public class BaseService<M extends BaseMapper,E,T>{
	
    protected M mapper;

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int save(T record){
        return mapper.insert(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int saveSelective(T record){
        return mapper.insertSelective(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int delete(String id){
        return mapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByExample(E e){
        mapper.deleteByExample(e);
    }

   
	@Transactional(propagation = Propagation.REQUIRED)
    public void updateSelective(T record){
        mapper.updateByPrimaryKeySelective(record);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T record){
        mapper.updateByPrimaryKey(record);
    }
    @Transactional(readOnly = true)
    public <T> T get(String id){
        return (T)mapper.selectByPrimaryKey(id);
    }


    @Transactional(readOnly = true)
    public List<T> findList(E example, PageBounds pageBounds){
        Assert.notNull(pageBounds);
        pageBounds.setContainsTotalCount(true);
        List<T> list=mapper.selectByExample(example,pageBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public List<T> findList(E example){
        return mapper.selectByExample(example,new PageBounds());
    }

    @Transactional(readOnly = true)
    public T findOne(E example){
        List<T> list= findList(example);
        return CollectionUtils.isEmpty(list)?null:list.get(0);
    }

    @Transactional(readOnly = true)
    public int findCount(E example){
        return mapper.countByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateExample(T record,E example){
        return mapper.updateByExample(record,example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateExampleSelective(T record,E example){
        return mapper.updateByExampleSelective(record,example);
    }
}
