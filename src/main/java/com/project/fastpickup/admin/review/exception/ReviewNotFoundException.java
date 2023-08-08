package com.project.fastpickup.admin.review.exception;

// 해당하는 리뷰 번호가 없을때 ReviewNotFoundException
public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException() {

    }

    public ReviewNotFoundException(String message) {

    }
}
