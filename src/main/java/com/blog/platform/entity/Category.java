package com.blog.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@Builder
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> posts;

    @Column(name = "created_on")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdOn;

    @Column(name = "updated_on")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedOn;
}
