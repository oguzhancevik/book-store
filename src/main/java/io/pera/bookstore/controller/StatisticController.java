package io.pera.bookstore.controller;

import io.pera.bookstore.model.response.CustomerStatisticResponse;
import io.pera.bookstore.service.query.StatisticQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

import static io.pera.bookstore.util.Constants.API.STATISTIC_MAPPING;

@RestController
@RequestMapping(STATISTIC_MAPPING)
@AllArgsConstructor
@Validated
public class StatisticController {

    private final StatisticQueryService statisticQueryService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomerStatisticResponse>> getCustomerStatistics(@Valid @NotNull @Positive @PathVariable Long customerId) {
        List<CustomerStatisticResponse> statistics = statisticQueryService.getCustomerStatistics(customerId);
        return ResponseEntity.ok(statistics);
    }


}
