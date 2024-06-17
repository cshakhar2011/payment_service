package com.nt.payment_service.requestDTO;

import lombok.Data;

@Data
public class OrderRequestDTO {

      private Integer amount;

      private String currency;

      private String receipt;

      private String notes;

}
