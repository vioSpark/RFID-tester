package com.example.rfidtester.ui.main

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class MyHostApduService : HostApduService() {

    override fun processCommandApdu(commandApdu: ByteArray, extras: Bundle?): ByteArray {
        //TODO: get default AID route to pint into this code
        return ByteArray(0)
    }

    override fun onDeactivated(reason: Int) {
        //TODO: code this as well
    }
}