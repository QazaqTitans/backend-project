package kz.reserve.backend.service;

import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.domain.ReservedTable;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.TableRequest;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.TableResponse;
import kz.reserve.backend.repository.RestaurantRepository;
import kz.reserve.backend.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TableService {
    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableRepository tableRepository;

    public ResponseEntity<?> getTables() {
        User user = serviceUtils.getPrincipal();
        List<ReservedTable> reservedTableList = tableRepository.findAllByRestaurant(user.getRestaurant());
        return ResponseEntity.ok(new TableResponse(reservedTableList));
    }

    public ResponseEntity<?> addTable(TableRequest tableRequest, MultipartFile file) {
        try {
            ReservedTable reservedTable = new ReservedTable();
            User user = serviceUtils.getPrincipal();

            reservedTable.setRestaurant(user.getRestaurant());
            reservedTable.setImageSrc(serviceUtils.saveUploadedFile(file));
            tableCreator(tableRequest, reservedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> updateTable(Long tableID, TableRequest tableRequest) {
        try {
            ReservedTable reservedTable = tableRepository.getOne(tableID);
            tableCreator(tableRequest, reservedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    private void tableCreator(TableRequest tableRequest, ReservedTable reservedTable) {
//        Restaurant restaurant = restaurantRepository.getOne(tableRequest.getRestaurantId());

        reservedTable.setPosition(tableRequest.getPosition());
        reservedTable.setName(tableRequest.getName());
        reservedTable.setReservePrice(tableRequest.getReservePrice());
        reservedTable.setForChildren(tableRequest.getForChildren());
        reservedTable.setPersonCount(tableRequest.getPersonCount());
//        reservedTable.setTapchan(tableRequest.isTapchan());
//        reservedTable.setRestaurant(restaurant);


        tableRepository.save(reservedTable);
    }

    public ResponseEntity<?> deleteTable(Long tableID) {
        try {
            tableRepository.deleteById(tableID);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

}
