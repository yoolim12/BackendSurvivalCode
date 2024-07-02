package com.yoolim.api.rest.demo.domain;

import java.util.Objects;
import java.util.UUID;

public class PostId {
    private String id;

    public PostId(String id) {
        this.id = id;
    }

    public static PostId of(String id) {
        return new PostId(id);
    }

    public static PostId generate() {
        return new PostId(UUID.randomUUID().toString().replace("-", ""));
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return Objects.equals(id, postId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int compareTo(PostId other) {
        return this.id.compareTo(other.toString());
    }
}
