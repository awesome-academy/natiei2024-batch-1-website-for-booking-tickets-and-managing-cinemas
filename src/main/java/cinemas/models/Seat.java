package cinemas.models;

import cinemas.enums.SeatTypeEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private SeatTypeEnum type;
    private Boolean available = true;
    @Column(name = "vertical_index")
    private Integer verticalIndex;
    @Column(name = "horizontal_index")
    private Integer horizontalIndex;
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

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

    public SeatTypeEnum getType() {
        return type;
    }

    public void setType(SeatTypeEnum type) {
        this.type = type;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getVerticalIndex() {
        return verticalIndex;
    }

    public void setVerticalIndex(Integer verticalIndex) {
        this.verticalIndex = verticalIndex;
    }

    public Integer getHorizontalIndex() {
        return horizontalIndex;
    }

    public void setHorizontalIndex(Integer horizontalIndex) {
        this.horizontalIndex = horizontalIndex;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
