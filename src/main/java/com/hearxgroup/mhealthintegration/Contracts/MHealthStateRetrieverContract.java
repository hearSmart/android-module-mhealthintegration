/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.Contracts;

import com.hearxgroup.mhealthintegration.MHealthStateHelper;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class MHealthStateRetrieverContract {

    public interface ContentRetrieverInterface {
        void onRetrieveStateHeadphone(MHealthStateHelper.HEADPHONE_STATE status);
        void onRetrieveStateDevice(MHealthStateHelper.DEVICE_STATE status);
        void onRetrieveStateApp(int appIndex, MHealthStateHelper.APP_STATE appState);
    }
}
