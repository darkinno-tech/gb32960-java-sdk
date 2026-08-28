package io.github.im10furry.gb32960.core.model;

import java.util.ArrayList;
import java.util.List;

public class BatteryVoltageData {

    public static class Subsystem {
        private int subsystemNumber;
        private double totalVoltage;
        private double totalCurrent;
        private int totalCellCount;
        private int frameStartCellIndex;
        private int frameCellCount;
        private List<Double> cellVoltages;

        public Subsystem() {
            this.cellVoltages = new ArrayList<>();
        }

        public int getSubsystemNumber() { return subsystemNumber; }
        public void setSubsystemNumber(int subsystemNumber) { this.subsystemNumber = subsystemNumber; }

        public double getTotalVoltage() { return totalVoltage; }
        public void setTotalVoltage(double totalVoltage) { this.totalVoltage = totalVoltage; }

        public double getTotalCurrent() { return totalCurrent; }
        public void setTotalCurrent(double totalCurrent) { this.totalCurrent = totalCurrent; }

        public int getTotalCellCount() { return totalCellCount; }
        public void setTotalCellCount(int totalCellCount) { this.totalCellCount = totalCellCount; }

        public int getFrameStartCellIndex() { return frameStartCellIndex; }
        public void setFrameStartCellIndex(int frameStartCellIndex) { this.frameStartCellIndex = frameStartCellIndex; }

        public int getFrameCellCount() { return frameCellCount; }
        public void setFrameCellCount(int frameCellCount) { this.frameCellCount = frameCellCount; }

        /**
         * @deprecated Since 1.1.0, use {@link #getFrameCellCount()}.
         */
        @Deprecated
        public int getCellCount() { return frameCellCount; }

        /**
         * @deprecated Since 1.1.0, use {@link #setFrameCellCount(int)}.
         */
        @Deprecated
        public void setCellCount(int cellCount) { this.frameCellCount = cellCount; }

        public List<Double> getCellVoltages() { return cellVoltages; }
        public void setCellVoltages(List<Double> cellVoltages) { this.cellVoltages = cellVoltages; }

        @Override
        public String toString() {
            return "Subsystem{#" + subsystemNumber + ", voltage=" + totalVoltage
                    + "V, frameCells=" + frameCellCount + "}";
        }
    }

    private int subsystemCount;
    private List<Subsystem> subsystems;

    public BatteryVoltageData() {
        this.subsystems = new ArrayList<>();
    }

    public int getSubsystemCount() { return subsystemCount; }
    public void setSubsystemCount(int subsystemCount) { this.subsystemCount = subsystemCount; }

    public List<Subsystem> getSubsystems() { return subsystems; }
    public void setSubsystems(List<Subsystem> subsystems) { this.subsystems = subsystems; }

    @Override
    public String toString() {
        return "BatteryVoltageData{subsystems=" + subsystemCount + "}";
    }
}
