package com.example.besporticast.DTO.Request.AdminRquest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AudioBookDTO {

    @NotNull(message = "Yêu cầu thêm tên sách")
    private String title;

    @NotNull(message = "Yêu cầu thêm tác giả")
    private String author;

    @NotNull(message = "Yêu cầu thêm ngôn ngữ")
    private String language;

    @NotNull(message = "Yêu cầu thêm đánh giá")
    private Float rating;

    @NotNull(message = "Yêu cầu thêm thời lượng")
    private Integer duration; // vẫn là String để dễ gửi từ client

    @NotNull(message = "Yêu cầu thêm mô tả")
    private String description;

    @NotNull(message = "Yêu cầu thêm link audio")
    private String audioUrl;

    @NotNull(message = "Yêu cầu thêm ảnh bìa")
    private String imageUrl;

    @NotNull(message = "Yêu cầu thêm thể loại")
    private String category;

    @NotNull(message = "Yêu cầu thêm lượt nghe")
    private Integer listenCount;
}
