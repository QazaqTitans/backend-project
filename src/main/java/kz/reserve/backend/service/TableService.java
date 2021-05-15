package kz.reserve.backend.service;

import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.domain.Table;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.TableRequest;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.TableResponse;
import kz.reserve.backend.repository.RestaurantRepository;
import kz.reserve.backend.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        List<Table> tableList = tableRepository.findAll();
        return ResponseEntity.ok(new TableResponse(tableList));
    }
    public ResponseEntity<?> addTable(TableRequest tableRequest) {
        try {
            Table table= new Table();
            User user = serviceUtils.getPrincipal();

            table.setRestaurant(user.getRestaurant());
            tableCreator(tableRequest, table);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }public ResponseEntity<?> updateTable(Long tableID, TableRequest tableRequest) {
        try {
            Table table = tableRepository.getOne(tableID);
            tableCreator(tableRequest, table);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    private void tableCreator(TableRequest tableRequest, Table table) {
        Restaurant restaurant = restaurantRepository.getOne(tableRequest.getRestaurant_id());
        table.setPosition(table.getPosition());
        table.setName(tableRequest.getName());
        table.setImage_src(tableRequest.getImage_src());
        table.setReserve_price(tableRequest.getReserve_price());
        table.setIs_for_children(tableRequest.isFor_children());
        table.setIs_tapchan(tableRequest.isTapchan());
        table.setRestaurant(restaurant);


        tableRepository.save(table);
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
