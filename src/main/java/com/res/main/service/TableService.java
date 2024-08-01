package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.TablesEntity;
import com.res.main.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public ApiResponse<List<TablesEntity>> getAllTables() {
        List<TablesEntity> tables = tableRepository.findAll();
        if (tables.isEmpty()) {
            return new ApiResponse<>(false, "No tables found", tables);
        }
        return new ApiResponse<>(true, "Tables retrieved successfully", tables);
    }

    public ApiResponse<TablesEntity> createTable(TablesEntity table) {
        TablesEntity savedTable = tableRepository.save(table);
        return new ApiResponse<>(true, "Table created successfully", savedTable);
    }

    public ApiResponse<TablesEntity> getTableById(long id) {
        Optional<TablesEntity> table = tableRepository.findById(id);
        if (table.isPresent()) {
            return new ApiResponse<>(true, "Table retrieved successfully", table.get());
        }
        return new ApiResponse<>(false, "Table not found", null);
    }

    public ApiResponse<TablesEntity> updateTable(long id, TablesEntity updatedTable) {
        if (tableRepository.existsById(id)) {
            updatedTable.setId(id);
            TablesEntity savedTable = tableRepository.save(updatedTable);
            return new ApiResponse<>(true, "Table updated successfully", savedTable);
        }
        return new ApiResponse<>(false, "Table not found", null);
    }

    public ApiResponse<String> deleteTable(long id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
            return new ApiResponse<>(true, "Table deleted successfully", "Table deleted");
        }
        return new ApiResponse<>(false, "Table not found", null);
    }
}
