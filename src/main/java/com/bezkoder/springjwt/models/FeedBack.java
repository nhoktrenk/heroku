package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "feedbacks")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5)
    private String title;

    @NotBlank
    @Size(min = 10)
    private String content;

    @JsonIgnore

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;
    @Column(name = "user_id")
    private Long user_id;

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp update_at;

    @UpdateTimestamp
    Timestamp create_at;
}
