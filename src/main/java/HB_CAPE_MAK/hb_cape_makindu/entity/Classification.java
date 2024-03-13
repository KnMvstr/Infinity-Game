package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.NomenclatureInterface;
import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.SluggerInterface;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Classification implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.ClassificationPrivateView.class)
    private Long id;

    @JsonView(JsonViews.ClassificationPublicView.class)
    private String name;

    @JsonView(JsonViews.ClassificationFullView.class)
    private String description;

    @JsonView(JsonViews.ClassificationFullView.class)
    private String image;

    @JsonView(JsonViews.ClassificationPrivateView.class)
    private String slug;

    @OneToMany (mappedBy = "classification")
    @JsonView(JsonViews.ClassificationPublicView.class)
    @JsonBackReference
    private List<Game> games = new ArrayList<>();


    @Override
    public String getField() {
        return name;
    }
}