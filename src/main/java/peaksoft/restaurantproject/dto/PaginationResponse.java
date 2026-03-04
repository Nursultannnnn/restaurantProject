package peaksoft.restaurantproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PaginationResponse<T> {
    private List<T> elements;      // Сами данные (например, категории)
    private int currentPage;       // Текущая страница
    private int totalPages;        // Всего страниц
}