package io.github.samuolis.timeago

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Formats a duration into a human-readable "time ago" string.
 *
 * ## Usage
 *
 * ```kotlin
 * import kotlin.time.Duration.Companion.minutes
 * import kotlin.time.Duration.Companion.hours
 * import kotlin.time.Duration.Companion.days
 *
 * 5.minutes.timeAgo()      // "5 minutes ago"
 * 1.hours.timeAgo()        // "1 hour ago"
 * 2.days.timeAgo()         // "2 days ago"
 * 45.seconds.timeAgo()     // "just now"
 * ```
 *
 * ## With Instant
 *
 * ```kotlin
 * import kotlin.time.Clock
 * import kotlin.time.ExperimentalTime
 *
 * @OptIn(ExperimentalTime::class)
 * fun example() {
 *     val pastTime = Clock.System.now() - 2.hours
 *     val elapsed = Clock.System.now() - pastTime
 *     elapsed.timeAgo() // "2 hours ago"
 * }
 * ```
 *
 * @param locale Optional locale configuration for customizing output strings
 * @return A human-readable string like "5 minutes ago" or "just now"
 */
public fun Duration.timeAgo(locale: TimeAgoLocale = TimeAgoLocale.English): String {
    val seconds = inWholeSeconds
    val minutes = inWholeMinutes
    val hours = inWholeHours
    val days = inWholeDays
    val weeks = days / 7
    val months = days / 30
    val years = days / 365

    return when {
        seconds < 0 -> locale.futurePrefix + formatFuture(-seconds, -minutes, -hours, -days, locale) + locale.futureSuffix
        seconds < 60 -> locale.justNow
        minutes == 1L -> locale.oneMinuteAgo
        minutes < 60 -> locale.minutesAgo(minutes)
        hours == 1L -> locale.oneHourAgo
        hours < 24 -> locale.hoursAgo(hours)
        days == 1L -> locale.yesterday
        days < 7 -> locale.daysAgo(days)
        weeks == 1L -> locale.oneWeekAgo
        weeks < 4 -> locale.weeksAgo(weeks)
        months == 1L -> locale.oneMonthAgo
        months < 12 -> locale.monthsAgo(months)
        years == 1L -> locale.oneYearAgo
        else -> locale.yearsAgo(years)
    }
}

private fun formatFuture(
    seconds: Long,
    minutes: Long,
    hours: Long,
    days: Long,
    locale: TimeAgoLocale
): String {
    val weeks = days / 7
    val months = days / 30
    val years = days / 365

    return when {
        seconds < 60 -> locale.justNowFuture
        minutes == 1L -> locale.inOneMinute
        minutes < 60 -> locale.inMinutes(minutes)
        hours == 1L -> locale.inOneHour
        hours < 24 -> locale.inHours(hours)
        days == 1L -> locale.tomorrow
        days < 7 -> locale.inDays(days)
        weeks == 1L -> locale.inOneWeek
        weeks < 4 -> locale.inWeeks(weeks)
        months == 1L -> locale.inOneMonth
        months < 12 -> locale.inMonths(months)
        years == 1L -> locale.inOneYear
        else -> locale.inYears(years)
    }
}

/**
 * Locale configuration for TimeAgo strings.
 * Implement this interface to provide custom translations.
 */
public interface TimeAgoLocale {
    // Past
    public val justNow: String
    public val oneMinuteAgo: String
    public fun minutesAgo(minutes: Long): String
    public val oneHourAgo: String
    public fun hoursAgo(hours: Long): String
    public val yesterday: String
    public fun daysAgo(days: Long): String
    public val oneWeekAgo: String
    public fun weeksAgo(weeks: Long): String
    public val oneMonthAgo: String
    public fun monthsAgo(months: Long): String
    public val oneYearAgo: String
    public fun yearsAgo(years: Long): String

    // Future
    public val futurePrefix: String get() = ""
    public val futureSuffix: String get() = ""
    public val justNowFuture: String
    public val inOneMinute: String
    public fun inMinutes(minutes: Long): String
    public val inOneHour: String
    public fun inHours(hours: Long): String
    public val tomorrow: String
    public fun inDays(days: Long): String
    public val inOneWeek: String
    public fun inWeeks(weeks: Long): String
    public val inOneMonth: String
    public fun inMonths(months: Long): String
    public val inOneYear: String
    public fun inYears(years: Long): String

    /**
     * Default English locale.
     */
    public companion object {
        public val English: TimeAgoLocale = EnglishLocale
    }
}

private object EnglishLocale : TimeAgoLocale {
    // Past
    override val justNow = "just now"
    override val oneMinuteAgo = "1 minute ago"
    override fun minutesAgo(minutes: Long) = "$minutes minutes ago"
    override val oneHourAgo = "1 hour ago"
    override fun hoursAgo(hours: Long) = "$hours hours ago"
    override val yesterday = "yesterday"
    override fun daysAgo(days: Long) = "$days days ago"
    override val oneWeekAgo = "1 week ago"
    override fun weeksAgo(weeks: Long) = "$weeks weeks ago"
    override val oneMonthAgo = "1 month ago"
    override fun monthsAgo(months: Long) = "$months months ago"
    override val oneYearAgo = "1 year ago"
    override fun yearsAgo(years: Long) = "$years years ago"

    // Future
    override val justNowFuture = "in a moment"
    override val inOneMinute = "in 1 minute"
    override fun inMinutes(minutes: Long) = "in $minutes minutes"
    override val inOneHour = "in 1 hour"
    override fun inHours(hours: Long) = "in $hours hours"
    override val tomorrow = "tomorrow"
    override fun inDays(days: Long) = "in $days days"
    override val inOneWeek = "in 1 week"
    override fun inWeeks(weeks: Long) = "in $weeks weeks"
    override val inOneMonth = "in 1 month"
    override fun inMonths(months: Long) = "in $months months"
    override val inOneYear = "in 1 year"
    override fun inYears(years: Long) = "in $years years"
}

// Swift-friendly API (Duration is an inline class and doesn't export well)

/**
 * Formats elapsed seconds into a human-readable "time ago" string.
 * Use this from Swift/Objective-C.
 *
 * @param seconds The number of seconds elapsed (positive for past, negative for future)
 * @param locale Optional locale for custom translations
 * @return A human-readable string like "5 minutes ago"
 */
public fun timeAgoFromSeconds(seconds: Long, locale: TimeAgoLocale = TimeAgoLocale.English): String {
    return seconds.seconds.timeAgo(locale)
}

/**
 * Formats elapsed minutes into a human-readable "time ago" string.
 * Use this from Swift/Objective-C.
 */
public fun timeAgoFromMinutes(minutes: Long, locale: TimeAgoLocale = TimeAgoLocale.English): String {
    return minutes.minutes.timeAgo(locale)
}

/**
 * Formats elapsed hours into a human-readable "time ago" string.
 * Use this from Swift/Objective-C.
 */
public fun timeAgoFromHours(hours: Long, locale: TimeAgoLocale = TimeAgoLocale.English): String {
    return hours.hours.timeAgo(locale)
}

/**
 * Formats elapsed days into a human-readable "time ago" string.
 * Use this from Swift/Objective-C.
 */
public fun timeAgoFromDays(days: Long, locale: TimeAgoLocale = TimeAgoLocale.English): String {
    return days.days.timeAgo(locale)
}

/**
 * Helper object for Swift interop - provides static-like access to timeAgo functions.
 *
 * Note: Kotlin default parameters don't export to Swift/ObjC, so we provide
 * overloaded methods both with and without the locale parameter.
 */
public object TimeAgo {
    /**
     * Formats elapsed seconds into a human-readable "time ago" string.
     * Uses English locale by default.
     */
    public fun fromSeconds(seconds: Long): String {
        return seconds.seconds.timeAgo(TimeAgoLocale.English)
    }

    /**
     * Formats elapsed seconds into a human-readable "time ago" string.
     */
    public fun fromSeconds(seconds: Long, locale: TimeAgoLocale): String {
        return seconds.seconds.timeAgo(locale)
    }

    /**
     * Formats elapsed minutes into a human-readable "time ago" string.
     * Uses English locale by default.
     */
    public fun fromMinutes(minutes: Long): String {
        return minutes.minutes.timeAgo(TimeAgoLocale.English)
    }

    /**
     * Formats elapsed minutes into a human-readable "time ago" string.
     */
    public fun fromMinutes(minutes: Long, locale: TimeAgoLocale): String {
        return minutes.minutes.timeAgo(locale)
    }

    /**
     * Formats elapsed hours into a human-readable "time ago" string.
     * Uses English locale by default.
     */
    public fun fromHours(hours: Long): String {
        return hours.hours.timeAgo(TimeAgoLocale.English)
    }

    /**
     * Formats elapsed hours into a human-readable "time ago" string.
     */
    public fun fromHours(hours: Long, locale: TimeAgoLocale): String {
        return hours.hours.timeAgo(locale)
    }

    /**
     * Formats elapsed days into a human-readable "time ago" string.
     * Uses English locale by default.
     */
    public fun fromDays(days: Long): String {
        return days.days.timeAgo(TimeAgoLocale.English)
    }

    /**
     * Formats elapsed days into a human-readable "time ago" string.
     */
    public fun fromDays(days: Long, locale: TimeAgoLocale): String {
        return days.days.timeAgo(locale)
    }

    /**
     * Default English locale.
     */
    public val englishLocale: TimeAgoLocale = TimeAgoLocale.English
}
