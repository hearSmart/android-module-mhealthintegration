package com.hearxgroup.mhealthintegration.Models

import com.hearxgroup.mhealth.resources.Const

class ProtocolHearScreen {

    var version : Int = 1
    var server_id : Long = com.hearxgroup.orbit.logic.Const.CODE_UNSET_LONG
    var uuid: String=""
    var name: String=""
    var deleted: Boolean = false
    var test_type: Const.HEARSCREEN_TEST_TYPE = Const.HEARSCREEN_TEST_TYPE.REGULAR // internal
    var params: TestProtocolHearScreenParams = TestProtocolHearScreenParams()
    var selected: Boolean = false
    var editable: Boolean = false //NEW HT FIELD
    var created_at: Long = com.hearxgroup.orbit.logic.Const.CODE_UNSET_LONG
    var updated_at: Long = com.hearxgroup.orbit.logic.Const.CODE_UNSET_LONG
}

data class TestProtocolHearScreenParams(
        val version: Int = 1,
        var freqs: List<Int> = emptyList(),
        var screening_intensities_adult: List<Int> = emptyList(),
        var screening_intensities_child: List<Int> = emptyList(),
        var severity: Boolean = false,
        var display_test_info: Boolean = false,
        var display_test_progress: Boolean = false,
        var short_rescreen: Boolean = false,
        var refer_freq_count: Int = 1,
        var better_ear_question: Boolean = true,
        var self_test: Boolean = false //Provisioning for future use
)