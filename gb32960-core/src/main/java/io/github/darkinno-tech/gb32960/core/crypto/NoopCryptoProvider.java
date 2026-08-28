package io.github.darkinno-tech-tech.gb32960.core.crypto;

public class NoopCryptoProvider implements CryptoProvider {

    @Override
    public byte[] decrypt(byte encryptionType, byte[] data) {
        return data;
    }

    @Override
    public byte[] encrypt(byte encryptionType, byte[] data) {
        return data;
    }
}
