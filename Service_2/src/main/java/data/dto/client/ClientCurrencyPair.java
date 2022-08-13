package data.dto.client;


import lombok.Data;

@Data
public class ClientCurrencyPair {
    private ClientCurrencyPairsTitle title;
    private ClientPriceValue[] priceHistory;
}
