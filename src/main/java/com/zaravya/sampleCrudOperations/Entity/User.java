package com.zaravya.sampleCrudOperations.Entity;

import lombok.*;
@Data
@NoArgsConstructor
//@Entity
@AllArgsConstructor
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String address;
    private String email;

}
