package com.miltron.miltron.utils;

import com.miltron.miltron.model.RocketTelemetryModel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public final class RawDataToTelemetryModel {

    private RawDataToTelemetryModel(){}

    public static RocketTelemetryModel getModel(byte[] buf){
        RocketTelemetryModel model = new RocketTelemetryModel();

        ByteBuffer bb = ByteBuffer.allocate(buf.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put(buf);
        bb.flip();

        model.setPackageStartByte(buf[0]);
        model.setRocketId(new String(Arrays.copyOfRange(buf, 1, 11)));
        model.setPackageNumber(buf[11]);
        model.setPackageSize(buf[12]);
        model.setAltitude(bigEndianToFloat(Arrays.copyOfRange(buf, 13, 17)));
        model.setSpeed(bigEndianToFloat(Arrays.copyOfRange(buf, 17, 21)));
        model.setAcceleration(bigEndianToFloat(Arrays.copyOfRange(buf, 21, 25)));
        model.setThrust(bigEndianToFloat(Arrays.copyOfRange(buf, 25, 29)));
        model.setTemprature(bigEndianToFloat(Arrays.copyOfRange(buf, 29, 33)));

        byte[] footer1 = Arrays.copyOfRange(buf, 33, 35);
        short foot = (short) (footer1[0] << 8 | footer1[1]);
        model.setFooter(foot);

        return model;
    }

    private static float bigEndianToFloat(byte[] data){
        return ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat();
    }

}
