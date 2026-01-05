# TimeAgo KMP

A lightweight Kotlin Multiplatform library for formatting durations into human-readable "time ago" strings.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.samuolis/timeago-kmp)](https://central.sonatype.com/artifact/io.github.samuolis/timeago-kmp)
[![Kotlin](https://img.shields.io/badge/kotlin-2.3.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## Features

- **Zero dependencies** - Uses only `kotlin.time.Duration` from Kotlin stdlib
- **Multiplatform** - Android, iOS, macOS, JVM, JS, WasmJS, Linux, Windows
- **Lightweight** - Single file, ~150 lines of code
- **Customizable** - Bring your own translations via `TimeAgoLocale`
- **Future support** - Handles both past and future durations

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
// For common/shared module
commonMain.dependencies {
    implementation("io.github.samuolis:timeago-kmp:0.1.0")
}
```

## Usage

### Basic Usage

```kotlin
import io.github.samuolis.timeago.timeAgo
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.days

5.minutes.timeAgo()      // "5 minutes ago"
1.hours.timeAgo()        // "1 hour ago"
2.days.timeAgo()         // "2 days ago"
45.seconds.timeAgo()     // "just now"
```

### With Instant (calculating elapsed time)

```kotlin
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours

val pastTime = Clock.System.now() - 2.hours
val elapsed = Clock.System.now() - pastTime
elapsed.timeAgo() // "2 hours ago"
```

### Future Durations

Negative durations are formatted as future times:

```kotlin
import kotlin.time.Duration.Companion.days

(-1).days.timeAgo()  // "tomorrow"
(-3).hours.timeAgo() // "in 3 hours"
```

### Custom Locale

Implement `TimeAgoLocale` for custom translations:

```kotlin
object GermanLocale : TimeAgoLocale {
    override val justNow = "gerade eben"
    override val oneMinuteAgo = "vor 1 Minute"
    override fun minutesAgo(minutes: Long) = "vor $minutes Minuten"
    override val oneHourAgo = "vor 1 Stunde"
    override fun hoursAgo(hours: Long) = "vor $hours Stunden"
    override val yesterday = "gestern"
    override fun daysAgo(days: Long) = "vor $days Tagen"
    // ... implement remaining properties
}

2.hours.timeAgo(GermanLocale) // "vor 2 Stunden"
```

## Output Examples

| Duration | Output |
|----------|--------|
| 30 seconds | just now |
| 1 minute | 1 minute ago |
| 5 minutes | 5 minutes ago |
| 1 hour | 1 hour ago |
| 3 hours | 3 hours ago |
| 1 day | yesterday |
| 5 days | 5 days ago |
| 1 week | 1 week ago |
| 2 weeks | 2 weeks ago |
| 1 month | 1 month ago |
| 6 months | 6 months ago |
| 1 year | 1 year ago |
| 3 years | 3 years ago |

## Supported Platforms

- Android
- iOS (arm64, x64, simulatorArm64)
- macOS (arm64, x64)
- JVM
- JavaScript (browser, Node.js)
- WasmJS (browser, Node.js)
- Linux (x64, arm64)
- Windows (mingwX64)

## License

MIT License - see [LICENSE](LICENSE) for details.
