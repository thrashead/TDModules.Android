package com.sinasalik.thrashead.tdframework;

import java.util.ArrayList;

public class WhereArgs {
    private String WhereClauses;
    private String[] Arguments;

    public String getWhereClauses() {
        return WhereClauses;
    }

    public String[] getArguments() {
        return Arguments;
    }

    public void setWhereClauses(String whereClauses) {
        WhereClauses = whereClauses;
    }

    public void setArguments(String[] arguments) {
        Arguments = arguments;
    }

    public static WhereArgs CreateArguments(ArrayList<Where> whereList) {
        WhereArgs whereArgs = new WhereArgs();
        ArrayList<String> args = new ArrayList();
        String whereClause = "";
        String whereClauseTemp;
        int i = 0;

        if (whereList != null) {
            for (Where where : whereList) {
                whereClauseTemp = "";

                if (i > 0) {
                    if (where.getKnots() == Where.Knots.And) {
                        whereClauseTemp = " and " + where.getOpenPharanthesis() + whereClauseTemp;
                    } else if (where.getKnots() == Where.Knots.Or) {
                        whereClauseTemp = " or " + where.getOpenPharanthesis() + whereClauseTemp;
                    }
                } else {
                    whereClauseTemp = " " + where.getOpenPharanthesis() + whereClauseTemp;
                }

                whereClause += whereClauseTemp;

                switch (where.getOperators()) {
                    case Equal:
                        args.add(where.getValues().get(0));
                        whereClause += " " + where.getColumn() + " = ? ";
                        break;
                    case Greater:
                        args.add(where.getValues().get(0));
                        whereClause += " " + where.getColumn() + " > ? ";
                        break;
                    case GreaterEqual:
                        args.add(where.getValues().get(0));
                        whereClause += " " + where.getColumn() + " >= ? ";
                        break;
                    case Smaller:
                        args.add(where.getValues().get(0));
                        whereClause += " " + where.getColumn() + " < ? ";
                        break;
                    case SmallerEqual:
                        args.add(where.getValues().get(0));
                        whereClause += " " + where.getColumn() + " <= ? ";
                        break;
                    case Like:
                        args.add("%" + where.getValues().get(0) + "%");
                        whereClause += " " + where.getColumn() + " like ? ";
                        break;
                    case StartLike:
                        args.add("%" + where.getValues().get(0));
                        whereClause += " " + where.getColumn() + " like ? ";
                        break;
                    case EndLike:
                        args.add(where.getValues().get(0) + "%");
                        whereClause += " " + where.getColumn() + " like ? ";
                        break;
                    case ExactLike:
                        args.add(where.getValues().get(0));
                        whereClause += " " + where.getColumn() + " like ? ";
                        break;
                    case Between:
                        args.add(where.getValues().get(0) + " and " + where.getValues().get(1));
                        whereClause += " " + where.getColumn() + " between ? ";
                        break;
                    case In:
                        String argString = "";

                        int indis = 0;

                        for (String str : where.getValues()) {
                            if (indis < where.getValues().size() - 1) {
                                argString += str + ",";
                            }
                            else {
                                argString += str;
                            }

                            indis++;
                        }

                        args.add(argString);
                        whereClause += " " + where.getColumn() + " In (?) ";
                        break;
                }

                whereClause = whereClause + where.getClosePharanthesis();

                i++;
            }
        }

        whereArgs.setWhereClauses(whereClause);

        whereArgs.setArguments(args.toArray(new String[args.size()]));

        return whereArgs;
    }
}
