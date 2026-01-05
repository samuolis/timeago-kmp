package io.github.samuolis.timeago

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class TimeAgoTest {

    @Test
    fun testJustNow() {
        assertEquals("just now", 30.seconds.timeAgo())
        assertEquals("just now", 59.seconds.timeAgo())
    }

    @Test
    fun testMinutes() {
        assertEquals("1 minute ago", 1.minutes.timeAgo())
        assertEquals("5 minutes ago", 5.minutes.timeAgo())
        assertEquals("59 minutes ago", 59.minutes.timeAgo())
    }

    @Test
    fun testHours() {
        assertEquals("1 hour ago", 1.hours.timeAgo())
        assertEquals("5 hours ago", 5.hours.timeAgo())
        assertEquals("23 hours ago", 23.hours.timeAgo())
    }

    @Test
    fun testDays() {
        assertEquals("yesterday", 1.days.timeAgo())
        assertEquals("3 days ago", 3.days.timeAgo())
        assertEquals("6 days ago", 6.days.timeAgo())
    }

    @Test
    fun testWeeks() {
        assertEquals("1 week ago", 7.days.timeAgo())
        assertEquals("2 weeks ago", 14.days.timeAgo())
        assertEquals("3 weeks ago", 21.days.timeAgo())
    }

    @Test
    fun testMonths() {
        assertEquals("1 month ago", 30.days.timeAgo())
        assertEquals("6 months ago", 180.days.timeAgo())
    }

    @Test
    fun testYears() {
        assertEquals("1 year ago", 365.days.timeAgo())
        assertEquals("2 years ago", 730.days.timeAgo())
    }

    @Test
    fun testFuture() {
        assertEquals("in a moment", (-30).seconds.timeAgo())
        assertEquals("in 5 minutes", (-5).minutes.timeAgo())
        assertEquals("tomorrow", (-1).days.timeAgo())
        assertEquals("in 3 days", (-3).days.timeAgo())
    }
}
