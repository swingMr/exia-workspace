package com.excellence.iamp.mongodb.dao;

import java.lang.reflect.Field;
import java.util.List;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.excellence.iamp.util.Page;
import com.excellence.iamp.util.ReflectionUtils;
import com.mongodb.WriteResult;

public class BaseMongoDaoImpl<T> implements BaseMongoDao<T>{
	
	@Autowired
    protected MongoTemplate mongoTemplate;    
	
	public T save(T entity) {    
        mongoTemplate.insert(entity);    
        return entity;    
    }    
	public T save(T entity,String collectionName) {    
        mongoTemplate.insert(entity, collectionName);
        return entity;    
    } 
    
    public T findById(String id) {    
        return mongoTemplate.findById(id, this.getEntityClass());    
    }
    
    public T findById(String id, String collectionName) {    
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);    
    }    
    
    public List<T> findAll() {    
        return mongoTemplate.findAll(this.getEntityClass());    
    }    
    
    public List<T> findAll(String collectionName) {    
        return mongoTemplate.findAll(this.getEntityClass(), collectionName);    
    }    
    
    public List<T> find(Query query) {    
        return mongoTemplate.find(query, this.getEntityClass());    
    }
    
    public List<T> find(Query query, String collectionName) {
    	return mongoTemplate.find(query, this.getEntityClass(), collectionName); 
	}
    
    public T findOne(Query query) {    
        return mongoTemplate.findOne(query, this.getEntityClass());    
    }
    
    public T findOne(Query query, String collectionName) {
    	return mongoTemplate.findOne(query, this.getEntityClass(),collectionName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<T> findPage(Query query, int pageNo, int pageSize) {
		query=query==null?new Query(Criteria.where("_id").exists(true)):query;    
        long count = this.count(query);    
        // 总数 
        Page page = new Page();
        page.setTotalCount((int) count); 
        page.setCurrentPage(pageNo);
        page.setPageSize(pageSize);
        query.skip((pageNo - 1) * pageSize).limit(pageSize);    
        List<T> rows = this.find(query);    
        page.build(rows);    
        return page;    
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<T> findPage(Query query, String collectionName, int pageNo, int pageSize) {
		query=query==null?new Query(Criteria.where("_id").exists(true)):query;    
        long count = this.count(query,collectionName);    
        // 总数 
        Page page = new Page();
        page.setTotalCount((int) count); 
        page.setCurrentPage(pageNo);
        page.setPageSize(pageSize);
        query.skip((pageNo - 1) * pageSize).limit(pageSize);    
        List<T> rows = this.find(query,collectionName);    
        page.build(rows);    
        return page; 
	}

    public long count(Query query) {    
        return mongoTemplate.count(query, this.getEntityClass());    
    }
    
    public long count(Query query, String collectionName) {
    	return mongoTemplate.count(query, collectionName);
	}

    public WriteResult update(Query query, Update update) {    
        if (update==null) {    
            return null;    
        }    
        return mongoTemplate.updateMulti(query, update, this.getEntityClass());    
    } 
    
    public WriteResult update(Query query, Update update, String collectionName) {
		if (update==null) {    
            return null;    
        } 
		return mongoTemplate.updateMulti(query, update, this.getEntityClass(),collectionName);    
	}
    
    public T updateOne(Query query, Update update, String collectionName) {
		return mongoTemplate.findAndModify(query, update, this.getEntityClass(),collectionName); 
	}

	

	
    
    public T updateOne(Query query, Update update) {    
        if (update==null) {    
            return null;    
        }    
        return mongoTemplate.findAndModify(query, update, this.getEntityClass());    
    }    
      
    public WriteResult update(T entity) {    
        Field[] fields = this.getEntityClass().getDeclaredFields();    
        if (fields == null || fields.length <= 0) {    
            return null;    
        }    
        Field idField = null;    
        // 查找ID的field    
        for (Field field : fields) {    
            if (field.getName() != null    
                    && "id".equals(field.getName().toLowerCase())) {    
                idField = field;    
                break;    
            }    
        }    
        if (idField == null) {    
            return null;    
        }    
        idField.setAccessible(true);    
        String id=null;    
        try {    
            id = (String) idField.get(entity);    
        } catch (IllegalArgumentException e) {    
            e.printStackTrace();    
        } catch (IllegalAccessException e) {    
            e.printStackTrace();    
        }    
        if (id == null || "".equals(id.trim()))    
            return null;    
        // 根据ID更新    
        Query query = new Query(Criteria.where("_id").is(id));    
        // 更新    
        // Update update = new Update();    
        // for (Field field : fields) {    
        // // 不为空 不是主键 不是序列化号    
        // if (field != null    
        // && field != idField    
        // && !"serialversionuid"    
        // .equals(field.getName().toLowerCase())) {    
        // field.setAccessible(true);    
        // Object obj = field.get(entity);    
        // if (obj == null)    
        // continue;    
        // update.set(field.getName(), obj);    
        // }    
        // }    
        Update update = ReflectionUtils.getUpdateObj(entity);    
        if (update == null) {    
            return null;    
        }    
        return mongoTemplate.updateFirst(query, update, getEntityClass());    
    }
    
    public WriteResult update(T entity, String collectionName) {
    	Field[] fields = this.getEntityClass().getDeclaredFields();    
        if (fields == null || fields.length <= 0) {    
            return null;    
        }    
        Field idField = null;    
        // 查找ID的field    
        for (Field field : fields) {    
            if (field.getName() != null    
                    && "id".equals(field.getName().toLowerCase())) {    
                idField = field;    
                break;    
            }    
        }    
        if (idField == null) {    
            return null;    
        }    
        idField.setAccessible(true);    
        String id=null;    
        try {    
            id = (String) idField.get(entity);    
        } catch (IllegalArgumentException e) {    
            e.printStackTrace();    
        } catch (IllegalAccessException e) {    
            e.printStackTrace();    
        }    
        if (id == null || "".equals(id.trim()))    
            return null;    
        // 根据ID更新    
        Query query = new Query(Criteria.where("_id").is(id));    
        // 更新    
        // Update update = new Update();    
        // for (Field field : fields) {    
        // // 不为空 不是主键 不是序列化号    
        // if (field != null    
        // && field != idField    
        // && !"serialversionuid"    
        // .equals(field.getName().toLowerCase())) {    
        // field.setAccessible(true);    
        // Object obj = field.get(entity);    
        // if (obj == null)    
        // continue;    
        // update.set(field.getName(), obj);    
        // }    
        // }    
        Update update = ReflectionUtils.getUpdateObj(entity);    
        if (update == null) {    
            return null;    
        }    
        return mongoTemplate.updateFirst(query, update, collectionName); 
	}
    
    public void remove(Query query) {    
        mongoTemplate.remove(query, this.getEntityClass());    
    }
    
    public void remove(Query query, String collectionName) {
    	mongoTemplate.remove(query, collectionName);  
	}
    /**   
     * 获得泛型类   
     */    
    private Class<T> getEntityClass() {          
        return ReflectionUtils.getSuperClassGenricType(getClass());    
    }    

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	
	
	

}
