//-----------------------------------------------------------------
// SmartDeviceIndex.java
// Group 2
// Description: This class serves as an index utility for Smart Device types. It provides methods to retrieve device types based on their number representation and vice versa.
// Created By: Francisco
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
//-----------------------------------------------------------------

package ClientServer;

public class SmartDeviceIndex {

    public static String getDeviceType(int deviceTypeNumber) {
        switch (deviceTypeNumber) {
            case 0:
                return "Smart Light";
            case 1:
                return "Smart Lock";
            case 2:
                return "Smart Thermostat";
            case 3:
                return "Smart Coffee Machine";
            case 4:
                return "Smart Garage Door";
            case 5:
                return "Smart Smoke Detector";
            default:
                return "Error: Device type not found";
        }
    }

    public static int getDeviceTypeNumber(String deviceType) {
        switch (deviceType) {
            case "Smart Light":
                return 0;
            case "Smart Lock":
                return 1;
            case "Smart Thermostat":
                return 2;
            case "Smart Coffee Machine":
                return 3;
            case "Smart Garage Door":
                return 4;
            case "Smart Smoke Detector":
                return 5;
            default:
                return -1;
        }
    }
}
