package HB_CAPE_MAK.hb_cape_makindu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moderator extends User {


    @Column(nullable = false)
    private String cellPhoneNumber;

    @OneToMany(mappedBy = "moderator")
    private List<Review> reviews = new ArrayList<>();

    // Si Moderateur a des responsabilités spécifiques, par exemple modérer des avis,
    // vous pouvez ajouter une relation OneToMany ou ManyToMany ici.
}

