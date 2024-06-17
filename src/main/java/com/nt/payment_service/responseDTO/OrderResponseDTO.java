package com.nt.payment_service.responseDTO;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private Integer amount;
    private String amount_paid;
    private String notes;
    private String created_at;
    private String currency;
    private String receipt;
    private String orderId;
    private String attempts;
    private String status;

}
