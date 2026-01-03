package com.lucatm11.iplimiter.utils;

import java.util.HashMap;

public class Connection {
    private HashMap<String, Integer> playersByIP;

    public void initializeHashMap() {
        playersByIP = new HashMap<String, Integer>();
    }

    public void initializeConnection(String ipAddress) {
        if (!playersByIP.containsKey(ipAddress)) {
            playersByIP.put(ipAddress, 0);
        }
    }

    public void addIPConnection(String ipAddress) {
        playersByIP.put(ipAddress, getConnections(ipAddress) + 1);
    }

    public void removeIPConnection(String ipAddress) {
        if (getConnections(ipAddress) != 1) {
            playersByIP.put(ipAddress, getConnections(ipAddress) - 1);
        } else {
            playersByIP.remove(ipAddress);
        }
    }

    public Integer getConnections(String ipAddress) {
        return playersByIP.get(ipAddress);
    }
}
