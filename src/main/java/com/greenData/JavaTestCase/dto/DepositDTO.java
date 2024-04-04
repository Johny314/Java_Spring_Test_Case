package com.greenData.JavaTestCase.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class DepositDTO {
    private Long client_id;
    private Long bank_id;
    private LocalDate date;
    private double percent;
    private int month_num;
}
