package com.github.dmhenry.whoami.data.game;

import com.github.dmhenry.whoami.application.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class GameDao {

    // TODO: add proper data store / cache
    private final Map<String, Game> gameCache = new ConcurrentHashMap<>();
    private static AtomicLong id = new AtomicLong();

    public Game getGame(String gameId) {
        return gameCache.get(gameId);
    }

    public boolean save(Game game) {
        return (gameCache.put(game.getId(), game) == null);
    }

    public Game fetch(String id) {
        return gameCache.get(id);
    }

    public List<Game> getGames() {
        return new ArrayList<>(gameCache.values());
    }

    public Long getNextId() {
        return id.incrementAndGet();
    }

}
