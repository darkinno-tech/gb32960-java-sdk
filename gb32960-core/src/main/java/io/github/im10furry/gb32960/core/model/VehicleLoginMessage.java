package io.github.im10furry.gb32960.core.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleLoginMessage {

    private LocalDateTime collectTime;
    private int serialNumber;
    private String iccid;
    private int batterySubsystemCount;
    private int batterySubsystemCodeLength;
    private List<String> batterySubsystemCodes = new ArrayList<>();
    private RawMessage raw;

    public VehicleLoginMessage() {}

    public LocalDateTime getCollectTime() { return collectTime; }
    public void setCollectTime(LocalDateTime collectTime) { this.collectTime = collectTime; }

    public int getSerialNumber() { return serialNumber; }
    public void setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; }

    public String getIccid() { return iccid; }
    public void setIccid(String iccid) { this.iccid = iccid; }

    public int getBatterySubsystemCount() { return batterySubsystemCount; }
    public void setBatterySubsystemCount(int batterySubsystemCount) { this.batterySubsystemCount = batterySubsystemCount; }

    public int getBatterySubsystemCodeLength() { return batterySubsystemCodeLength; }
    public void setBatterySubsystemCodeLength(int batterySubsystemCodeLength) { this.batterySubsystemCodeLength = batterySubsystemCodeLength; }

    public List<String> getBatterySubsystemCodes() { return batterySubsystemCodes; }
    public void setBatterySubsystemCodes(List<String> batterySubsystemCodes) {
        this.batterySubsystemCodes = batterySubsystemCodes;
    }

    public RawMessage getRaw() { return raw; }
    public void setRaw(RawMessage raw) { this.raw = raw; }

    @Override
    public String toString() {
        return "VehicleLoginMessage{" +
                "vin='" + (raw != null ? raw.getVin() : "null") + '\'' +
                ", time=" + collectTime +
                ", sn=" + serialNumber +
                ", iccid='" + iccid + '\'' +
                '}';
    }
}
