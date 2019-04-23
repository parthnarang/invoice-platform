package com.billt.core.invoicereceiver.Repositories;
import java.util.List;

import com.billt.core.invoicereceiver.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UserRepository  extends MongoRepository<User,String>{
    public User findByMobile(String mobile);
    public User findByEmail(String email);

    /*MongoTemplate mongoTemplate;

    @Autowired
    public UserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<User> getAllUsers(){
      return mongoTemplate.findAll(User.class);
    }

    public void addnewuser(User object){
        mongoTemplate.save(object);
    }

    public User getUser(String id){
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
                User.class);
    }

   /* public WriteResult updateObject(String id, String name)
    {

    }*/
}
