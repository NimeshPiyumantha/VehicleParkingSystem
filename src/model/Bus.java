package model;

import java.io.IOException;

import static controller.ManagementFromHomeController.pOutputListTM;

public class Bus extends Vehicle {
    final int[] BusSlot = new int[]{14};

    public Bus() {
        super();
    }

    public Bus(String vehicleNumber, String vehicleType, double maxWeight, int noPassengers) {
        super(vehicleNumber, vehicleType, maxWeight, noPassengers);
    }

    //--------------------------------CHECK SLOT----------------------------//
    @Override
    public int park(String vehicleNumber, String VehicleType) throws IOException {
        B1:
        for (int i : BusSlot) {
            if (pOutputListTM.size() == 0) {
                return i;
            } else {
                B2:
                for (int j = 0; j < pOutputListTM.size(); j++) {
                    if (pOutputListTM.get(j).getParkingSlot() == i) {
                        continue B1;
                    } else {
                        if (j != pOutputListTM.size() - 1) {
                            continue B2;
                        } else {
                            return i;
                        }
                    }
                }
            }
        }
        return 0;
    }

}

