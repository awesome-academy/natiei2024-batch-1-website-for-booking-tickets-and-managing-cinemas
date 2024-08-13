package cinemas.dtos;

import java.util.Date;

public class BookingAmountSummary {
    private Date date;
    private Long totalAmount;

    public BookingAmountSummary(Date date, Long totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
