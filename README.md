# ArcifyIndicator

**ArcifyIndicator** is a customizable circular progress indicator designed for Android Jetpack Compose. It offers a flexible and animated arc-shaped progress bar with various configuration options to enhance your application's user interface.

---

## 🌟 Features

- **Progress Modes**:
  - **Manual**: Allows manual control of the progress value.
  - **Auto**: Automatically progresses at a specified interval.

- **Customization Options**:
  -  **Color**: Set the color of the progress arc.
  -  **Background Color**: Define the color of the unprogressed background arc.
  -  **Stroke Width**: Adjust the thickness of the progress bar.
  -  **Stroke Cap**: Choose the shape of the ends of the progress bar (Butt or Round).
  -  **Progress Direction**: Select the direction of progress (CLOCKWISE or COUNTERCLOCKWISE).
  -  **Animation Specification**: Configure the animation's duration and easing.
  -  **Center Content**: Insert custom composable content at the center of the indicator.
  -  **Progress Change Callback**: Receive callbacks when the progress value changes.

---
## 🚀 Installation

[![GitHub release (latest by date)](https://img.shields.io/github/v/release/chani01/ArcifyIndicator?label=Latest%20Version)](https://github.com/chani01/ArcifyIndicator/releases)

Add the following dependency to your `build.gradle` file once the library is published:

```gradle
dependencies {
    implementation 'com.chani01:ArcifyIndicator:<latest_version>'
}
```

---

## 📖  Usage

### Manual Progress Mode

```kotlin
ArcifyIndicator(
    modifier = Modifier.size(100.dp),
    progressState = ProgressState.Manual(progress = 0.75f),
    color = Color.Blue,
    backgroundColor = Color.Gray,
    strokeWidth = 8.dp,
    strokeCap = StrokeCap.Round,
    progressDirection = ProgressDirection.CLOCKWISE
)
```

### Auto Progress Mode
```kotlin
ArcifyIndicator(
    modifier = Modifier.size(100.dp),
    progressState = ProgressState.Auto(autoProgressDelay = 1000L), // Updates every second
    color = Color.Green,
    backgroundColor = Color.LightGray,
    strokeWidth = 6.dp,
    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
)
```

### Center Content and Progress Change Callback
```kotlin
ArcifyIndicator(
    modifier = Modifier.size(120.dp),
    progressState = ProgressState.Manual(progress = 0.5f),
    color = Color.Red,
    strokeWidth = 10.dp,
    centerContent = {
        Text(text = "50%", style = MaterialTheme.typography.h6)
    },
    onProgressChanged = { progress ->
        println("Progress changed: $progress")
    }
)
```

### Infinite Progress Indicator
```kotlin
ArcifyInfiniteIndicator(
    modifier = Modifier.size(100.dp),
    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
    backgroundColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
    strokeWidth = 6.dp,
    strokeCap = StrokeCap.Round,
    progressDirection = ArcifyProgressDirection.CLOCKWISE,
    animationDuration = 1000,
    centerContent = {
        Text(text = "Loading...", style = MaterialTheme.typography.body2)
    }
)
```

---
## 📄 License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

---

## 🙌 Contributions

Contributions are welcome! Feel free to open issues, submit pull requests, or suggest new features. 😊

---
