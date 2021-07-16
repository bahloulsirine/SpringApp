package com.springapp.firstapp.dto;

import com.springapp.firstapp.module.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketRequest {

  List<OrderItemRequest> orderItems;
}