package org.food.ordering.system.order.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import org.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import org.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import org.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import org.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Slf4j
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreateCommandHandler createCommandHandler;
    private final OrderTrackCommandHandler trackCommandHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler, OrderTrackCommandHandler orderTrackCommandHandler) {
        this.createCommandHandler = orderCreateCommandHandler;
        this.trackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return createCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return trackCommandHandler.trackOrder(trackOrderQuery);
    }
}
