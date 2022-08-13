package data.dto.application;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationCurrencyPair {
    private String title;
    private ApplicationPriceValue[] values;
}
