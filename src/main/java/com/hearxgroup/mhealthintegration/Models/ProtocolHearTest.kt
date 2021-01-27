package com.hearxgroup.mhealthintegration.Models

import com.hearxgroup.mhealth.resources.Const.HEARTEST_TEST_TYPE
import com.hearxgroup.mhealth.resources.Const.THRESHOLD_TEST_METHOD
import com.hearxgroup.orbit.logic.Const.CODE_UNSET_LONG

class ProtocolHearTest {
     var version = 1
     var server_id: Long = CODE_UNSET_LONG
     var uuid = ""
     var name = ""
     var deleted = false
     var test_type = HEARTEST_TEST_TYPE.REGULAR // internal
     var params = TestProtocolHearTestParams()
     var occ_health_obj: TestProtocolHearTestOccHealth? = null
     var threshold_test_method = THRESHOLD_TEST_METHOD.SHORTENED_ASCENDING
     var selected = false
     var editable = false //NEW HT FIELD
     var created_at: Long = CODE_UNSET_LONG
     var updated_at: Long = CODE_UNSET_LONG
}

 data class TestProtocolHearTestOccHealth (
         var version :Int = 1,
         var region  : String? = null,//ZA US US/Texas
         var standard  : String? = null,//SANS/NAL/OSHA
         var enforce_baseline_waiting: Boolean? = null,
         var minimum_baseline_intervar :Int = 0 //unix
         )

 class TestProtocolHearTestParams {
     var version = 1
     var freqs_left: List<Int> = emptyList()
     var freqs_right : List<Int> ? = null
     var min_testing_intensity: Int ? = 10
     var max_pretone_wait: Int ? = 4000
     var max_posttone_wait: Int? = 1500
     var self_test: Boolean ?= true
     var display_test_info: Boolean? = false
     var display_test_progress: Boolean? = false
     var masking_noise: Boolean? = false
     var flight_mode: Boolean? = true
     var better_ear_question: Boolean? = true
}