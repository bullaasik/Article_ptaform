package data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.romanorlov.data_collection_module.dto.application.ApplicationCurrencyPair;
import ru.romanorlov.data_collection_module.service.ApplicationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pair")
public class ApplicationController {
    private ApplicationService service;

    @GetMapping
    public ApplicationCurrencyPair getTheHistoryFromDateByTitle(@RequestParam Map<String, String> parameters) {
        String title = parameters.getOrDefault("title", "Unknown");
        String fromDate = parameters.getOrDefault("fromDate", null);
        String toDate = parameters.getOrDefault("toDate", null);

        if (fromDate != null && toDate != null) {
            return service.getInfoAboutCurrencyPair(title, fromDate, toDate);
        } else if (fromDate != null) {
            return service.getInfoAboutCurrencyPair(title, fromDate);
        } else {
            return service.getInfoAboutCurrencyPair(title);
        }
    }

    @Autowired
    public void setService(ApplicationService service) {
        this.service = service;
    }
}
