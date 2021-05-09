package kz.reserve.backend.controller;

import kz.reserve.backend.payload.request.TableRequest;
import kz.reserve.backend.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/table")
@PreAuthorize("hasAuthority('restaurantAdmin')")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping()
    public ResponseEntity<?> getTables(){return tableService.getTables();}

    @PostMapping()
    public ResponseEntity<?> addTable(@Valid @RequestBody TableRequest tableRequest) {
        return tableService.addTable(tableRequest);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTable(@Valid @Min(1) @PathVariable Long id, @Valid @RequestBody TableRequest tableRequest) {
        return tableService.updateTable(id, tableRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTable(@Valid @Min(1) @PathVariable Long id) {
        return tableService.deleteTable(id);
    }

}
