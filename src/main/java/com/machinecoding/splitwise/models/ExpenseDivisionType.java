package com.machinecoding.splitwise.models;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public enum ExpenseDivisionType {
    EQUAL {
        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitEqual();
        }
    },
    EXACT {
        @Override
        public <T> T visit(Visitor<T> visitor) throws Exception {
            return visitor.visitExact();
        }
    },
    PERCENT {
        @Override
        public <T> T visit(Visitor<T> visitor) throws Exception {
            return visitor.visitPercent();
        }
    };

    public abstract <T> T visit(Visitor<T> visitor) throws Exception;

    public interface Visitor<T> {

        T visitEqual();

        T visitExact() throws Exception;

        T visitPercent() throws Exception;

    }
}
