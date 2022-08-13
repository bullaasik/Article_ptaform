package data.dto.client;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientPriceValue {
    private long id;
    private double price;
    private LocalDateTime date;
}
