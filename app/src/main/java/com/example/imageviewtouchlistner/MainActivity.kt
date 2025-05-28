package com.example.imageviewtouchlistner

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var circleOverlay: CircleOverlayView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        circleOverlay = findViewById(R.id.circleOverlay)

        imageView.setOnTouchListener { _, event ->
            circleOverlay.showCircle(event.x, event.y)
            true
        }
    }
}
