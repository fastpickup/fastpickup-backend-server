package com.project.fastpickup.admin.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreFileUploadDTO {
    // 변수
    private String uuid; // PK
    private String fileName; // 실제파일명
    private boolean img; // 이미지 존재 유무

    // 이미지 파일 경로 가져오기
    public String getLink() {
        if (img) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return "noImage.png";
        }
    }
}
