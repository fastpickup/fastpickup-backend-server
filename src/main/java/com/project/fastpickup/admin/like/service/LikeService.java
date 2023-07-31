package com.project.fastpickup.admin.like.service;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

// Like Service Interface 
public interface LikeService {
    
    // Toggle Like Service 
    int toggleLike(Long pno, String email);

    // Count Like service 
    Long countLike(Long pno);
}
