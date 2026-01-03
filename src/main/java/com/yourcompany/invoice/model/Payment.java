package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.View;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payment")
@View(members="invoice , amount, method, paidDate")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private CommercialDocument invoice;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Size(max = 50)
    @Column(name = "method", length = 50)
    private String method;

    @Column(name = "paid_date")
    private LocalDate paidDate;

}