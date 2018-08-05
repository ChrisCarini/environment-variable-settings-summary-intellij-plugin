package com.chriscarini.jetbrains.settingssummary.environmentvariables;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utils for Environment Variables
 */
public final class EnvVarUtils {
    private EnvVarUtils() {
    }

    public static List<String> getSortedDistinctList(final Set<String> input) {
        return getSortedDistinctList(input.stream());
    }

    public static List<String> getSortedDistinctList(final List<String> input) {
        return getSortedDistinctList(input.stream());
    }

    private static List<String> getSortedDistinctList(final Stream<String> input) {
        return input.filter(s -> !s.isEmpty()).sorted().distinct().collect(Collectors.toList());
    }
}
