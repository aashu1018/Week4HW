package com.Week4HW.Week4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String name;
    private String title;
}
