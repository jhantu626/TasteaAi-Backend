package io.app.service;

import io.app.dto.AuthBody;
import io.app.dto.ResponseToken;

public interface AuthService {
    public ResponseToken register(AuthBody authBody);
    public ResponseToken login(AuthBody authBody);
}
