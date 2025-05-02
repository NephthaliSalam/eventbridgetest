# 📱 Payment Widget Integration Tester (Android)

This Android project is a simple WebView wrapper to help test integration of a **Payment Widget** and log any JavaScript-based UI events emitted by the widget. It's designed for fast local development and debugging.

---

## Features

- Loads a web-based payment widget inside a WebView.
- Captures and logs events sent via JavaScript using `eventBridge.publishUIEvent(...)`.
- Shows toast notifications for each event received.
- Logs all received events to Logcat.

---

## 🛠️ Setup Instructions

### 1. Clone or open the project in **Android Studio**.

### 2. Set your **Payment Widget URL**

Edit the `MainActivity.kt` file:

```
webView.loadUrl("https://your-payment-widget.com/checkout?accessCode=xyz&paymentId=abc")
```

### 3. (Optional) Set Authentication Cookie

If your widget requires a token via cookies:

```
val cookie = "st-access-token=your-access-token-here; Path=/; HttpOnly"
cookieManager.setCookie("https://your-payment-widget.com", cookie)
CookieManager.getInstance().flush()
```

> ⚠️ If testing with localhost, use `http://10.0.2.2` for Android emulators.

### 4. Run the app

- Use an emulator or physical device with internet access.
- Ensure the widget URL is reachable.

---

## 📋 Receiving Events

Your web widget should emit events like this:

```js
eventBridge.publishUIEvent({
  eventName: 'UI_WIDGET_LOADED_SUCCESS',
  timestamp: Date.now(),
  ...
});
```

These events will be captured in Kotlin via:

```
@JavascriptInterface
fun processEvent(eventData: String)
```

---

## 🔍 How to View Events

1. Open **Logcat** in Android Studio.
2. Search using:
   ```
   WebAppInterface
   ```
3. You’ll see logs like:
   ```
   Received eventData: {"eventName":"UI_WIDGET_LOADED_SUCCESS", ...}
   ```

A `Toast` will also appear on the screen for each event.

---

## 📎 Notes

- JavaScript and DOM Storage must be enabled on the WebView (already configured).
- This app uses Jetpack Compose but only minimally.
- Can be expanded for more advanced testing if needed.

---

Made for rapid testing and debugging of mobile widget integrations 🚀