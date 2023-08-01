package com.project.fastpickup.admin.like.service.impl;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.like.dto.LikeCreateDTO;
import com.project.fastpickup.admin.like.dto.LikeDTO;
import com.project.fastpickup.admin.like.mappers.LikeMapper;
import com.project.fastpickup.admin.like.service.LikeService;

import lombok.extern.log4j.Log4j2;

// Like Service Class
@Log4j2
@Service
public class LikeServiceImpl implements LikeService {

    // 의존성 주입
    private final LikeMapper likeMapper;

    public LikeServiceImpl(LikeMapper likeMapper) {
        log.info("Constructor Called, Like Mapper Injected.");
        this.likeMapper = likeMapper;
    }

    // Toggle Like ServiceImpl
    @Override
    @Transactional
    public int toggleLike(Long pno, String email) {
        log.info("Is Running Toggle Like ServiceImpl");
        LikeCreateDTO likeCreateDTO = LikeCreateDTO
                .builder()
                .email(email)
                .pno(pno)
                .build();
        LikeDTO checkLike = likeMapper.checkLikeByMemberAndPost(pno, email);
        if (checkLike == null) {
            likeMapper.createLike(likeCreateDTO);
            return likeMapper.incrementLikeCount(pno);
        } else {
            likeMapper.deleteLike(checkLike);
            return likeMapper.decrementLikeCount(pno);
        }
    }

    // Count Like ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public Long countLike(Long pno) {
        log.info("Is Running Count Like ServiceImpl");
        return likeMapper.countLikes(pno);
    }

    // Check Like
    @Override
    @Transactional(readOnly = true)
    public LikeDTO checkLikeByMemberAndPost(Long pno, String email) {
        log.info("Is Running Check Like Member And Post");
        return likeMapper.checkLikeByMemberAndPost(pno, email);
    }
}
