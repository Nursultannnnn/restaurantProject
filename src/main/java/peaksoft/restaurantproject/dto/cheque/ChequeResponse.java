package peaksoft.restaurantproject.dto.cheque;

import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ChequeResponse(
        Long id,
        String waiterName,
        List<String> items,
        int priceAverage,
        int serviceFeePercent,
        LocalDate createdAt        )
{}