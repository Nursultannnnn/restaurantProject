package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.cheque.ChequeRequest;
import peaksoft.restaurantproject.dto.cheque.ChequeResponse;

import java.util.List;

public interface ChequeService {
    ChequeResponse saveCheque(Long userId, ChequeRequest request);
    List<ChequeResponse> getAllCheques();
    ChequeResponse getChequeById(Long id);
    SimpleResponse deleteCheque(Long id);
    int getTotalAmountByUserId(Long userId);
}