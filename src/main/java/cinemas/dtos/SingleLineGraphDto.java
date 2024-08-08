package cinemas.dtos;

import java.util.List;

public class SingleLineGraphDto<L, D> {
    private List<L> labels;
    private List<D> datas;

    public SingleLineGraphDto() {
    }

    public SingleLineGraphDto(List<L> labels, List<D> datas) {
        this.labels = labels;
        this.datas = datas;
    }

    public List<L> getLabels() {
        return labels;
    }

    public void setLabels(List<L> labels) {
        this.labels = labels;
    }

    public List<D> getDatas() {
        return datas;
    }

    public void setData(List<D> datas) {
        this.datas = datas;
    }
}
