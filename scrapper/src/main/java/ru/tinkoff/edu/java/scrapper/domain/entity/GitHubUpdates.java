package ru.tinkoff.edu.java.scrapper.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "github_updates", schema = "link_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GitHubUpdates {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "forks_count")
    private Integer forksCount;
    @Column(name = "watchers")
    private Integer watchers;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Link.class)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    private Link link;
}
