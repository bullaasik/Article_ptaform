package data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanorlov.data_collection_module.entity.Title;

public interface TitleRepository extends JpaRepository<Title, String> {
}
