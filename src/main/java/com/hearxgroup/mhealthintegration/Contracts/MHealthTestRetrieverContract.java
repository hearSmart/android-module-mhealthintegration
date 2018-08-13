package com.hearxgroup.mhealthintegration.Contracts;

import com.hearxgroup.mhealthintegration.Models.Facility;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.Models.PeekAcuityTest;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class MHealthTestRetrieverContract {

    public interface ContentRetrieverInterface {
        void onRetrieveTestHearScreen(HearscreenTest test);
        void onRetrieveTestHearTest(HeartestTest test);
        void onRetrieveTestPeekAcuity(PeekAcuityTest test);
        void onRetrievePatient(Patient patient);
        void onRetrieveFacility(Facility facility);
        void onRetrieveContentError(String errorMessage);
    }
}
