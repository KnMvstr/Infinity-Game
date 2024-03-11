package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.NomenclatureInterface;
import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.SluggerInterface;

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
    private Long id;

    private String name;

    private String description;

    private String image;

    private String slug;

    @OneToMany (mappedBy = "classification")
    private List<Game> games = new ArrayList<>();


    @Override
    public String getField() {
        return name;
    }
}
