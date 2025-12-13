package org.example.respository;

import org.example.model.Token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TokenRepository {

    private Long tokenId = 0L;
    private Map<Long, Token> tokenMap = new HashMap<>();

    public Token saveToken(Token token) {
        token.setId(tokenId++);
        token.setCreatedAt(new Date());
        token.setModifiedAt(new Date());
        tokenMap.put(token.getId(), token);
        return token;
    }

    public Optional<Token> fetchToken(Long tokenNumber) {
        if (tokenMap.containsKey(tokenNumber)) {
            return Optional.of(tokenMap.get(tokenNumber));
        }
        return  Optional.empty();
    }
}
