package com.pafassigment.fitplus.response_dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApiResponse {
    private boolean success;
    private String message;

}
