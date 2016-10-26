package com.rapplogic.xbee;

import gnu.io.SerialPortEvent;

public interface RxTxSerialEventListener {
    void handleSerialEvent(SerialPortEvent event);
}
