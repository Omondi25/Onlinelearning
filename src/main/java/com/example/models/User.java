package com.example.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.management.relation.Role;
import java.util.Set;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String username;
        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @ManyToMany
        private Set<Course> enrolledCourses;

        public void setPassword(String encode) {
        }

        public CharSequence getPassword() {
        }

        public String getUsername() {
            return null;
        }
    }


