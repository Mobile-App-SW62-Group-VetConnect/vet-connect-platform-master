package com.org.vetconnect.platform.favorites.infrastructure.persistence.jpa.respositories;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
}
