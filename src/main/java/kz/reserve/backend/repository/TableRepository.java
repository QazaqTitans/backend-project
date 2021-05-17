package kz.reserve.backend.repository;

import kz.reserve.backend.domain.ReservedTable;
import kz.reserve.backend.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TableRepository extends JpaRepository<ReservedTable, Long> {

    List<ReservedTable> findAllByRestaurant(Restaurant restaurant);

    @Query(value = "select * from reserved_table where id not in (\n" +
            "    select rt.id from reserved_table rt\n" +
            "        left join orders_reserved_tables ort on rt.id = ort.table_id\n" +
            "        left join orders o on ort.order_id = o.id\n" +
            "    where o.end_time >= ?1 and o.start_time <= ?2\n" +
            "      and rt.person_count >= ?3 and position = ?4 and is_for_children = ?5 and rt.restaurant_id = ?6\n" +
            ") and person_count >= ?3 and position = ?4 and is_for_children = ?5 and restaurant_id = ?6" +
            " order by person_count, random() limit 1", nativeQuery = true)
    ReservedTable findTableByConfigurations(LocalDateTime startTime, LocalDateTime endTime, Integer personCount,
                                                  String position, Boolean isForChildren, Long restaurant);

    @Query(value = "select * from reserved_table where id not in (\n" +
            "    select rt.id from reserved_table rt\n" +
            "        left join orders_reserved_tables ort on rt.id = ort.table_id\n" +
            "        left join orders o on ort.order_id = o.id\n" +
            "    where o.end_time >= ?1 and o.start_time <= ?2\n" +
            "      and rt.person_count >= ?3 and rt.restaurant_id = ?4\n" +
            ") and person_count >= ?3 and restaurant_id = ?4" +
            " order by person_count, random() limit 1", nativeQuery = true)
    ReservedTable findEmptyTable(LocalDateTime startTime, LocalDateTime endTime, Integer personCount, Long restaurant);
}
