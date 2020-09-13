/**
 * A container for a disjunction of two possible types
 * By convention, the Left constructor is used to hold an error value and the Right constructor is used to hold a correct value
 * @param <L> The left alternative type
 * @param <R> The right alternative type
 * add in slabe --
 */
public abstract class Either<L, R>
{
  public abstract <T> T match(Function<? super L, ? extends T> lf, Function<? super R, ? extends T> rf);

  public abstract void match(Consumer<? super L> lf, Consumer<? super R> rf);

  public <A, B> Either<A, B> bimap(Function<? super L, ? extends A> lf, Function<? super R, ? extends B> rf)
  {
    return match(
        (L l) -> left(lf.apply(l)),
        (R r) -> right(rf.apply(r))
    );
  }

  public L fromLeft(L left)
  {
    return match(
        (L l) -> l,
        (R r) -> left
    );
  }

  public R fromRight(R right)
  {
    return match(
        (L l) -> right,
        (R r) -> r
    );
  }

  public static <L, R> Either<L, R> left(L value)
  {
    return new Left<>(value);
  }

  public static <L, R> Either<L, R> right(R value)
  {
    return new Right<>(value);
  }

  private static <L, R> Either<L, R> cast(Either<? extends L, ? extends R> either)
  {
    return either.match(
        (L l) -> left(l),
        (R r) -> right(r)
    );
  }

  static class Left<L, R> extends Either<L, R>
  {
    final L value;

    Left(L value)
    {
      this.value = value;
    }

    @Override
    public <T> T match(Function<? super L, ? extends T> lf, Function<? super R, ? extends T> rf)
    {
      return lf.apply(value);
    }

    @Override
    public void match(Consumer<? super L> lf, Consumer<? super R> rf)
    {
      lf.accept(value);
    }
  }

  static class Right<L, R> extends Either<L, R>
  {
    final R value;

    Right(R value)
    {
      this.value = value;
    }

    @Override
    public <T> T match(Function<? super L, ? extends T> lf, Function<? super R, ? extends T> rf)
    {
      return rf.apply(value);
    }

    @Override
    public void match(Consumer<? super L> lf, Consumer<? super R> rf)
    {
      rf.accept(value);
    }
  }
}

