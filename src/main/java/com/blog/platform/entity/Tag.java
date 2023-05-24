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
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    private List<Post> posts;

    @Column(name = "created_on")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdOn;

    @Column(name = "updated_on")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedOn;
}
