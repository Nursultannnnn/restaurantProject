package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.stopList.StopListRequest;
import peaksoft.restaurantproject.dto.stopList.StopListResponse;

import java.util.List;

public interface StopListService {
    StopListResponse saveStopList(Long menuItemId, StopListRequest request);
    List<StopListResponse> getAllStopLists();
    StopListResponse getStopListById(Long id);
    StopListResponse updateStopList(Long id, StopListRequest request);
    SimpleResponse deleteStopList(Long id);
}