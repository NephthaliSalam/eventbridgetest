package com.example.eventbridgetest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventbridgetest.ui.theme.EventbridgetestTheme
import org.json.JSONObject
import android.webkit.CookieManager

class WebAppInterface(private val context: Context) {
    @JavascriptInterface
    fun processEvent(eventData: String) {
        try {
            val json = JSONObject(eventData)
            val eventName = json.optString("eventName", "UNKNOWN_EVENT")

            Log.d("WebAppInterface", "Received eventData: $eventData")

            Toast.makeText(context, "EventName: $eventName", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("WebAppInterface", "Failed to parse event data", e)
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)


        webView.addJavascriptInterface(WebAppInterface(this), "PaymentAppBridge")
        webView.loadUrl("") // ! ADD URL HERE
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EventbridgetestTheme {
        Greeting("Android")
    }
}