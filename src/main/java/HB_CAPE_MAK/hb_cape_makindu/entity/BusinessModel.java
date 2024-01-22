package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.BusinessModelPrivateView.class)
    private Long id;

    @JsonView(JsonViews.BusinessModelPublicView.class)
    @Column(nullable = false, unique = true)
    private String name;

    @JsonView(JsonViews.BusinessModelPrivateView.class)
    private String slug;

    @JsonView(JsonViews.BusinessModelFullView.class)
    @OneToMany(mappedBy = "businessModel")
    private List<Game> games = new ArrayList<>();

}

