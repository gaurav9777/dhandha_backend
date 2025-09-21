package com.pg.backend.CsvUsingThread;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CsvRequestDto {
    private Integer id;
    private String name;
    private Integer age;
}