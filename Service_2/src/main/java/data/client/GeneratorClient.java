package data.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import data.client.ClientCurrencyPair;

@FeignClient(name = "generator-client", url = "${feign.client.config.generator-client.url}")
public interface GeneratorClient {
    @GetMapping(value = "/pair/{pairTitle}")
    ClientCurrencyPair getPairByTitle(@PathVariable String pairTitle);

    @GetMapping(value = "/pair")
    String[] getAvailablePairs();
}
