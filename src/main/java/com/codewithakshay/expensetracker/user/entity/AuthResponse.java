package com.codewithakshay.expensetracker.user.entity;

public class AuthResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private AuthResponse(Builder builder) {
        this.token = builder.token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(this);
        }

    }
}
