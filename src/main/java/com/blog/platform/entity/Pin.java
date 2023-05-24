package com.blog.platform.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@Entity
@Builder
@Table(name = "pins")
@NoArgsConstructor
@AllArgsConstructor
public class Pin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;
}
