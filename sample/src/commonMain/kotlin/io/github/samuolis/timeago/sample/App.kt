package io.github.samuolis.timeago.sample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.samuolis.timeago.TimeAgoLocale
import io.github.samuolis.timeago.timeAgo
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars),
            color = MaterialTheme.colorScheme.background
        ) {
            TimeAgoSampleContent()
        }
    }
}

@Composable
fun TimeAgoSampleContent() {
    val examples = remember {
        listOf(
            "Past durations" to listOf(
                "30 seconds" to 30.seconds.timeAgo(),
                "1 minute" to 1.minutes.timeAgo(),
                "5 minutes" to 5.minutes.timeAgo(),
                "1 hour" to 1.hours.timeAgo(),
                "3 hours" to 3.hours.timeAgo(),
                "1 day" to 1.days.timeAgo(),
                "5 days" to 5.days.timeAgo(),
                "7 days" to 7.days.timeAgo(),
                "14 days" to 14.days.timeAgo(),
                "30 days" to 30.days.timeAgo(),
                "365 days" to 365.days.timeAgo(),
            ),
            "Future durations" to listOf(
                "-30 seconds" to (-30).seconds.timeAgo(),
                "-5 minutes" to (-5).minutes.timeAgo(),
                "-1 day" to (-1).days.timeAgo(),
                "-7 days" to (-7).days.timeAgo(),
            ),
            "German locale" to listOf(
                "2 hours" to 2.hours.timeAgo(GermanLocale),
                "1 day" to 1.days.timeAgo(GermanLocale),
                "-1 day" to (-1).days.timeAgo(GermanLocale),
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "TimeAgo KMP Sample",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        examples.forEach { (sectionTitle, items) ->
            item {
                Text(
                    text = sectionTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }

            items(items) { (duration, result) ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = duration)
                        Text(
                            text = result,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

private object GermanLocale : TimeAgoLocale {
    override val justNow = "gerade eben"
    override val oneMinuteAgo = "vor 1 Minute"
    override fun minutesAgo(minutes: Long) = "vor $minutes Minuten"
    override val oneHourAgo = "vor 1 Stunde"
    override fun hoursAgo(hours: Long) = "vor $hours Stunden"
    override val yesterday = "gestern"
    override fun daysAgo(days: Long) = "vor $days Tagen"
    override val oneWeekAgo = "vor 1 Woche"
    override fun weeksAgo(weeks: Long) = "vor $weeks Wochen"
    override val oneMonthAgo = "vor 1 Monat"
    override fun monthsAgo(months: Long) = "vor $months Monaten"
    override val oneYearAgo = "vor 1 Jahr"
    override fun yearsAgo(years: Long) = "vor $years Jahren"
    override val justNowFuture = "gleich"
    override val inOneMinute = "in 1 Minute"
    override fun inMinutes(minutes: Long) = "in $minutes Minuten"
    override val inOneHour = "in 1 Stunde"
    override fun inHours(hours: Long) = "in $hours Stunden"
    override val tomorrow = "morgen"
    override fun inDays(days: Long) = "in $days Tagen"
    override val inOneWeek = "in 1 Woche"
    override fun inWeeks(weeks: Long) = "in $weeks Wochen"
    override val inOneMonth = "in 1 Monat"
    override fun inMonths(months: Long) = "in $months Monaten"
    override val inOneYear = "in 1 Jahr"
    override fun inYears(years: Long) = "in $years Jahren"
}
