package com.unsubble.evo.common;

public interface Holder<T> {
   void hold(T obj);

   boolean holds();

   T release();

   T get();
}
