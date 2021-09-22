package edu.miu.group3.appointment.system.service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CategoryDto {
    private Long id;

    @NonNull
    private String title;
    @NonNull
    private int defaultSessionLength;
}