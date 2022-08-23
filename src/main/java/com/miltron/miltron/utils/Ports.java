package com.miltron.miltron.utils;

public enum Ports {
    PORT_1(4000),
    PORT_2(4001),
    PORT_3(4002),
    PORT_4(4003),
    PORT_5(4004),
    PORT_6(4005),
    PORT_7(4006),
    PORT_8(4007),
    PORT_9(4008),
    PORT_10(4009);

    int port;

    Ports(int port) {
        this.port =port;
    }

    public int getPort(){
        return port;
    }
}
