package com.aqua.aquabe.service.sample;

import com.aqua.aquabe.constants.LocationEnumConstants.Division;

public class EnumSampleService {
    public void TestEnumSample() {
        Division divisionEnum = Division.valueOf("KOREA"); // String -> Enum
        Division divisionEnum2 = Division.valueOfCode("ko"); // Value -> Enum

        String divisionStr = Division.KOREA.name(); // Enum -> String
        String divisionLangCd = Division.KOREA.getLanguageCode(); // Enum -> Value

        System.out.println(divisionEnum);
        System.out.println(divisionEnum2);
        System.out.println(divisionStr);
        System.out.println(divisionLangCd);
    }
}
