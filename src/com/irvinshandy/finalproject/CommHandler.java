package com.irvinshandy.finalproject;

import com.rapplogic.xbee.api.*;
import com.rapplogic.xbee.api.zigbee.ZNetExplicitRxResponse;
import com.rapplogic.xbee.api.zigbee.ZNetTxRequest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CommHandler implements Runnable {
    private final static Logger log = Logger.getLogger(CommHandler.class);

    private static XBee xbee = new XBee();

    /**
     * receivedPayload is the data received from the remote XBee
     * receivedPayload is an array of integers
     * receivedPayload[0] is the roll angle of the vessel as read by IMU
     * receivedPayload[1] is the pitch angle of the vessel as read by IMU
     * receivedPayload[2] is the yaw angle of the vessel as read by IMU
     * receivedPayload[3] is the depth measured by the ultrasonic sensor
     * receivedPayload[4] is the output angle for pitch servo as calculated by the control algorithm
     * receivedPayload[5] is the output angle for left roll servo as calculated by the control algorithm
     * receivedPayload[6] is the output angle for right roll as calculated by the control algorithm
     * receivedPayload[7] is the output angle for rudder servo as calculated by the control algorithm
     * receivedPayload[8] is the time required to complete one control cycle
     */

    private static int[] receivedPayload = new int[9];

    /**
     * sentPayload is the data sent to the remote XBee
     * sentPayload is an array of integers
     * sentPayload[0] defines whether automatic or manual control is currently active
     * sentPayload[1] defines automatic depth control gain in respect to proportional band (P Gain)
     * sentPayload[2] defines automatic depth control gain in respect to integral action (I Gain)
     * sentPayload[3] defines automatic depth control gain in respect to derivative action (D Gain)
     * sentPayload[4] defines automatic roll control gain in respect to proportional band (P Gain)
     * sentPayload[5] defines automatic roll control gain in respect to integral action (I Gain)
     * sentPayload[6] defines automatic roll control gain in respect to derivative action (D Gain)
     * sentPayload[7] defines automatic pitch control gain in respect to proportional band (P Gain)
     * sentPayload[8] defines automatic pitch control gain in respect to integral action (I Gain)
     * sentPayload[9] defines automatic pitch control gain in respect to derivative gain (D Gain)
     * sentPayload[10] defines propulsion throttle
     * sentPayload[11] defines servo angles for manual depth control
     * sentPayload[12] defines servo angles for manual roll control
     * sentPayload[13] defines servo angles for manual pitch control
     */

    private int count = 1;
    private int i = 0;
    private static int[] sentPayload = new int[14];
    private static int[] testPayload = new int[14];
    private boolean flag = false;
    private ConfigTuner configTuner;

    public CommHandler(ConfigTuner configTuner) {
        this.configTuner = configTuner;
    }

    public static void setReceivedPayloadValue(int position, int value) {
        receivedPayload[position] = value;
    }

    public static int getReceivedPayloadValue(int position) {
        return receivedPayload[position];
    }

    public static void setSentPayloadValue(int position, int value) {
        sentPayload[position] = value;
    }

    public static int getSentPayloadValue(int position) {
        return sentPayload[position];
    }

    public static void setTestPayloadValue(int position, int value) {
        testPayload[position] = value;
    }

    public void receiveData() throws Exception {
        xbee.open("/dev/tty.usbmodem1411", 9600);

        XBeeAddress64 destination = new XBeeAddress64(0, 0x13, 0xa2, 0, 0x40, 0xdb, 0xe4, 0x8b);

        ZNetTxRequest request = new ZNetTxRequest(destination, sentPayload);

        while (true) {

            try {
                xbee.sendSynchronous(request, 200);
                request.setFrameId(xbee.getNextFrameId());
            } catch (XBeeTimeoutException e) {
                log.warn("request timed out");
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }

            try {
                XBeeResponse response = xbee.getResponse();

                if (response.getApiId() == ApiId.ZNET_EXPLICIT_RX_RESPONSE) {
                    ZNetExplicitRxResponse rx = (ZNetExplicitRxResponse) response;

                    for (int i = 0; i < receivedPayload.length; i++) {
                        setReceivedPayloadValue(i, rx.getData()[i]);
                        log.info("Payload " + i + " is " + getReceivedPayloadValue(i));
                        configTuner.displayVesselCondition();
                    }

                    configTuner.displayVesselCondition();

                    log.info("received explicit packet response " + ((ZNetExplicitRxResponse) response).getData()[0]);
                } else {
                    log.debug("received unexpected packet " + response.toString());
                }
            } catch (Exception e) {
                log.error(e);
            }
        }
    }

    @Override
    public void run() {
        PropertyConfigurator.configure("log4j.properties");

        try {
            receiveData();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
