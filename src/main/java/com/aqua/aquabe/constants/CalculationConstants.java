package com.aqua.aquabe.constants;

import java.util.function.BiFunction;

public class CalculationConstants {
    public enum Operation { // Java7 이하
        PLUS {
            public double apply(double x, double y) {
                return x + y;
            }
        },
        MINUS {
            public double apply(double x, double y) {
                return x - y;
            }
        },
        TIMES {
            public double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE {
            public double apply(double x, double y) {
                return x / y;
            }
        };

        public abstract double apply(double x, double y); // Java7 이하, 추상메소드 구현
        // Operation.PLUS.apply(1, 2); // 3.0
    }

    public enum PriceOperation { // Java 1.8 이상
        ORIGIN((value1, value2) -> value1 + value2), // 람다 표현식
        VAT((value1, value2) -> (value1 + value2) / 11);

        // BiFunction Interface는 Java에서 함수형 프로그래밍을 구현하기 위해 Java 버전 1.8부터 도입된 함수형 인터페이스
        // 두 개(이상)의 매개변수를 전달받아 작업을 수행 후 새로운 값을 반환
        private final BiFunction<Double, Double, Double> expression;

        PriceOperation(BiFunction<Double, Double, Double> expression) {
            this.expression = expression;
        }

        public double apply(double value1, double value2) {
            return expression.apply(value1, value2);
        }
    } // PriceOperation.ORIGIN.apply(1000, 1000); // 2000.0
}
