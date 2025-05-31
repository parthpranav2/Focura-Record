package com.example.speakoutloudpilot

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity() , TextToSpeech.OnInitListener {

    private val TAG = "MainActivity"

    private lateinit var apiKey : String

    private lateinit var tts : TextToSpeech


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbRef = FirebaseDatabase.getInstance().getReference("speakoutloudpilot").child("apikey")

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                apiKey = snapshot.getValue(String::class.java) ?: ""
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@MainActivity,
                    "Unable to fetch api key error: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        findViewById<Button>(R.id.btnGenerate).setOnClickListener {
            Log.d(TAG, "Generate button pressed")
            generateStory(findViewById<EditText>(R.id.txtWordCount).text.toString())
            findViewById<Button>(R.id.btnSpeak).isEnabled=true
        }

        findViewById<Button>(R.id.btnSpeak).setOnClickListener {
            val text = findViewById<TextView>(R.id.textView).text.toString()
            speak(text)
            findViewById<Button>(R.id.btnSpeak).isEnabled=false
        }

        val speedLabel = findViewById<TextView>(R.id.txtSpeedLabel)
        val speedSeekBar = findViewById<SeekBar>(R.id.speedSeekBar)

        var speechRate = 1.0f // Default speed

        speedSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                findViewById<Button>(R.id.btnSpeak).isEnabled=true
                speechRate = progress / 10.0f
                tts.setSpeechRate(speechRate)
                speedLabel.text = "Speech Speed: ${"%.1f".format(speechRate)}x"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    // Called when TTS is ready
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or the language is not supported.
                // Handle this case in your UI
            }
        }
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }


    private fun generateStory(wordCount : String) {
        val generativeModel = GenerativeModel(
            modelName = "gemini-2.0-flash",
            apiKey = apiKey
        )

        val prompt = "Write a paragraph of exactly $wordCount words about India. Do not include any introductory phrases like 'Here's a paragraph…'. Simply return the paragraph itself, making sure it is exactly $wordCount words long. Count words accurately."

        Log.d(TAG,prompt)
        // "Write an exact {$wordCount}-word paragraph about politics. Do not include any introductory phrases like ‘Here’s a paragraph…’—just output the paragraph itself. Keep a word count to ensure {$wordCount} words"

        lifecycleScope.launch {
            try {
                val response = generativeModel.generateContent(prompt)
                Log.d(TAG, "Model response = ${response.text}")
                findViewById<TextView>(R.id.textView).text = response.text
            } catch (e: Exception) {
                Log.e(TAG, "Gemini call failed $e", e)
                findViewById<TextView>(R.id.textView).text =
                    "Error: ${e.javaClass.simpleName}\n${e.message}"
            }
        }
    }


    override fun onDestroy() {
        // Shutdown TTS when activity is destroyed
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}