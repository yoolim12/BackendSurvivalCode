package com.yoolim.api.rest.demo.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultilineText {
    private final List<String> lines;

    public MultilineText(String text) {
        this.lines = Arrays.asList(text.split("\n"));
    }

    public static MultilineText of(String text) {
        return new MultilineText(text);
    }

    @Override
    public String toString() {
        return lines.stream().collect(Collectors.joining("\n")); // lines라는 리스트 내의 요소들을 조인한다("\n"를 중간에 넣어서 조인)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultilineText that = (MultilineText) o;
        return Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }
}
