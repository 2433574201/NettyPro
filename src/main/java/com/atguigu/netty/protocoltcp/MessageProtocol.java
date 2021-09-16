package com.atguigu.netty.protocoltcp;


//协议包
public class MessageProtocol {
    private int len; //报文长度
    private byte[] content;
    private float point_x;
    private float point_y;
    private float point_z;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public float getPoint_x() {
        return point_x;
    }

    public void setPoint_x(float point_x) {
        this.point_x = point_x;
    }

    public float getPoint_y() {
        return point_y;
    }

    public void setPoint_y(float point_y) {
        this.point_y = point_y;
    }

    public float getPoint_z() {
        return point_z;
    }

    public void setPoint_z(float getPoint_z) {
        this.point_z = getPoint_z;
    }
}
