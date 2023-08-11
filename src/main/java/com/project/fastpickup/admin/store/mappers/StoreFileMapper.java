package com.project.fastpickup.admin.store.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

// Store File Mapper Interface
@Mapper
public interface StoreFileMapper {
    
    // Create File 
    int createStoreFile(List<Map<String,String>> imageList);
    
    // Delete File 
    int deleteStoreFile(Long sno);
}
