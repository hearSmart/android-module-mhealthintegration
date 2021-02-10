/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.Contracts;

import com.hearxgroup.mhealthintegration.Models.Facility;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.HearriskTest;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HearspeechTest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.Models.VulaVisionTest;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class MHealthTestRetrieverContract {

    public interface ContentRetrieverInterface {
        void onRetrieveTestHearScreen(HearscreenTest test);
        void onRetrieveTestHearTest(HeartestTest test);
        void onRetrieveTestVulaVision(VulaVisionTest test);
        void onRetrieveTestHearSpeech(HearspeechTest test);
        void onRetrieveTestHearRisk(HearriskTest test);
        void onRetrievePatient(Patient patient);
        void onRetrieveFacility(Facility facility);
        void onRetrieveContentError(String errorMessage);
    }
}
