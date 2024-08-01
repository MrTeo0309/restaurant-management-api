package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.ShiftsEntity;
import com.res.main.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public ApiResponse<List<ShiftsEntity>> getAllShifts() {
        List<ShiftsEntity> shifts = shiftRepository.findAll();
        if (shifts.isEmpty()) {
            return new ApiResponse<>(false, "No shifts found", shifts);
        }
        return new ApiResponse<>(true, "Shifts retrieved successfully", shifts);
    }

    public ApiResponse<ShiftsEntity> createShift(ShiftsEntity shift) {
        ShiftsEntity savedShift = shiftRepository.save(shift);
        return new ApiResponse<>(true, "Shift created successfully", savedShift);
    }

    public ApiResponse<ShiftsEntity> getShiftById(long id) {
        Optional<ShiftsEntity> shift = shiftRepository.findById(id);
        if (shift.isPresent()) {
            return new ApiResponse<>(true, "Shift retrieved successfully", shift.get());
        }
        return new ApiResponse<>(false, "Shift not found", null);
    }

    public ApiResponse<ShiftsEntity> updateShift(long id, ShiftsEntity updatedShift) {
        if (shiftRepository.existsById(id)) {
            updatedShift.setId(id);
            ShiftsEntity savedShift = shiftRepository.save(updatedShift);
            return new ApiResponse<>(true, "Shift updated successfully", savedShift);
        }
        return new ApiResponse<>(false, "Shift not found", null);
    }

    public ApiResponse<String> deleteShift(long id) {
        if (shiftRepository.existsById(id)) {
            shiftRepository.deleteById(id);
            return new ApiResponse<>(true, "Shift deleted successfully", "Shift deleted");
        }
        return new ApiResponse<>(false, "Shift not found", null);
    }
}
