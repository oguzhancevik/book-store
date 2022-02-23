package io.pera.bookstore.service.command;

import io.pera.bookstore.model.dto.CustomerDTO;
import io.pera.bookstore.model.entity.Customer;
import io.pera.bookstore.repository.CustomerRepository;
import io.pera.bookstore.util.BaseMapper;
import io.pera.bookstore.util.Constants.SEQUENCE;
import io.pera.bookstore.util.SequenceUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerCommandService {

    private final BaseMapper mapper;
    private final SequenceUtil sequenceUtil;
    private final CustomerRepository customerRepository;

    public long create(CustomerDTO request) {
        Customer customer = mapper.fromCustomerDTO(request);
        customer.setId(sequenceUtil.generateSequence(SEQUENCE.CUSTOMER));
        customer = customerRepository.save(customer);
        return customer.getId();
    }

}
