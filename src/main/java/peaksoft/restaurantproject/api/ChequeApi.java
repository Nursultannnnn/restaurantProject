package peaksoft.restaurantproject.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.cheque.ChequeRequest;
import peaksoft.restaurantproject.dto.cheque.ChequeResponse;
import peaksoft.restaurantproject.service.ChequeService;

import java.util.List;

@RestController
@RequestMapping("/api/cheques")
@RequiredArgsConstructor
public class ChequeApi {

    private final ChequeService chequeService;
    @PreAuthorize("hasRole('WAITER')")
    @PostMapping("/user/{userId}")
    public ChequeResponse saveCheque(@PathVariable Long userId,
                                     @RequestBody @Valid ChequeRequest request) {
        return chequeService.saveCheque(userId, request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ChequeResponse> getAllCheques() {
        return chequeService.getAllCheques();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'WAITER')")
    @GetMapping("/{id}")
    public ChequeResponse getChequeById(@PathVariable Long id) {
        return chequeService.getChequeById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteCheque(@PathVariable Long id) {
        return chequeService.deleteCheque(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}/total")
    public int getTotalAmountByUserId(@PathVariable Long userId) {
        return chequeService.getTotalAmountByUserId(userId);
    }
}