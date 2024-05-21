package InfinityGaming.entity;

import InfinityGaming.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue ("GAMER")
public class Gamer extends User {

    @JsonView(JsonViews.GamerPrivateView.class)
    private LocalDate birthAt;

    @OneToMany(mappedBy = "gamer")
    @JsonView(JsonViews.GamerPublicView.class)
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();
}