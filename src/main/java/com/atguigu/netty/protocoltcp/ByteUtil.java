package com.atguigu.netty.protocoltcp;

import java.nio.charset.Charset;

public class ByteUtil
{
    public static byte[] getBytes(short data)
    {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        return bytes;
    }

    public static byte[] getBytes(char data)
    {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data);
        bytes[1] = (byte) (data >> 8);
        return bytes;
    }

    public static byte[] getBytes(int data)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    public static byte[] getBytes(long data)
    {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    public static byte[] getBytes(float data)
    {
        int intBits = Float.floatToIntBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(double data)
    {
        long intBits = Double.doubleToLongBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(String data, String charsetName)
    {
        Charset charset = Charset.forName(charsetName);
        return data.getBytes(charset);
    }

    public static byte[] getBytes(String data)
    {
        return getBytes(data, "GBK");
    }


    public static short getShort(byte[] bytes)
    {
        return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    public static char getChar(byte[] bytes)
    {
        return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    public static int getInt(byte[] bytes)
    {
        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }

    public static long getLong(byte[] bytes)
    {
        return(0xffL & (long)bytes[0]) | (0xff00L & ((long)bytes[1] << 8)) | (0xff0000L & ((long)bytes[2] << 16)) | (0xff000000L & ((long)bytes[3] << 24))
                | (0xff00000000L & ((long)bytes[4] << 32)) | (0xff0000000000L & ((long)bytes[5] << 40)) | (0xff000000000000L & ((long)bytes[6] << 48)) | (0xff00000000000000L & ((long)bytes[7] << 56));
    }

    public static float getFloat(byte[] bytes)
    {
        return Float.intBitsToFloat(getInt(bytes));
    }

    public static double getDouble(byte[] bytes)
    {
        long l = getLong(bytes);
        //System.out.println(l);
        return Double.longBitsToDouble(l);
    }

    public static String getString(byte[] bytes, String charsetName)
    {
        return new String(bytes, Charset.forName(charsetName));
    }

    public static String getString(byte[] bytes)
    {
        return getString(bytes, "GBK");
    }


    public static void main(String[] args)
    {
        short s = 122;
        int i = 122;
        long l = 1222222;

        char c = 'a';

        float f = 122.2255555f;
        double d = 122.22;

        String string = "我是好孩子";
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(c);
        System.out.println(f);
        System.out.println(d);
        System.out.println(string);

        System.out.println("**************");

        System.out.println(getShort(getBytes(s)));
        System.out.println(getInt(getBytes(i)));
        System.out.println(getLong(getBytes(l)));
        System.out.println(getChar(getBytes(c)));
        System.out.println(getFloat(getBytes(f)));
        System.out.println(getDouble(getBytes(d)));
        System.out.println(getString(getBytes(string)));
    }
}
