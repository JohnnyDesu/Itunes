package com.example.itunesmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.itunesmovies.api.NetworkCheck
import com.example.itunesmovies.ui.Index
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var networkCheck = NetworkCheck()
    private var chnetwork = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chnetwork = networkCheck.networkisConnected(this)
        checkConnected()

    }
    //Check Network Connected
    private fun checkConnected(){
        if(chnetwork){
            idlogo.isVisible = true
            idlogo.alpha = 0f
            idlogo.animate().setDuration(1000).alpha(1f).withEndAction {
                startActivity(Intent(this, Index::class.java))
                finish()
            }
        }else{
            lynetwork1.isVisible = true
            lynetwork1.alpha = 0f
            lynetwork1.animate().setDuration(1000).alpha(1f).withEndAction {
                idlogo.isVisible = false
            }
        }
    }
}