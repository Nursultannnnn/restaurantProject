package peaksoft.restaurantproject.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/user/{userId}")
    public ChequeResponse saveCheque(@PathVariable Long userId,
                                     @RequestBody @Valid ChequeRequest request) {
        return chequeService.saveCheque(userId, request);
    }

    @GetMapping
    public List<ChequeResponse> getAllCheques() {
        return chequeService.getAllCheques();
    }

    @GetMapping("/{id}")
    public ChequeResponse getChequeById(@PathVariable Long id) {
        return chequeService.getChequeById(id);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteCheque(@PathVariable Long id) {
        return chequeService.deleteCheque(id);
    }

    @GetMapping("/user/{userId}/total")
    public int getTotalAmountByUserId(@PathVariable Long userId) {
        return chequeService.getTotalAmountByUserId(userId);
    }
}