package io.github.im10furry.gb32960.auth.provider;

import io.github.im10furry.gb32960.auth.api.AuthProvider;
import io.github.im10furry.gb32960.core.model.RawMessage;

public class NoopAuthProvider implements AuthProvider {

    @Override
    public AuthResult authenticate(RawMessage raw) {
        return AuthResult.success();
    }
}
