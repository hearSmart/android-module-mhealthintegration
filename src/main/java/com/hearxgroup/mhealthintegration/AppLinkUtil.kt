/*
 * Copyright © 2018 - 2020 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle

object AppLinkUtil {

        val IntentActionMHealthLaunch = "com.hearxgroup.mhealthstudio.intent.action.launch"

        fun getLaunchAppIntent(
            packageManager: PackageManager,
            bundle: Bundle?,
            actionName: String): Intent? {
            val newIntent = Intent(actionName)
            newIntent.type = "text/plain"
            val activities = packageManager.queryIntentActivities(newIntent, 0)
            val isIntentSafe = activities.size > 0
            return if (isIntentSafe) {
                if (bundle != null) {
                    newIntent.putExtras(bundle)
                }

                newIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                newIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                newIntent
            } else {
                null
            }
    }
}