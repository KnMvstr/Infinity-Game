package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.UserPrivateView.class)
    protected Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String firstName;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String name;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String pseudo;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String email;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    private LocalDate birthAt;


    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String pwd;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    private String cellPhoneNumber;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserFullView.class)
    private List<Review> reviews = new ArrayList<>();


}

