package lox.types;

import java.util.List;

public abstract class Expr { 

  public abstract <R> R accept(ExprVisitor<R> visitor);

  public interface ExprVisitor<R> {
    R visitBinaryExpr(Binary expr);
    R visitGroupingExpr(Grouping expr);
    R visitLiteralExpr(Literal expr);
    R visitUnaryExpr(Unary expr);
  }

  public static class Binary extends Expr {
    public final Expr left;
    public final Token operator;
    public final Expr right;
    public Binary(Expr left, Token operator, Expr right) {
      this.left = left;
      this.operator = operator;
      this.right = right;
    }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
      return visitor.visitBinaryExpr(this);
    }

  }

  public static class Grouping extends Expr {
    public final Expr expression;
    public Grouping(Expr expression) {
      this.expression = expression;
    }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
      return visitor.visitGroupingExpr(this);
    }

  }

  public static class Literal extends Expr {
    public final Object value;
    public Literal(Object value) {
      this.value = value;
    }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
      return visitor.visitLiteralExpr(this);
    }

  }

  public static class Unary extends Expr {
    public final Token operator;
    public final Expr right;
    public Unary(Token operator, Expr right) {
      this.operator = operator;
      this.right = right;
    }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
      return visitor.visitUnaryExpr(this);
    }

  }

}

