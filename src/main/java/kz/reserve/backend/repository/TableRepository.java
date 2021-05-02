package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}
