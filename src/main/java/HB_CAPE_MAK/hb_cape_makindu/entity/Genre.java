package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.NomenclatureInterface;
import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.SluggerInterface;
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
public class Genre implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GenrePrivateView.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.GenrePublicView.class)
    private String name;

    private String slug;

    @OneToMany(mappedBy = "genre")
    @JsonView(JsonViews.GenreFullView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}

