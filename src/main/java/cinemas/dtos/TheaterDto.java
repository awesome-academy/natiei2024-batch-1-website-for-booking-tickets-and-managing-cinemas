package cinemas.dtos;

public class TheaterDto {
    private Integer id;
    private String name;

    public TheaterDto() {
    }

    public TheaterDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
