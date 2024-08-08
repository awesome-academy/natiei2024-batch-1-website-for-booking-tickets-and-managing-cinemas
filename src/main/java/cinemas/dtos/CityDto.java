package cinemas.dtos;


import java.util.List;

public class CityDto {
    private Integer id;
    private String name;
    private List<TheaterDto> theaters;
    public CityDto(Integer id, String name, List<TheaterDto> theaters) {
        this.id = id;
        this.name = name;
        this.theaters = theaters;
    }
    public CityDto() {
    }

    public CityDto(String name) {
        this.name = name;
    }

    public List<TheaterDto> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<TheaterDto> theaters) {
        this.theaters = theaters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
