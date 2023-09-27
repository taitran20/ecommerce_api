package com.tai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Entity
/*@Data
AllArgsConstructor
NoArgsConstructor
@Table(name = "paymentinfo")*/
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentInformation {
    private String cardholderName;
    private String cardNumber;
    private LocalDate expirationDate;
    private  String cvv;
}
