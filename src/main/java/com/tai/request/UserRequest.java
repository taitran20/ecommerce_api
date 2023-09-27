package com.tai.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String password;
    private String f_name;
    private String l_name;
}
