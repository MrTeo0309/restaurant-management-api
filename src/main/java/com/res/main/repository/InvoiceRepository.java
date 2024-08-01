package com.res.main.repository;

import com.res.main.model.CategoriesEntity;
import com.res.main.model.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
}
