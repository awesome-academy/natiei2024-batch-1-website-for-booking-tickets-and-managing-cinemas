package cinemas.dtos;

public class ScreenCreateFormDto {
    private String name;
    private Integer verticalSize;
    private Integer horizontalSize;
    private Integer theaterId;
    public ScreenCreateFormDto(String name, Integer verticalSize, Integer horizontalSize, Integer theaterId) {
        this.name = name;
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        this.theaterId = theaterId;
    }

    public ScreenCreateFormDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVerticalSize() {
        return verticalSize;
    }

    public void setVerticalSize(Integer verticalSize) {
        this.verticalSize = verticalSize;
    }

    public Integer getHorizontalSize() {
        return horizontalSize;
    }

    public void setHorizontalSize(Integer horizontalSize) {
        this.horizontalSize = horizontalSize;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }
}
