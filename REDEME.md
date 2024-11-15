# 🎯 ArcifyIndicator

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

## 🚀 Usage

### Basic Example

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
