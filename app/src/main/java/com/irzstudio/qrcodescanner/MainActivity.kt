package com.irzstudio.qrcodescanner

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_scan.setOnClickListener {
            startActivityForResult(Intent(applicationContext, ScanActivity::class.java), 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 100){
            val resultScan = data?.getStringExtra("data")
            tv_result.text  = resultScan

            if (resultScan.orEmpty().startsWith("http") || resultScan.orEmpty().startsWith("www")){
                val intent = Intent(ACTION_VIEW)
                intent.setData(Uri.parse(resultScan))
                startActivity(intent)
            }
        }
    }
}