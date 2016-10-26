/**
 * Copyright (c) 2008 Andrew Rapp. All rights reserved.
 * <p>
 * This file is part of XBee-API.
 * <p>
 * XBee-API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * XBee-API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with XBee-API.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.rapplogic.xbee.api;

import java.io.IOException;
import java.util.List;

/**
 * XBee interface
 *
 * @author andrew
 *
 */
public interface IXBee {
    void open(String port, int baudRate) throws XBeeException;

    void addPacketListener(PacketListener packetListener);

    void removePacketListener(PacketListener packetListener);

    void sendPacket(XBeePacket packet) throws IOException;

    void sendPacket(int[] packet) throws IOException;

    void sendAsynchronous(XBeeRequest xbeeRequest) throws XBeeException;

    XBeeResponse sendSynchronous(final XBeeRequest xbeeRequest, int timeout) throws XBeeException;

    XBeeResponse getResponse() throws XBeeException;

    XBeeResponse getResponse(int timeout) throws XBeeException;

    void close();

    int getCurrentFrameId();

    int getNextFrameId();

    void updateFrameId(int val);

    boolean isConnected();

    void clearResponseQueue();

    List<? extends XBeeResponse> collectResponses(int wait, CollectTerminator terminator) throws XBeeException;
}