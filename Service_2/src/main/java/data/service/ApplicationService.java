package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romanorlov.data_collection_module.dto.application.ApplicationCurrencyPair;
import ru.romanorlov.data_collection_module.dto.application.ApplicationPriceValue;
import ru.romanorlov.data_collection_module.entity.Title;
import ru.romanorlov.data_collection_module.entity.Trade;
import ru.romanorlov.data_collection_module.repository.TitleRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private TitleRepository repository;

    public ApplicationCurrencyPair getInfoAboutCurrencyPair(String title) {
        Title titleEntity = repository.findById(title).orElseThrow();

        return ApplicationCurrencyPair.builder()
                .title(titleEntity.getTitle())
                .values(titleEntity.getTrades()
                        .stream()
                        .map(this::convertFromTradeToPriceValue)
                        .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                        .toArray(ApplicationPriceValue[]::new))
                .build();
    }

    public ApplicationCurrencyPair getInfoAboutCurrencyPair(String title, String fromDateString) {
        ApplicationCurrencyPair wholeHistory = getInfoAboutCurrencyPair(title);
        LocalDateTime fromDate = LocalDateTime.parse(fromDateString, DATE_FORMATTER);

        wholeHistory.setValues(Arrays.stream(wholeHistory.getValues())
                .filter(value -> value.getDateTime().isAfter(fromDate))
                .toArray(ApplicationPriceValue[]::new));

        return wholeHistory;
    }

    public ApplicationCurrencyPair getInfoAboutCurrencyPair(String title, String fromDateString, String toDateString) {
        ApplicationCurrencyPair wholeHistory = getInfoAboutCurrencyPair(title);
        LocalDateTime fromDate = LocalDateTime.parse(fromDateString, DATE_FORMATTER);
        LocalDateTime toDate = LocalDateTime.parse(toDateString, DATE_FORMATTER);

        wholeHistory.setValues(Arrays.stream(wholeHistory.getValues())
                .filter(value -> value.getDateTime().isAfter(fromDate) && value.getDateTime().isBefore(toDate))
                .toArray(ApplicationPriceValue[]::new));

        return wholeHistory;
    }

    private ApplicationPriceValue convertFromTradeToPriceValue(Trade trade) {
        return ApplicationPriceValue.builder()
                .price(trade.getPrice())
                .dateTime(trade.getDateTime())
                .build();
    }

    @Autowired
    public void setRepository(TitleRepository repository) {
        this.repository = repository;
    }
}
