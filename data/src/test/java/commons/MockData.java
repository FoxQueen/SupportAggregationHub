package commons;

import crm_connector.connectors.CRMConnectorType;

import java.util.List;
import java.util.Map;

public class MockData {
    public static final Case CASE_BANANA_0 = new Case(1L, 818591L, 6111L, 324L, Case.CaseStatus.CLOSED, "3/14/2019 16:30", "3/17/2019 03:41", "BLUE");
    public static final Case CASE_BANANA_1 = new Case(2L, 790521L, 26241L, 0L, Case.CaseStatus.CLOSED, "3/4/2019 09:30", "3/5/2019 02:47", "BLUE");
    public static final Case CASE_BANANA_2 = new Case(3L, 738081L, 1211L, 101L, Case.CaseStatus.CLOSED, "2/5/2019 00:30", "2/10/2019 01:52", "BLUE");
    public static final Case CASE_BANANA_3 = new Case(4L, 729841L, 10416L, 101L, Case.CaseStatus.CLOSED, "1/31/2019 06:31", "2/3/2019 11:42", "BLUE");
    public static final Case CASE_BANANA_4 = new Case(5L, 827331L, 11016L, 324L, Case.CaseStatus.CLOSED, "3/19/2019 15:30", "3/21/2019 08:54", "BLUE");
    public static final Case CASE_BANANA_5 = new Case(6L, 831011L, 2811L, 108L, Case.CaseStatus.CLOSED, "3/21/2019 06:33", "3/21/2019 10:35", "BLUE");
    public static final Case CASE_BANANA_6 = new Case(7L, 831071L, 12L, 101L, Case.CaseStatus.CLOSED, "3/21/2019 07:31", "3/21/2019 23:35", "BLUE");
    public static final Case CASE_BANANA_7 = new Case(8L, 831831L, 10896L, 101L, Case.CaseStatus.CLOSED, "3/21/2019 14:32", "3/21/2019 16:35", "BLUE");
    public static final Case CASE_BANANA_8 = new Case(9L, 792661L, 18121L, 195L, Case.CaseStatus.CLOSED, "3/4/2019 15:32", "3/4/2019 23:54", "BLUE");
    public static final Case CASE_BANANA_9 = new Case(10L, 723441L, 11990L, 103L, Case.CaseStatus.OPEN, "1/28/2019 15:30", "1/29/2019 06:07", "BLUE");
    public static final Case CASE_STRAWBERRY_0 = new Case(1L, 818591L, 10001121L, 101L, Case.CaseStatus.CLOSED, "4/1/2019 17:25", "4/2/2019 08:00", "RED");
    public static final Case CASE_STRAWBERRY_1 = new Case(2L, 790521L, 11196L, 108L, Case.CaseStatus.CLOSED, "3/22/2019 14:33", "3/23/2019 23:00", "GREEN");
    public static final Case CASE_STRAWBERRY_2 = new Case(3L, 738081L, 6111L, 324L, Case.CaseStatus.CLOSED, "3/14/2019 16:30", "3/17/2019 13:41", "ORANGE");
    public static final Case CASE_STRAWBERRY_3 = new Case(4L, 729841L, 11181L, 122L, Case.CaseStatus.CLOSED, "2/5/2019 17:32", "2/6/2019 01:06", "PINK");

    public static final ReducedCases REDUCED_CASES_BANANA_0 = new ReducedCases(List.of(CASE_BANANA_0));
    public static final ReducedCases REDUCED_CASES_BANANA_1 = new ReducedCases(List.of(CASE_BANANA_1));
    public static final ReducedCases REDUCED_CASES_BANANA_2 = new ReducedCases(List.of(CASE_BANANA_2));
    public static final ReducedCases REDUCED_CASES_BANANA_3 = new ReducedCases(List.of(CASE_BANANA_3));
    public static final ReducedCases REDUCED_CASES_BANANA_4 = new ReducedCases(List.of(CASE_BANANA_4));
    public static final ReducedCases REDUCED_CASES_BANANA_5 = new ReducedCases(List.of(CASE_BANANA_5));
    public static final ReducedCases REDUCED_CASES_BANANA_6 = new ReducedCases(List.of(CASE_BANANA_6));
    public static final ReducedCases REDUCED_CASES_BANANA_7 = new ReducedCases(List.of(CASE_BANANA_7));
    public static final ReducedCases REDUCED_CASES_BANANA_8 = new ReducedCases(List.of(CASE_BANANA_8));
    public static final ReducedCases REDUCED_CASES_BANANA_9 = new ReducedCases(List.of(CASE_BANANA_9));
    public static final ReducedCases REDUCED_CASES_STRAWBERRY_0 = new ReducedCases(List.of(CASE_STRAWBERRY_0));
    public static final ReducedCases REDUCED_CASES_STRAWBERRY_1 = new ReducedCases(List.of(CASE_STRAWBERRY_1));

    public static final Map<String, Object> MOCK_BANANA_CONNECTOR_CRM = Map.of (
            "type", CRMConnectorType.BANANA.name(),
            "endpoint", "https://fakebanky.herokuapp.com/fruit/banana"
    );

    public static final Map<String, Object> MOCK_STRAWBERRY_CONNECTOR_CRM = Map.of (
            "type", CRMConnectorType.STRAWBERRY.name(),
            "endpoint", "https://fakebanky.herokuapp.com/fruit/strawberry"
    );

    public static final Map<String, Object> MOCK_CACHE_CRMS = Map.of(
            "crms", List.of(MOCK_BANANA_CONNECTOR_CRM, MOCK_STRAWBERRY_CONNECTOR_CRM)
    );

    public static final List<Case> MOCK_RAW_BANANA_DATA = List.of(
            CASE_BANANA_0,
            CASE_BANANA_1,
            CASE_BANANA_2,
            CASE_BANANA_3,
            CASE_BANANA_4,
            CASE_BANANA_5,
            CASE_BANANA_6,
            CASE_BANANA_7,
            CASE_BANANA_8,
            CASE_BANANA_9
    );

    public static final List<Case> MOCK_STERILE_BANANA_DATA = List.of(
            CASE_BANANA_0,
            CASE_BANANA_1,
            CASE_BANANA_2,
            CASE_BANANA_3,
            CASE_BANANA_4,
            CASE_BANANA_5,
            CASE_BANANA_6,
            CASE_BANANA_7,
            CASE_BANANA_8,
            CASE_BANANA_9
    );

    public static final List<ReducedCases> MOCK_MAPPED_BANANA_DATA = List.of(
            REDUCED_CASES_BANANA_0,
            REDUCED_CASES_BANANA_1,
            REDUCED_CASES_BANANA_2,
            REDUCED_CASES_BANANA_3,
            REDUCED_CASES_BANANA_4,
            REDUCED_CASES_BANANA_5,
            REDUCED_CASES_BANANA_6,
            REDUCED_CASES_BANANA_7,
            REDUCED_CASES_BANANA_8,
            REDUCED_CASES_BANANA_9
    );

    public static final List<Case> MOCK_RAW_STRAWBERRY_DATA = List.of(
            CASE_STRAWBERRY_0,
            CASE_STRAWBERRY_1,
            CASE_STRAWBERRY_2,
            CASE_STRAWBERRY_3
    );

    public static final List<Case> MOCK_STERILE_STRAWBERRY_DATA = List.of(
            CASE_STRAWBERRY_0,
            CASE_STRAWBERRY_1
    );

    public static final List<ReducedCases> MOCK_MAPPED_STRAWBERRY_DATA = List.of(
            REDUCED_CASES_STRAWBERRY_0,
            REDUCED_CASES_STRAWBERRY_1
    );

    public static final List<ReducedCases> MOCK_FINAL_DATA = List.of(
            REDUCED_CASES_BANANA_0,
            REDUCED_CASES_BANANA_1,
            REDUCED_CASES_BANANA_2,
            REDUCED_CASES_BANANA_3,
            REDUCED_CASES_BANANA_4,
            REDUCED_CASES_BANANA_5,
            REDUCED_CASES_BANANA_6,
            REDUCED_CASES_BANANA_7,
            REDUCED_CASES_BANANA_8,
            REDUCED_CASES_BANANA_9,
            REDUCED_CASES_STRAWBERRY_0,
            REDUCED_CASES_STRAWBERRY_1
    );
}
