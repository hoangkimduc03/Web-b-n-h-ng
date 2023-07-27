package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity @Table(name="Accounts")
public class Account implements Serializable {
    @Id
    String username;
    String password;
    String fullname;
    String email;
    String roles;
}
