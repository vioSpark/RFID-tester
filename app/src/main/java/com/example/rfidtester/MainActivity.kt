package com.example.rfidtester

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.example.rfidtester.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "OnCreate logging"
        Log.d(TAG, "Process Started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            Toast.makeText(this, "Testing toast", Toast.LENGTH_SHORT).show()
        }
        this.onNewIntent(this.intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val TAG = "Intent_sorter"
        Log.d(TAG, "RFID intent started")
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                // Process the messages array.
                Log.d(TAG, "NDEF")
                Log.d(TAG, messages[0].toString())
                Toast.makeText(this, messages[0].toString(), Toast.LENGTH_SHORT).show()
            }
        }

        //TODO: get tags info
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {
            Log.d(TAG, "TECH")
            //var tagFromIntent: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            Toast.makeText(this, "RFID_TECH found", Toast.LENGTH_SHORT).show()
            val tag = MifareClassic.get(intent.getParcelableExtra(NfcAdapter.EXTRA_TAG))
            /*thread(start = true) {
                Log.d("THREAD", "${Thread.currentThread()} has run.")
            }*/
            val id=tag.tag.id
            var snackText = ""
            for (byte in id) {
                snackText+=String.format("%02x",byte)+" "
            }
            Snackbar.make(findViewById(R.id.fab), snackText, Snackbar.LENGTH_INDEFINITE).show()

            //ViewModelScope.launch {}

            //coroutine try 2
            // Dispatchers.Main
            suspend fun fetchRFID(tag: MifareClassic) {
                // Dispatchers.Main
                val result = tag.transceive("0000".toByteArray())
                val tmp:String
                tmp=result.toString()
            }
            // look at this in the next section
            //suspend fun fetchRFID(tag: tag) = withContext(Dispatchers.IO){/*...*/}


            //var data = tag.authenticateSectorWithKeyA(0, MifareClassic.KEY_DEFAULT)
            //Log.d(TAG, messages[0].toString())
            //Toast.makeText(this, messages[0].toString(), Toast.LENGTH_SHORT).show()
        }
    }

    /*
    fun readTag(tag: Tag): String? {
        return MifareClassic.get(tag)?.use { mifare ->
            mifare.connect()
            val payload = mifare.readBlock(0)
            String(payload, Charset.forName("US-ASCII"))
        }
    }
     */
}