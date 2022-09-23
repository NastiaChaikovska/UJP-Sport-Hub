package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserSaveProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private LocalDateTime updateDateTime;
}
