package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.UserPrivateView.class)
    protected Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String pseudo;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String email;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String pwd;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserFullView.class)
    private List<Review> reviews = new ArrayList<>();


}

