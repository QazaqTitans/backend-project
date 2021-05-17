package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByParentCategoryId(Long category);

    List<Category> findAllByParentCategoryIsNull();

}
