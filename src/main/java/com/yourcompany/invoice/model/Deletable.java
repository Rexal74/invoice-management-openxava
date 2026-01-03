package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass @Getter
@Setter
public class Deletable extends Identifiable {
    @Hidden
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean deleted;
}
