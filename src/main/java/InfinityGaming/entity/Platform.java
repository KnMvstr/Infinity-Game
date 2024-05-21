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
public class Platform implements
        SluggerInterface,
        NomenclatureInterface
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.PlatformPrivateView.class)

    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.PlatformPublicView.class)
    private String name;

    @JsonView(JsonViews.PlatformPublicView.class)
    private String slug;

    @ManyToMany(mappedBy = "platforms")
    @JsonView(JsonViews.PlatformPublicView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}
