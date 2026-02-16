package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.entity.StopList;
import java.util.List;

public interface StopListService {
    StopList saveStopList(Long menuItemId, StopList stopList);
    List<StopList> getAllStopLists();
    StopList getStopListById(Long id);
    StopList updateStopList(Long id, StopList stopList);
    String deleteStopList(Long id);
}