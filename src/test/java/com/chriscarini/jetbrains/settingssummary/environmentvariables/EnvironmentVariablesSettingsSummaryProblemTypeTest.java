package com.chriscarini.jetbrains.settingssummary.environmentvariables;


import com.intellij.openapi.project.Project;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentVariablesSettingsSummaryProblemTypeTest {

    private static final String TEST_KEY_1 = "TEST_KEY_1";
    private static final String TEST_KEY_2 = "TEST_KEY_2";
    private static final String TEST_KEY_3 = "TEST_KEY_3";
    private static final String TEST_VALUE_1 = "TEST_VALUE_1";
    private static final String TEST_VALUE_2 = "TEST_VALUE_2";
    private static final String TEST_VALUE_3 = "TEST_VALUE_3";
    private EnvironmentVariablesSettingsSummaryProblemType settingsSummaryProblemType;

    @BeforeMethod
    public void setUp() {
        // Create mock results
        final Map<String, String> exampleResult = new HashMap<>();
        exampleResult.put(TEST_KEY_1, TEST_VALUE_1);
        exampleResult.put(TEST_KEY_2, TEST_VALUE_2);
        exampleResult.put(TEST_KEY_3, TEST_VALUE_3);

        // Mock
        settingsSummaryProblemType = Mockito.spy(EnvironmentVariablesSettingsSummaryProblemType.class);
        Mockito.when(settingsSummaryProblemType.getSystemEnvironmentVariables()).thenReturn(exampleResult);
    }

    @Test
    public void testCollectInfo() {
        // Mock a {@link Project} to pass into the {@link ProblemType#collectInfo()} call.
        final Project project = Mockito.mock(Project.class);
        // Test
        final String info = settingsSummaryProblemType.collectInfo(project);
        assert info.contains(TEST_KEY_1);
        assert info.contains(TEST_KEY_2);
        assert info.contains(TEST_KEY_3);

        assert info.contains(TEST_VALUE_1);
        assert info.contains(TEST_VALUE_2);
        assert info.contains(TEST_VALUE_3);

        assert info.contains(String.format("%s:%s", TEST_KEY_1, TEST_VALUE_1));
        assert info.contains(String.format("%s:%s", TEST_KEY_2, TEST_VALUE_2));
        assert info.contains(String.format("%s:%s", TEST_KEY_3, TEST_VALUE_3));
    }

    @Test
    public void testGetSystemEnvironmentVariables() {
        // Test
        final Map<String, String> systemEnvironmentVariables =
                settingsSummaryProblemType.getSystemEnvironmentVariables();
        assert systemEnvironmentVariables.containsKey(TEST_KEY_1);
        assert systemEnvironmentVariables.containsKey(TEST_KEY_2);
        assert systemEnvironmentVariables.containsKey(TEST_KEY_3);

        assert systemEnvironmentVariables.containsValue(TEST_VALUE_1);
        assert systemEnvironmentVariables.containsValue(TEST_VALUE_2);
        assert systemEnvironmentVariables.containsValue(TEST_VALUE_3);

        assert systemEnvironmentVariables.get(TEST_KEY_1).equals(TEST_VALUE_1);
        assert systemEnvironmentVariables.get(TEST_KEY_2).equals(TEST_VALUE_2);
        assert systemEnvironmentVariables.get(TEST_KEY_3).equals(TEST_VALUE_3);
    }
}