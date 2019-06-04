package com.hearxgroup.mhealthintegration;

public class MHealthFunctionHelper {

    private static final String TAG = MHealthFunctionHelper.class.getSimpleName();

    /**
     * Method used to open the headphone manager component
     * @param verification If verification required, will automatically open scanner screen
     * @param returnIntentActionName Intent filter action name so mHealth knows where to return when process complete
     */
    public static void openHeadphoneManager(boolean verification, String returnIntentActionName) {
        //todo
    }

    /**
     * Method used to open the sync manager component
     * The component gives the user options to sync the following:
     * 1. Sync Data - Upload all local data - Uploads any pending facilities, patients or tests
     * 2. Sync Data - Download all facility data
     * 3. Sync Data - Download all patient data
     * 4. Sync Device - Member account, Software Licenses, Software version status, Device Standardisation, Firebase logs,
     * @param syncDevice If syncDevice specified, will automatically run syncDevice
     * @param returnIntentActionName Intent filter action name so mHealth knows where to return when process complete
     */
    public static void openSyncManager(boolean syncDevice, String returnIntentActionName) {
        //todo
    }

    /**
     * Method used to open the update manager component
     * The component allows the  user to see the version status of all the apps and allows for
     * updating and installing of apps
     * @param updateAppIndex If appIndex specified, will automatically attempt to update specified app
     * @param returnIntentActionName Intent filter action name so mHealth knows where to return when process complete
     */
    public static void openUpdateManager(int updateAppIndex, String returnIntentActionName) {
        //todo
    }
}
