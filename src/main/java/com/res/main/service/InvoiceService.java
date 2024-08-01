package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.InvoiceEntity;
import com.res.main.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public ApiResponse<List<InvoiceEntity>> getAllInvoices() {
        List<InvoiceEntity> invoices = invoiceRepository.findAll();
        if (invoices.isEmpty()) {
            return new ApiResponse<>(false, "No invoices found", invoices);
        }
        return new ApiResponse<>(true, "Invoices retrieved successfully", invoices);
    }

    public ApiResponse<InvoiceEntity> createInvoice(InvoiceEntity invoice) {
        InvoiceEntity savedInvoice = invoiceRepository.save(invoice);
        return new ApiResponse<>(true, "Invoice created successfully", savedInvoice);
    }

    public ApiResponse<InvoiceEntity> getInvoiceById(long id) {
        Optional<InvoiceEntity> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            return new ApiResponse<>(true, "Invoice retrieved successfully", invoice.get());
        }
        return new ApiResponse<>(false, "Invoice not found", null);
    }

    public ApiResponse<InvoiceEntity> updateInvoice(long id, InvoiceEntity updatedInvoice) {
        if (invoiceRepository.existsById(id)) {
            updatedInvoice.setId(id);
            InvoiceEntity savedInvoice = invoiceRepository.save(updatedInvoice);
            return new ApiResponse<>(true, "Invoice updated successfully", savedInvoice);
        }
        return new ApiResponse<>(false, "Invoice not found", null);
    }

    public ApiResponse<String> deleteInvoice(long id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return new ApiResponse<>(true, "Invoice deleted successfully", "Invoice deleted");
        }
        return new ApiResponse<>(false, "Invoice not found", null);
    }
}
