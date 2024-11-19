package com.org.vetconnect.platform.favorites.domain.model.commands;

import java.util.Date;

public record CreateFavoriteCommand(String idFavorite, String userId, String veterinaryId, Date createAt) {
}
