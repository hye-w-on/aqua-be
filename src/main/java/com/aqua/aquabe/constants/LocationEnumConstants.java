package com.aqua.aquabe.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public class LocationEnumConstants {

    public enum Division {
        KOREA("ko", LocationType.DOMESTIC),
        USA("en", LocationType.INTERNATIONAL),
        JAPAN("jp", LocationType.INTERNATIONAL),
        CHINA("cn", LocationType.INTERNATIONAL);

        private final String languageCode;
        private final LocationType locationType;

        private Division(String languageCode, LocationType locationType) {
            this.languageCode = languageCode;
            this.locationType = locationType;
        }

        public String getLanguageCode() {
            return this.languageCode;
        }

        public LocationType getLocationType() {
            return this.locationType;
        }

        // Case 1 : languageCode -> Enum
        public static Division valueOfCode(String langCd) {
            return Arrays.stream(values())
                    .filter(value -> value.languageCode.equals(langCd))
                    .findAny()
                    .orElse(null);
        }

        // Case 2 : languageCode -> Enum (with cache)
        private static final Map<String, Division> codeMap = Stream.of(values())
                .collect(Collectors.toMap(Division::getLanguageCode, e -> e));

        public static Division valueOfCodeWithCache(String langCd) {
            return codeMap.get(langCd);
        }

    }

    public enum LocationType {
        DOMESTIC,
        INTERNATIONAL,
    }
}