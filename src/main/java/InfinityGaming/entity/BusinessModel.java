package InfinityGaming.entity;

import InfinityGaming.entity.interfaces.NomenclatureInterface;
import InfinityGaming.entity.interfaces.SluggerInterface;
import InfinityGaming.json_views.JsonViews;
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
public class BusinessModel implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.BusinessModelPrivateView.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.BusinessModelPublicView.class)

    private String name;

    @JsonView(JsonViews.BusinessModelPrivateView.class)
    private String slug;

    @JsonView(JsonViews.BusinessModelPublicView.class)
    @OneToMany(mappedBy = "businessModel")
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}

