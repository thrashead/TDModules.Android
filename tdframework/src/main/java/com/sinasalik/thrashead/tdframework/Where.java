package com.sinasalik.thrashead.tdframework;

import java.util.ArrayList;

public class Where {
    public String column;
    public ArrayList<String> values;
    public String openPharanthesis;
    public String closePharanthesis;
    public Operators operators;
    public Knots knots;

    public Where() {
        this.operators = Operators.Equal;
        this.knots = Knots.And;
        this.openPharanthesis = "";
        this.closePharanthesis = "";
        this.values = new ArrayList<>();
    }

    public String getColumn() {
        return column;
    }

    public String getOpenPharanthesis() {
        return openPharanthesis;
    }

    public String getClosePharanthesis() {
        return closePharanthesis;
    }

    public Operators getOperators() {
        return operators;
    }

    public Knots getKnots() {
        return knots;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setOpenPharanthesis(String openPharanthesis) {
        this.openPharanthesis = openPharanthesis;
    }

    public void setClosePharanthesis(String closePharanthesis) {
        this.closePharanthesis = closePharanthesis;
    }

    public void setOperators(Operators operators) {
        this.operators = operators;
    }

    public void setKnots(Knots knots) {
        this.knots = knots;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public enum Knots {
        And,
        Or
    }

    public enum Operators {
        Equal,
        Greater,
        GreaterEqual,
        Smaller,
        SmallerEqual,
        Like,
        StartLike,
        EndLike,
        ExactLike,
        Between,
        In
    }
}
