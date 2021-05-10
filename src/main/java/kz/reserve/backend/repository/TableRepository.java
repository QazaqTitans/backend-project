package kz.reserve.backend.repository;

import kz.reserve.backend.domain.ReservedTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<ReservedTable, Long> {
}
