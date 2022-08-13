package data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "pair_titles")
public class Title {
    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "pair_info")
    private String pairInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pairTitle", fetch = FetchType.EAGER)
    private Set<Trade> trades;

    public Title(String title, String pairInfo) {
        this.title = title;
        this.pairInfo = pairInfo;
    }

    public void addTrade(Trade trade) {
        if (trades == null) {
            trades = new HashSet<>();
        }
        trades.add(trade);
        trade.setPairTitle(this);
    }

    @Override
    public String toString() {
        return "Title{" +
                "title='" + title + '\'' +
                ", pairInfo='" + pairInfo + '\'' +
                '}';
    }
}
