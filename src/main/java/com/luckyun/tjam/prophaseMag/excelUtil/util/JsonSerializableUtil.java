package com.luckyun.tjam.prophaseMag.excelUtil.util;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by whs on 2020/6/8.
 */
public class JsonSerializableUtil extends JsonSerializer<Double> {
        private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(decimalFormat.format(value));
    }
}
