package com.org.vetconnect.platform.favorites.domain.model.aggregates;

import com.org.vetconnect.platform.favorites.domain.model.commands.CreateFavoriteCommand;
import com.org.vetconnect.platform.favorites.domain.model.valueobjects.IdFavorite;
import com.org.vetconnect.platform.favorites.domain.model.valueobjects.UserId;
import com.org.vetconnect.platform.favorites.domain.model.valueobjects.VeterinaryId;
import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Favorite extends AuditableAbstractAggregateRoot<Favorite> {
    @Embedded
    private IdFavorite idFavorite;

    @Embedded
    private UserId userId;

    @Embedded
    private VeterinaryId veterinaryId;

    public Favorite() {
    }

    public Favorite(IdFavorite idFavorite, UserId userId, VeterinaryId veterinaryId) {
        this.idFavorite = idFavorite;
        this.userId = userId;
        this.veterinaryId = veterinaryId;
    }

    public Favorite(CreateFavoriteCommand command) {
        this.idFavorite = new IdFavorite(command.idFavorite());
        this.userId = new UserId(command.userId());
        this.veterinaryId = new VeterinaryId(command.veterinaryId());
    }

}
