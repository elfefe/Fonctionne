package com.elfefe.fonctionne.mathengine.parser;

public interface Parser<T, R> {
    R parse(T t);
}
