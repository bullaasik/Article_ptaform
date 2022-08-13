package data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pair_title")
    private Title pairTitle;

    @Column(name = "price")
    private double price;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Trade(long id, Title pairTitle, double price, LocalDateTime dateTime) {
        this.id = id;
        this.pairTitle = pairTitle;
        this.price = price;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "price=" + price +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Trade trade = (Trade) o;
        return id == trade.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
