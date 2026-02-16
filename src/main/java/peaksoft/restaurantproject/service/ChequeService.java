package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.entity.Cheque;
import java.util.List;

public interface ChequeService {
    Cheque saveCheque(Long userId, List<Long> menuItemIds);
    List<Cheque> getAllCheques();
    Cheque getChequeById(Long id);
    String deleteCheque(Long id);
    int getTotalAmountByUserId(Long userId);
}