# TimeAgo KMP

A lightweight Kotlin Multiplatform library for formatting durations into human-readable "time ago" strings.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.samuolis/timeago-kmp)](https://central.sonatype.com/artifact/io.github.samuolis/timeago-kmp)
[![npm](https://img.shields.io/npm/v/timeago-kmp)](https://www.npmjs.com/package/timeago-kmp)
[![Swift Package Manager](https://img.shields.io/badge/SPM-compatible-brightgreen.svg)](https://swift.org/package-manager/)
[![Kotlin](https://img.shields.io/badge/kotlin-2.3.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## Features

- **Zero dependencies** - Uses only `kotlin.time.Duration` from Kotlin stdlib
- **Multiplatform** - Android, iOS, macOS, JVM, JS, WasmJS, Linux, Windows
- **Swift Package Manager** - Native iOS/macOS support via XCFramework
- **npm** - JavaScript/TypeScript support with type definitions
- **Lightweight** - Single file, minimal code
- **Customizable** - Bring your own translations via `TimeAgoLocale`
- **Future support** - Handles both past and future durations

## Installation

### Kotlin/Android (Maven Central)

Add the dependency to your `build.gradle.kts`:

```kotlin
// For common/shared module
commonMain.dependencies {
    implementation("io.github.samuolis:timeago-kmp:0.1.11")
}
```

### Swift Package Manager (iOS/macOS)

In Xcode: **File** → **Add Package Dependencies** → Enter:

```
https://github.com/samuolis/timeago-kmp
```

Or add to your `Package.swift`:

```swift
dependencies: [
    .package(url: "https://github.com/samuolis/timeago-kmp", from: "0.1.11")
]
```

### npm (JavaScript/TypeScript)

```bash
npm install timeago-kmp
```

Or with yarn:

```bash
yarn add timeago-kmp
```

## Usage

### Kotlin

```kotlin
import io.github.samuolis.timeago.timeAgo
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.days

5.minutes.timeAgo()      // "5 minutes ago"
1.hours.timeAgo()        // "1 hour ago"
2.days.timeAgo()         // "2 days ago"
45.seconds.timeAgo()     // "just now"

// Future times
(-1).days.timeAgo()      // "tomorrow"
(-3).hours.timeAgo()     // "in 3 hours"
```

### Swift

```swift
import TimeAgoKMP

// Using TimeAgo object
TimeAgo.shared.fromSeconds(seconds: 30)    // "just now"
TimeAgo.shared.fromMinutes(minutes: 5)     // "5 minutes ago"
TimeAgo.shared.fromHours(hours: 2)         // "2 hours ago"
TimeAgo.shared.fromDays(days: 1)           // "yesterday"

// Future times (negative values)
TimeAgo.shared.fromDays(days: -1)          // "tomorrow"

// Or using top-level functions
TimeAgoKt.timeAgoFromSeconds(seconds: 300) // "5 minutes ago"
```

### JavaScript/TypeScript

```javascript
// ES Modules
import { TimeAgo } from 'timeago-kmp';

TimeAgo.fromSeconds(30);     // "just now"
TimeAgo.fromMinutes(5);      // "5 minutes ago"
TimeAgo.fromHours(2);        // "2 hours ago"
TimeAgo.fromDays(1);         // "yesterday"

// Future times (negative values)
TimeAgo.fromDays(-1);        // "tomorrow"
```

```javascript
// CommonJS
const { TimeAgo } = require('timeago-kmp');

TimeAgo.fromSeconds(300);    // "5 minutes ago"
```

### Swift Date Extension

```swift
import Foundation
import TimeAgoKMP

extension Date {
    func timeAgo() -> String {
        let seconds = Int64(Date().timeIntervalSince(self))
        return TimeAgo.shared.fromSeconds(seconds: seconds)
    }
}

// Usage
let postDate = Date().addingTimeInterval(-7200)
print(postDate.timeAgo()) // "2 hours ago"
```

### Custom Locale

**Kotlin:**
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
