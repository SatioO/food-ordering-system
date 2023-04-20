package org.food.ordering.system.order.service.domain.mapper;

import org.food.ordering.system.domain.valueobject.CustomerId;
import org.food.ordering.system.domain.valueobject.Money;
import org.food.ordering.system.domain.valueobject.ProductId;
import org.food.ordering.system.domain.valueobject.RestaurantId;
import org.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import org.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import org.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import org.food.ordering.system.order.service.domain.entity.Order;
import org.food.ordering.system.order.service.domain.entity.OrderItem;
import org.food.ordering.system.order.service.domain.entity.Product;
import org.food.ordering.system.order.service.domain.entity.Restaurant;
import org.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getOrderItems().stream()
                        .map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .orderItems(orderItemsToOrderItemEntities(createOrderCommand.getOrderItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<org.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> OrderItem.Builder.builder()
                .product(new Product(new ProductId(orderItem.getProductId())))
                .price(new Money(orderItem.getPrice()))
                .quantity(orderItem.getQuantity())
                .subTotal(new Money(orderItem.getSubTotal()))
                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }
}
