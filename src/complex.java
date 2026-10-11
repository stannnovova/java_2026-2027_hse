public record complex(double real, double imaginary) {
    public complex add(complex other) {
        return new complex(this.real+other.real, this.imaginary+other.imaginary);
    }

    public complex subtract(complex other) {
        return new complex(this.real-other.real, this.imaginary-other.imaginary);
    }

    public complex multiply(complex other) {
        return new complex(this.real*other.real-this.imaginary*other.imaginary, this.real*other.imaginary+this.imaginary*other.real);
    }

    public complex devide(complex other) {
        double x = other.real*other.real+other.imaginary*other.imaginary;
        if (x == 0) {
            throw new ArithmeticException("деление на 0 запрещено");
        }
        return new complex((this.real*other.real+this.imaginary*other.imaginary)/x, (this.imaginary*other.real-this.real*other.imaginary)/x);
    }
}
