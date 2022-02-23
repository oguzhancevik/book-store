package io.pera.bookstore.service.query;

import io.pera.bookstore.model.entity.Order;
import io.pera.bookstore.model.response.CustomerStatisticResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticQueryService {

    private final MongoTemplate mongoTemplate;

    public List<CustomerStatisticResponse> getCustomerStatistics(Long customerId) {
        MatchOperation customerMatch = Aggregation.match(Criteria.where("customerId").is(customerId));

        Aggregation groupByYearAndMonth = Aggregation.newAggregation(
                customerMatch,
                Aggregation.project("createdDate")
                        .and(DateOperators.Month.monthOf("createdDate")).as("month")
                        .and(DateOperators.Year.yearOf("createdDate")).as("year")
                        .and("price").as("price")
                        .and("itemCount").as("itemCount"),
                Aggregation.group("year", "month")
                        .sum("itemCount").as("totalBookCount")
                        .sum("price").as("totalPurchasedAmount")
                        .count().as("totalOrderCount"),
                Aggregation.project("totalPurchasedAmount", "totalBookCount", "totalOrderCount", "year", "month")
        );

        AggregationResults<CustomerStatisticResponse> aggregationResults =
                mongoTemplate.aggregate(groupByYearAndMonth, Order.class, CustomerStatisticResponse.class);

        return aggregationResults.getMappedResults();
    }

}
