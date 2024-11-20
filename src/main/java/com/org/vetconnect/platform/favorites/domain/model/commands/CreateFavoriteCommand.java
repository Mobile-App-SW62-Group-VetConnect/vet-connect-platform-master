package com.org.vetconnect.platform.favorites.domain.model.commands;

import java.util.Date;

public record CreateFavoriteCommand(Long userId, Long veterinaryId){}
