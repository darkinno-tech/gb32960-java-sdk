package io.github.darkinno.gb32960.transport.handler;

import io.github.darkinno.gb32960.auth.provider.NoopAuthProvider;
import io.github.darkinno.gb32960.callback.dispatcher.CallbackDispatcher;
import io.github.darkinno.gb32960.core.codec.MessageDecoder;
import io.github.darkinno.gb32960.core.codec.MessageEncoder;
import io.github.darkinno.gb32960.core.constant.CommandFlag;
import io.github.darkinno.gb32960.core.constant.EncryptionType;
import io.github.darkinno.gb32960.core.constant.ResponseFlag;
import io.github.darkinno.gb32960.core.crypto.AesCryptoProvider;
import io.github.darkinno.gb32960.core.crypto.NoopCryptoProvider;
import io.github.darkinno.gb32960.core.model.RawMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageHandlerTest {

    private static final String VIN = "LSVAM40E7GA000001";

    @Test
    void shouldReturnErrorInsteadOfSuccessWhenTypedDecodingFails() {
        EmbeddedChannel channel = newChannel(new NoopCryptoProvider());
        try {
            RawMessage malformedLogin = rawCommand(CommandFlag.VEHICLE_LOGIN, EncryptionType.NONE, null);

            channel.writeInbound(malformedLogin);

            RawMessage response = readResponse(channel);
            assertEquals(ResponseFlag.ERROR, response.getResponseFlag());
        } finally {
            channel.finishAndReleaseAll();
        }
    }

    @Test
    void shouldEncryptTimingResponseAfterValidation() {
        AesCryptoProvider cryptoProvider = new AesCryptoProvider(new byte[16]);
        EmbeddedChannel channel = newChannel(cryptoProvider);
        try {
            channel.writeInbound(rawCommand(CommandFlag.TERMINAL_TIMING, EncryptionType.AES128, null));

            RawMessage response = readResponse(channel);
            assertEquals(ResponseFlag.SUCCESS, response.getResponseFlag());
            assertEquals(EncryptionType.AES128, response.getEncryptionType());
            assertEquals(6, cryptoProvider.decrypt(EncryptionType.AES128, response.getDataUnit()).length);
        } finally {
            channel.finishAndReleaseAll();
        }
    }

    private static EmbeddedChannel newChannel(io.github.darkinno.gb32960.core.crypto.CryptoProvider cryptoProvider) {
        return new EmbeddedChannel(new MessageHandler(new NoopAuthProvider(), cryptoProvider,
                new CallbackDispatcher(), new ConcurrentHashMap<>(), new LongAdder(), new LongAdder()));
    }

    private static RawMessage rawCommand(byte commandFlag, byte encryptionType, byte[] dataUnit) {
        return RawMessage.builder()
                .commandFlag(commandFlag)
                .responseFlag(ResponseFlag.COMMAND)
                .vin(VIN)
                .encryptionType(encryptionType)
                .dataLength(dataUnit == null ? 0 : dataUnit.length)
                .dataUnit(dataUnit)
                .build();
    }

    private static RawMessage readResponse(EmbeddedChannel channel) {
        ByteBuf responseBuffer = channel.readOutbound();
        assertNotNull(responseBuffer);
        try {
            return MessageDecoder.decodeRaw(ByteBufUtil.getBytes(responseBuffer));
        } finally {
            responseBuffer.release();
        }
    }
}
