package data.dto.application;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationPriceValue {
    private double price;
    private LocalDateTime dateTime;
}
