package com.xuxinpei.blog.vo;

import java.util.ArrayList;
import java.util.List;

public class ExpressionChain {
    private List<Expression> expressionList;

    public ExpressionChain() {
        this.expressionList = new ArrayList();
    }

    public ExpressionChain and(Expression expression) {
        this.expressionList.add(expression);
        return this;
    }

    public List<Expression> getExpressionList() {
        return this.expressionList;
    }

    public void setExpressionList(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }
}