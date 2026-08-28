package io.github.im10furry.gb32960.core.crypto;

import io.github.im10furry.gb32960.core.constant.EncryptionType;

public interface CryptoProvider {

    byte[] decrypt(byte encryptionType, byte[] data);

    byte[] encrypt(byte encryptionType, byte[] data);

    default boolean supports(byte encryptionType) {
        return encryptionType == EncryptionType.NONE;
    }
}
