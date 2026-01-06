@file:OptIn(ExperimentalJsExport::class)

package io.github.samuolis.timeago

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * JavaScript/TypeScript API for TimeAgo.
 *
 * Usage:
 * ```javascript
 * import { TimeAgo } from 'timeago-kmp';
 *
 * TimeAgo.fromSeconds(30);     // "just now"
 * TimeAgo.fromMinutes(5);      // "5 minutes ago"
 * TimeAgo.fromHours(2);        // "2 hours ago"
 * TimeAgo.fromDays(1);         // "yesterday"
 *
 * // Future times
 * TimeAgo.fromSeconds(-3600);  // "in 1 hour"
 * ```
 */
@JsExport
@JsName("TimeAgo")
public object TimeAgoJs {
    /**
     * Formats elapsed seconds into a human-readable "time ago" string.
     * @param seconds Number of seconds (positive for past, negative for future)
     * @return Human-readable string like "5 minutes ago" or "in 2 hours"
     */
    @JsName("fromSeconds")
    public fun fromSeconds(seconds: Int): String {
        return TimeAgo.fromSeconds(seconds.toLong())
    }

    /**
     * Formats elapsed minutes into a human-readable "time ago" string.
     * @param minutes Number of minutes (positive for past, negative for future)
     */
    @JsName("fromMinutes")
    public fun fromMinutes(minutes: Int): String {
        return TimeAgo.fromMinutes(minutes.toLong())
    }

    /**
     * Formats elapsed hours into a human-readable "time ago" string.
     * @param hours Number of hours (positive for past, negative for future)
     */
    @JsName("fromHours")
    public fun fromHours(hours: Int): String {
        return TimeAgo.fromHours(hours.toLong())
    }

    /**
     * Formats elapsed days into a human-readable "time ago" string.
     * @param days Number of days (positive for past, negative for future)
     */
    @JsName("fromDays")
    public fun fromDays(days: Int): String {
        return TimeAgo.fromDays(days.toLong())
    }
}
