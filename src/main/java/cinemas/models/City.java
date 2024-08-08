package cinemas.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "city", orphanRemoval = false)
    private Set<Theater> theaters;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Theater> getTheaters() {
        return theaters;
    }

    public void setTheaters(Set<Theater> theaters) {
        this.theaters = theaters;
    }
}
