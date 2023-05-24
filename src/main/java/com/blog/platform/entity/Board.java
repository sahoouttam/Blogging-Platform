package com.blog.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Pin> pins;

    @Column(name = "created_on")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdOn;

    @Column(name = "updated_on")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedOn;
}
