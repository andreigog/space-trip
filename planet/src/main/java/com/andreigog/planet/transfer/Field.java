package com.andreigog.planet.transfer;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.EqualsAndHashCode;
import static java.util.Objects.requireNonNull;

/**
 * similar to Optional, but with an extra null as an acceptable value
 * can be used on PATCH requests where you want to distinguish between no input and nullable input
 * if optional field would have been used it would contain: no input -> null; null input -> Optional.empty(); value input -> Optional.of(value)
 * field would contain: no input -> Field.empty(); null input -> Field.nil(); value input -> Field.of(value)
 *
 * @param <T>
 */
public abstract class Field<T> {

  public static <T> Field<T> of(T value) {
    return new Filled<>(value);
  }

  public static <T> Field<T> empty() {
    return new Empty<>();
  }

  public static <T> Field<T> nil() {
    return new Null<>();
  }

  public static <T> Field<T> ofNullable(T value) {
    return Optional.ofNullable(value).map(Field::of).orElseGet(Field::nil);
  }

  public abstract <U> Field<U> map(Function<? super T, ? extends U> mapper);

  public abstract <U> Field<U> flatMap(Function<? super T, ? extends Field<? extends U>> mapper);

  public abstract T get();

  public abstract T orElse(T alternative);

  public abstract T orElseGet(Supplier<T> alternative);

  public abstract Field<T> filter(Predicate<T> condition);

  public abstract void ifPresent(Consumer<T> action);

  public abstract boolean isPresent();

  public abstract boolean isNull();

  public abstract boolean isEmpty();

  public abstract boolean isFilled();

  public abstract void ifFilledOrElse(Consumer<? super T> action, Runnable emptyAction, Runnable nullAction);

  @EqualsAndHashCode
  public static class Filled<T> extends Field<T> {

    private final T value;

    public Filled(T value) {
      this.value = requireNonNull(value);
    }

    @Override
    public <U> Field<U> map(Function<? super T, ? extends U> mapper) {
      return Field.of(mapper.apply(value));
    }

    @Override
    public <U> Field<U> flatMap(Function<? super T, ? extends Field<? extends U>> mapper) {
      return (Field<U>) mapper.apply(value);
    }

    @Override
    public T get() {
      return value;
    }

    @Override
    public T orElse(T alternative) {
      return value;
    }

    @Override
    public T orElseGet(Supplier<T> alternative) {
      return value;
    }

    @Override
    public Field<T> filter(Predicate<T> condition) {
      return condition.test(value) ? Field.of(value) : Field.empty();
    }

    @Override
    public void ifPresent(Consumer<T> action) {
      action.accept(value);
    }

    @Override
    public boolean isPresent() {
      return true;
    }

    @Override
    public boolean isNull() {
      return false;
    }

    @Override
    public boolean isEmpty() {
      return false;
    }

    @Override
    public boolean isFilled() {
      return true;
    }

    @Override
    public String toString() {
      return String.format("Field[%s]", value);
    }

    @Override
    public void ifFilledOrElse(Consumer<? super T> action, Runnable emptyAction, Runnable nullAction) {
      action.accept(get());
    }
  }

  @EqualsAndHashCode
  public static class Empty<T> extends Field<T> {

    @Override
    public <U> Field<U> map(Function<? super T, ? extends U> mapper) {
      return Field.empty();
    }

    @Override
    public <U> Field<U> flatMap(Function<? super T, ? extends Field<? extends U>> mapper) {
      return Field.empty();
    }

    @Override
    public T get() {
      throw new NoSuchElementException("No value present");
    }

    @Override
    public T orElse(T alternative) {
      return alternative;
    }

    @Override
    public T orElseGet(Supplier<T> alternative) {
      return alternative.get();
    }

    @Override
    public Field<T> filter(Predicate<T> condition) {
      return Field.empty();
    }

    @Override
    public void ifPresent(Consumer<T> action) {
    }

    @Override
    public boolean isPresent() {
      return false;
    }

    @Override
    public boolean isNull() {
      return false;
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public boolean isFilled() {
      return false;
    }

    @Override
    public String toString() {
      return "Field.empty";
    }

    @Override
    public void ifFilledOrElse(Consumer<? super T> action, Runnable emptyAction, Runnable nullAction) {
      emptyAction.run();
    }
  }

  @EqualsAndHashCode
  public static class Null<T> extends Field<T> {

    @Override
    public <U> Field<U> map(Function<? super T, ? extends U> mapper) {
      return nil();
    }

    @Override
    public <U> Field<U> flatMap(Function<? super T, ? extends Field<? extends U>> mapper) {
      return nil();
    }

    @Override
    public T get() {
      return null;
    }

    @Override
    public T orElse(T alternative) {
      return null;
    }

    @Override
    public T orElseGet(Supplier<T> alternative) {
      return null;
    }

    @Override
    public Field<T> filter(Predicate<T> condition) {
      return empty();
    }

    @Override
    public void ifPresent(Consumer<T> action) {
      action.accept(null);
    }

    @Override
    public boolean isPresent() {
      return true;
    }

    @Override
    public boolean isNull() {
      return true;
    }

    @Override
    public boolean isEmpty() {
      return false;
    }

    @Override
    public boolean isFilled() {
      return false;
    }

    @Override
    public String toString() {
      return "Field.null";
    }

    @Override
    public void ifFilledOrElse(Consumer<? super T> action, Runnable emptyAction, Runnable nullAction) {
      nullAction.run();
    }
  }
}
