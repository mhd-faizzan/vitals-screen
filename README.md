# Vitals Screen

An Android app that turns your phone camera into a real-time vitals monitor. Built with Presage SmartSpectra SDK. Point your camera at your face and watch your heart rate and breathing rate update live. No wearables, no contact sensors, just your camera.

---

## Demo

> The values shown below were recorded during a live session. For privacy reasons the face has been removed from the frame before taking the screenshot — the metrics shown are real measurements captured by the SDK.

<p align="center">
  <img src="assets/demo.jpg" alt="Vitals Screen Demo" width="300"/>
</p>

---

## How it works

The app uses the Presage SmartSpectra SDK to access your phone camera and measure physiological signals from your face in real time. It extracts heart rate and breathing rate using computer vision and signal processing. No data leaves your device in an identifiable form — the SDK operates on anonymized data only.

---

## Tech Stack

| Layer | Tool |
|---|---|
| Language | Kotlin |
| Framework | Android SDK |
| Vitals SDK | Presage SmartSpectra 1.0.25 |
| Min Android | API 26 (Android 8.0 Oreo) |

---

## Prerequisites

Before you start make sure you have:

- Android Studio installed → [developer.android.com/studio](https://developer.android.com/studio)
- An Android phone running Android 8.0 or higher
- A Presage API key → sign up free at [physiology.presagetech.com](https://physiology.presagetech.com/auth/register)

---

## Getting Started

### Step 1 — Clone the repo

```bash
git clone https://github.com/mhd-faizzan/vitals-screen.git
```

Open the project in Android Studio:

- Launch Android Studio
- Click **Open**
- Navigate to the cloned folder and select it

### Step 2 — Add your Presage API key

Open `app/src/main/java/com/yourname/vitalsscreen/MainActivity.kt` and replace the placeholder with your real key:

```kotlin
private val apiKey = "YOUR_PRESAGE_API_KEY"
```

> Never commit your real API key. Keep it local and never push it to GitHub.

### Step 3 — Connect your Android phone

1. Enable USB Debugging on your phone:
   - Go to **Settings → About Phone**
   - Tap **Build Number** 7 times
   - Go back → **Settings → Developer Options**
   - Enable **USB Debugging**
2. Connect your phone via USB cable
3. When prompted on your phone tap **Allow USB Debugging**

Or use **Wireless Debugging**:
- Go to **Settings → Developer Options → Wireless Debugging**
- In Android Studio → top bar → **No Devices → Pair Devices Using WiFi**
- Scan the QR code shown in Android Studio

### Step 4 — Run the app

In Android Studio click the green **Run ▶** button at the top.

The app will install and launch on your phone automatically.

---

## Usage

1. Open the app on your phone
2. Tap **CHECKUP** button
3. Grant camera permission when prompted
4. Hold your phone 1 to 2 feet from your face
5. Make sure you are in good lighting (60 lux minimum)
6. Stay still and watch your vitals update live

---

## Important Notes

- Never commit your real Presage API key to GitHub
- The app requires Android 8.0 (API 26) or higher
- Good lighting makes a significant difference in measurement accuracy
- Keep your face steady during measurement for best results
- The SDK handles all camera permissions automatically

---

## Built With

- [Presage SmartSpectra SDK](https://physiology.presagetech.com) — Real-time vitals from camera
- [Android Studio](https://developer.android.com/studio) — Development environment
- [Kotlin](https://kotlinlang.org) — Programming language