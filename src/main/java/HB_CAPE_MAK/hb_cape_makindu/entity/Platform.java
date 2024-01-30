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

    private String slug;

    @ManyToMany(mappedBy = "platforms")
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}
