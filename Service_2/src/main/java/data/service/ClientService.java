package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.romanorlov.data_collection_module.client.GeneratorClient;
import ru.romanorlov.data_collection_module.dto.client.ClientCurrencyPair;
import ru.romanorlov.data_collection_module.dto.client.ClientPriceValue;
import ru.romanorlov.data_collection_module.entity.Trade;
import ru.romanorlov.data_collection_module.repository.TitleRepository;

import java.util.Arrays;

@Service
public class ClientService {
    private GeneratorClient client;
    private TitleRepository titleRepository;

    /*@Scheduled(fixedDelay = 10000)*/
    public void getInfoAboutTrades() {
        titleRepository.findAll()
                .forEach(title -> {
                    ClientCurrencyPair pair = client.getPairByTitle(title.getTitle());
                    ClientPriceValue[] prices = pair.getPriceHistory();

                    Arrays.stream(prices)
                            .map(this::convertInfoFromDtoToEntity)
                            .forEach(title::addTrade);

                    titleRepository.save(title);
                });
    }

    private Trade convertInfoFromDtoToEntity(ClientPriceValue price){
        return Trade.builder()
                .id(price.getId())
                .price(price.getPrice())
                .dateTime(price.getDate())
                .build();
    }

    @Autowired
    public void setClient(GeneratorClient client) {
        this.client = client;
    }

    @Autowired
    public void setTitleRepository(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }
}
