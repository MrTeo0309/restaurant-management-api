package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.TablesEntity;
import com.res.main.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public ResponseEntity<?> getAllTables() {
        ApiResponse<List<TablesEntity>> response = tableService.getAllTables();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createTable(@RequestBody TablesEntity table) {
        ApiResponse<TablesEntity> response = tableService.createTable(table);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTableById(@PathVariable long id) {
        ApiResponse<TablesEntity> response = tableService.getTableById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTable(@PathVariable long id, @RequestBody TablesEntity updatedTable) {
        ApiResponse<TablesEntity> response = tableService.updateTable(id, updatedTable);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable long id) {
        ApiResponse<String> response = tableService.deleteTable(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
