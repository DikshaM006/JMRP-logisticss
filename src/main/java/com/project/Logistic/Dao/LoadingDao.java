package com.project.Logistic.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.Repository.LoadingRepo;
@Repository
public class LoadingDao {
	@Autowired
	private LoadingRepo loadingRepo;
    public Loading saveLoading(Loading loading) {
    	return loadingRepo.save(loading);
    }
    public Loading findById(int id) {
    	Optional<Loading>optional=loadingRepo.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}else {
    		return null;
    	}
		
    }
}
