package se.learning.home.androidclient.controller;

import DTO.Device;
import DTO.Devices;
import DTO.Schedule;
import DTO.ServerData;
import se.learning.home.androidclient.model.ConnectionToServer;

/**
 * Controller takes requests from View to appropriate object
 * and return values from this object to View
 * In this case we don't follow complete MVC pattern - se.learning.home.androidclient.view will have some logic
 */
public final class Controller {
    private final ConnectionToServer server = ConnectionToServer.getInstance();

    /**
     * This method tells ConnectionToServer object to establish connection
     * @param serverData - data needed to establish connection with server
     */
    public void connectToServer(ServerData serverData){
        server.setServerData(serverData);
        new Thread(server).start();
    }

    /**
     * This method returns status of connection to server
     * @return boolean value true for connection established
     * of false if not or not status could be retrieved
     */
    public boolean isConnectedToServer(){
        try{
            return server.isConnected();
        }catch (NullPointerException ex){
            return false;
        }
    }

    /**
     * @return list of the devices from server
     */
    public Devices getListOfDevices(){
        return server.requestDeviceList();
    }

    /**
     * Request ConnectionToServer object to request server to change status of
     * a device that is connected to server
     * @param deviceID - device identifier
     */
    public void switchDeviceOnServer(int deviceID){
        server.switchDevice(deviceID);
    }

    /**
     * Requests server to add new device
     * @param device - DTO.Device contains new device data
     */
    public void addingDeviceToServer(Device device){
        server.addNewDevice(device);
    }

    public Schedule getScheduleListFromServer (){ return server.requestSchedule();}
}