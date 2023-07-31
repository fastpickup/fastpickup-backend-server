package com.project.fastpickup.admin.like.mappers;

import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.like.dto.LikeCreateDTO;
import com.project.fastpickup.admin.like.dto.LikeDTO;

// Like Mapper Interface
public interface LikeMapper {
    
    // Create Like
    int createLike(LikeCreateDTO likeCreateDTO);

    // Delete Like
    int deleteLike(LikeDTO likeDTO);

    // Count Like
    Long countLikes(Long pno);

    // Check Like For Member 
    LikeDTO checkLikeByMemberAndPost(@Param("pno") Long pno, @Param("email") String email);

}
