package com.example.rfidtester

import android.nfc.tech.MifareClassic
import android.widget.Toast
import androidx.lifecycle.ViewModel
import java.lang.Thread
import kotlin.concurrent.thread

class MifareCommunicatorViewModel : ViewModel() {
    init {
        viewModelScope.launch {}


    }
    //old junk
    /*
     fun test (tag:MifareClassic){
         thread{
             tag.connect()
             for (i in 0 until 16) {
                 tag.authenticateSectorWithKeyA(i, MifareClassic.KEY_DEFAULT)
                 tag.authenticateSectorWithKeyB(i, MifareClassic.KEY_DEFAULT)
             }
             for (i in 0..64) {
                 tag.readBlock(i)
             }
         }
        //Toast.makeText(this, "All sectors authenticated", Toast.LENGTH_SHORT).show()
    }
    */
}