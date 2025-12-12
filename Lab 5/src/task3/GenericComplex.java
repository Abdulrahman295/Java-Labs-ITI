package task3;

public class GenericComplex <T extends Number> {
    private T real;
    private T imaginary;

    public GenericComplex(T real, T imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public T getImaginary() {
        return imaginary;
    }

    public GenericComplex<T> addAsGeneric(GenericComplex<T> other) {
        T newReal;
        T newImaginary;

        if (this.real instanceof Double) {
            Double r = this.real.doubleValue() + other.real.doubleValue();
            Double i = this.imaginary.doubleValue() + other.imaginary.doubleValue();
            newReal = (T) r;
            newImaginary = (T) i;
        } else if (this.real instanceof Integer) {
            Integer r = this.real.intValue() + other.real.intValue();
            Integer i = this.imaginary.intValue() + other.imaginary.intValue();
            newReal = (T) r;
            newImaginary = (T) i;
        } else if (this.real instanceof Float) {
            Float r = this.real.floatValue() + other.real.floatValue();
            Float i = this.imaginary.floatValue() + other.imaginary.floatValue();
            newReal = (T) r;
            newImaginary = (T) i;
        } else if (this.real instanceof Long) {
            Long r = this.real.longValue() + other.real.longValue();
            Long i = this.imaginary.longValue() + other.imaginary.longValue();
            newReal = (T) r;
            newImaginary = (T) i;
        } else {
            throw new IllegalArgumentException("Type " + this.real.getClass().getSimpleName() + " is not supported.");
        }

        return new GenericComplex<>(newReal, newImaginary);
    }

    public GenericComplex<Double> addAsDouble(GenericComplex<T> other) {
        Double newReal = this.real.doubleValue() + other.real.doubleValue();
        Double newImaginary = this.imaginary.doubleValue()  + other.imaginary.doubleValue();
        return new GenericComplex<Double>(newReal, newImaginary);
    }

}
