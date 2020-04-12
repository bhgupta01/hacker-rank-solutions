package main.java.expedia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    private static final String BOT_PATTERN_STRING = "^(ExpediaBookings/(?!\\d{5,}|[4-9]\\d{3}|(3([4-9]\\d{2}|3([7-9]\\d|6[8-9]))))\\d+\\sCFNetwork/([.\\d]*)\\sDarwin/([.\\d]*))$";
    private static final String HMAC_PATTERN_STRING = "^(ExpediaBookings/(\\d{5,}|[4-9]\\d{3}|(3([4-9]\\d{2}|3([7-9]\\d|6[8-9]))))\\sCFNetwork/([.\\d]*)\\sDarwin/([.\\d]*))$";

    private static final String[] INPUT_STRINGS = {
            "ExpediaBookings/2165 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2170 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2519 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2557 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2572 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2576 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2581 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2584 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2587 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2589 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2593 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2600 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2604 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2608 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2613 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2618 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2620 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2625 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2626 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2627 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2633 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2651 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2657 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2683 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2699 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2706 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2721 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2727 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2730 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2738 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2741 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2753 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2766 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2772 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2776 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2809 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2813 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2815 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2824 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2825 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2833 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2841 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2844 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2850 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2851 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2855 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2858 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2860 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2887 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2891 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2894 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2909 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2934 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2951 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/2995 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3022 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3026 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3032 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3045 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3058 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3069 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3072 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3074 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3077 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3084 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3100 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3102 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3104 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3112 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3115 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3117 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3118 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3119 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3123 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3124 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3126 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3128 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3131 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3135 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3139 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3141 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3146 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3153 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3156 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3158 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3163 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3167 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3188 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3193 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3196 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3203 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3207 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3208 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3212 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3220 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3226 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3229 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3231 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3234 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3236 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3238 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3241 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3249 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3251 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3258 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3259 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3261 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3262 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3263 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3273 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3279 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3280 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3281 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3283 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3284 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3288 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3290 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3292 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3295 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3297 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3299 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3303 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3308 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3329 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3330 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3334 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3337 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3341 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3343 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3345 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3348 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3349 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3352 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3353 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3359 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3363 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3364 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3366 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3368 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3372 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3374 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3376 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3381 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3387 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3389 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3391 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3394 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3399 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3400 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3409 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3411 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3413 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3414 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3416 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3417 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3425 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3429 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3430 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3431 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3433 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3435 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3436 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3439 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3440 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3441 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3444 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3445 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3450 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3454 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3462 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3469 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3475 CFNetwork/123.45.6 Darwin/789.0.1",
            "ExpediaBookings/3476 CFNetwork/123.45.6 Darwin/789.0.1"
    };

    public static void main(String[] args) {
        performTest(Pattern.compile(HMAC_PATTERN_STRING), "Protected");
        performTest(Pattern.compile(BOT_PATTERN_STRING), "Blocked");
    }

    private static void performTest(final Pattern pattern, final String testName) {
        final List<String> matchedVersions = new ArrayList<>();
        final List<String> unmatchedVersions = new ArrayList<>();
        for (final String input : INPUT_STRINGS) {
            final Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                matchedVersions.add(matcher.group(0));
            } else {
                unmatchedVersions.add(input);
            }
        }
        Logger.getGlobal().info(testName + ": \n" + printList(matchedVersions));
        Logger.getGlobal().info("NOT " + testName + ": \n" + printList(unmatchedVersions));
    }

    private static String printList(List<String> list) {
        final StringBuilder builder = new StringBuilder();
        list.forEach(item -> {
            builder.append(item).append("\n");
        });
        return builder.toString().trim();
    }
}