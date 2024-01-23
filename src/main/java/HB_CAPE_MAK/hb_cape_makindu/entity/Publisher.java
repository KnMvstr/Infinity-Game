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
public class Publisher implements
        SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.PublisherPrivateView.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.PublisherPublicView.class)
    private String name;

    private String slug;

    @OneToMany(mappedBy = "publisher")
    @JsonView(JsonViews.PublisherFullView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}
