package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5)
    private String title;

    @NotBlank
    @Size(min = 10)
    private String content;

    private String image;

    @Column(name="user_id")
    private Integer userId;
    @ManyToOne
    @JoinColumn(name="user_id",updatable = false,insertable = false)
    private User user;

    @Column(name="category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name="category_id",updatable = false,insertable = false)
    private Category category;

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp update_at;

    @UpdateTimestamp
    Timestamp create_at;
}
