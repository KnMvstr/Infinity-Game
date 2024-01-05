package HB_CAPE_MAK.hb_cape_makindu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player extends User{

    @Column(nullable = false)
    private LocalDateTime birthAt;

    @OneToMany(mappedBy = "player")
    private List<Review> reviews = new ArrayList<>();
}
