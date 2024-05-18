package com.bamyanggang.persistence.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.nio.ByteBuffer;
import java.util.UUID;

@Converter
public class UUIDBinaryConverter implements AttributeConverter<UUID, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(UUID attribute) {
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(attribute.getMostSignificantBits());
        bb.putLong(attribute.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public UUID convertToEntityAttribute(byte[] dbData) {
        ByteBuffer bb = ByteBuffer.wrap(dbData);
        long high = bb.getLong();
        long low = bb.getLong();
        return new UUID(high, low);
    }
}
