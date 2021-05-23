package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "with all_empty_tables as (\n" +
            "    select * from reserved_table where id not in (\n" +
            "        select rt.id from reserved_table rt\n" +
            "              left join orders_reserved_tables ort on rt.id = ort.table_id\n" +
            "              left join orders o on ort.order_id = o.id\n" +
            "        where o.end_time >= ?1 and o.start_time <= ?2\n" +
            "          and rt.person_count >= ?3)\n" +
            "    )," +
            "stars as (select rt.*, coalesce(star.average, 0) as average from restaurant rt\n" +
            "    left join (select avg(star) as average, restaurant_id\n" +
            "        from comment group by restaurant_id) star on rt.id = star.restaurant_id) \n" +
            "select * from restaurant rt left join stars on stars.id = rt.id where rt.id in (select restaurant_id from all_empty_tables) " +
            "   and rt.min_price >= ?4 and rt.max_price <= ?5 and lower(rt.name) like lower(concat('%', ?6, '%')) and average >= ?7 " +
            "order by rt.id desc", nativeQuery = true)
    List<Restaurant> searchRestaurants(LocalDateTime startTime, LocalDateTime endTime, Integer personCount, Integer minPrice,
                                       Integer maxPrice, String filter, Integer star);

    @Query(value = "with all_empty_tables as (\n" +
            "    select * from reserved_table where id not in (\n" +
            "        select rt.id from reserved_table rt\n" +
            "              left join orders_reserved_tables ort on rt.id = ort.table_id\n" +
            "              left join orders o on ort.order_id = o.id\n" +
            "        where o.end_time >= ?1 and o.start_time <= ?2\n" +
            "          and rt.person_count >= ?3)\n" +
            "    )," +
            "stars as (select rt.*, coalesce(star.average, 0) as average from restaurant rt\n" +
            "    left join (select avg(star) as average, restaurant_id\n" +
            "        from comment group by restaurant_id) star on rt.id = star.restaurant_id) \n" +
            "select * from restaurant rt left join stars on stars.id = rt.id where rt.id in (select restaurant_id from all_empty_tables) " +
            "   and rt.min_price >= ?4 and rt.max_price <= ?5 and lower(rt.name) like lower(concat('%', ?6, '%')) and average >= ?7  " +
            "order by average desc", nativeQuery = true)
    List<Restaurant> searchRestaurantsAverage(LocalDateTime startTime, LocalDateTime endTime, Integer personCount, Integer minPrice,
                                       Integer maxPrice, String filter, Integer star);
}
