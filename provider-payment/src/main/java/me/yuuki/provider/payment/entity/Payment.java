package me.yuuki.provider.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class Payment {
    private String id;
    private String serial;
}
