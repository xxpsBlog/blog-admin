package com.xxp.blog.vo;

import java.util.ArrayList;
import java.util.List;

public class VO {
    private List<ExpressionChain> expressionChainList;

    public VO() {
        this.expressionChainList = new ArrayList();
    }

    public void or(ExpressionChain expressionChain) {
        this.expressionChainList.add(expressionChain);
    }

    public void or(Expression expression) {
        this.expressionChainList.add(new ExpressionChain().and(expression));
    }

    public void and(Expression expression) {
        if (this.expressionChainList.isEmpty()) {
            this.expressionChainList.add(new ExpressionChain());
        }
        ((ExpressionChain) this.expressionChainList.get(0)).and(expression);
    }

    public List<ExpressionChain> getExpressionChainList() {
        return this.expressionChainList;
    }

    public void setExpressionChainList(List<ExpressionChain> expressionChainList) {
        this.expressionChainList = expressionChainList;
    }
}