package lab1;

import java.util.Scanner;

public class console_interface {
    private Scanner input;
    private complex_matrix current;

    public console_interface() {
        input = new Scanner(System.in);
    }

    public void run() {
        System.out.println("калькулятор комплексных матриц");

        while (true) {
            print_menu();

            System.out.print("выберите действие: ");
            String choice = input.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println("пока!");
                break;
            }

            try {
                execute(choice);
            } catch (IllegalArgumentException e) {
                System.out.println("ошибка: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("ошибка: " + e.getMessage());
            }
        }
    }

    private void print_menu() {
        System.out.println();
        System.out.println("1 - создать матрицу");
        System.out.println("2 - показать матрицу");
        System.out.println("3 - сложить матрицы");
        System.out.println("4 - вычесть матрицы");
        System.out.println("5 - умножить матрицы");
        System.out.println("6 - транспонировать");
        System.out.println("7 - найти определитель");
        System.out.println("0 - выход");
    }

    private void execute(String choice) {
        if (choice.equals("1")) {
            current = read_matrix();
            print_matrix(current);
        }

        else if (current == null) {
            System.out.println("сначала создай матрицу");
        }

        else if (choice.equals("2")) {
            print_matrix(current);
        }

        else if (choice.equals("3")) {
            System.out.println("введи вторую матрицу:");
            complex_matrix other = read_matrix();

            current = current.add(other);
            print_matrix(current);
        }

        else if (choice.equals("4")) {
            System.out.println("введи вторую матрицу:");
            complex_matrix other = read_matrix();

            current = current.subtract(other);
            print_matrix(current);
        }

        else if (choice.equals("5")) {
            System.out.println("введи вторую матрицу:");
            complex_matrix other = read_matrix();

            current = current.multiply(other);
            print_matrix(current);
        }

        else if (choice.equals("6")) {
            current = current.transpose();
            print_matrix(current);
        }

        else if (choice.equals("7")) {
            complex det = current.determinant();
            System.out.println("определитель: " + format(det));
        }

        else {
            System.out.println("такого пункта нет");
        }
    }

    private complex_matrix read_matrix() {
        int rows = read_number("количество строк: ");
        int columns = read_number("количество столбцов: ");

        complex[][] matrix = new complex[rows][columns];

        System.out.println("вводи действительную и мнимую часть через пробел");
        System.out.println("например: 3 -4");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = read_complex(i, j);
            }
        }

        return new complex_matrix(matrix);
    }

    private int read_number(String message) {
        while (true) {
            System.out.print(message);
            String line = input.nextLine().trim();

            try {
                int number = Integer.parseInt(line);

                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("это не целое число");
                continue;
            }

            System.out.println("введи положительное число");
        }
    }

    private complex read_complex(int row, int column) {
        while (true) {
            System.out.print("элемент [" + (row + 1) + "][" + (column + 1) + "]: ");

            String line = input.nextLine().trim();
            line = line.replace(',', '.');

            String[] parts = line.split("\\s+");

            if (parts.length != 2) {
                System.out.println("введи два числа через пробел");
                continue;
            }

            try {
                double re = Double.parseDouble(parts[0]);
                double im = Double.parseDouble(parts[1]);

                if (!Double.isFinite(re) || !Double.isFinite(im)) {
                    System.out.println("числа должны быть конечными");
                    continue;
                }

                return new complex(re, im);

            } catch (NumberFormatException e) {
                System.out.println("неверный ввод, попробуй ещё раз");
            }
        }
    }

    private void print_matrix(complex_matrix matrix) {
        System.out.println();
        System.out.println("матрица:");

        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.columns(); j++) {
                System.out.print(format(matrix.get(i, j)) + "    ");
            }

            System.out.println();
        }
    }

    private String format(complex number) {
        double re = number.real();
        double im = number.imaginary();

        if (im == 0) {
            return "" + re;
        }

        if (re == 0) {
            return im + "i";
        }

        if (im < 0) {
            return re + " - " + Math.abs(im) + "i";
        }

        return re + " + " + im + "i";
    }
}