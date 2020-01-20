package com.br.github.gabrielotsuka.storesystem.error;

public class ConstraintViolationExceptionDetails {
    private String title;
    private int status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static final class Builder {
        private String title;
        private int status;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public ConstraintViolationExceptionDetails build() {
            ConstraintViolationExceptionDetails constraintViolationExceptionDetails = new ConstraintViolationExceptionDetails();
            constraintViolationExceptionDetails.setTitle(title);
            constraintViolationExceptionDetails.setStatus(status);
            return constraintViolationExceptionDetails;
        }
    }
}
