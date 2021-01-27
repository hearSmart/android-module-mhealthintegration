package com.hearxgroup.mhealthintegration.Models

import com.hearxgroup.orbit.logic.Const

class ProtocolHearSpeech {

    var uuid: String=""
    var name: String=""
    var deleted: Boolean = false
    var tests: List<TestComposition> = mutableListOf()
    var selected: Boolean = false
    var editable: Boolean = false
    var deletable: Boolean = false
    var created_at: Long = Const.CODE_UNSET_LONG
    var updated_at: Long = Const.CODE_UNSET_LONG

    enum class DIGIT_ACCENT {US_ENGLISH, US_SPANISH, ZA_ENGLISH}
    enum class TEST_TYPE {DIOTIC, ANTIPHASIC, UCL_MCL, WORD_RECOGNITION}
}

data class TestComposition (
        var version: Int = 1,
        var accent: ProtocolHearSpeech.DIGIT_ACCENT = ProtocolHearSpeech.DIGIT_ACCENT.ZA_ENGLISH,
        var type: ProtocolHearSpeech.TEST_TYPE = ProtocolHearSpeech.TEST_TYPE.DIOTIC,
        var structure : Int = 1, //1=non-positional
        var cutoff : Double = -10.0,
        var volume : Int = 80,
        var steps : Int = 23
)