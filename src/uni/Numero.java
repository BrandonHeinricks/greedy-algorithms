package uni;

public class Numero {
    private int valor;

    public Numero(int num) {
        this.valor = num;
    }

    public Numero(Numero num) {
        this.valor = num.getNumInt();
    }

    public Numero(String num) {
        this.valor = Integer.parseInt(num);
    }

    public int getNumInt() {
        return this.valor;
    }

    public String getNumStr() {
        return String.valueOf(this.valor);
    }

    public int getLength() {
        return getNumStr().length();
    }

    public int getDigit(int pos_i) {
        return Character.getNumericValue(getNumStr().charAt(pos_i));
    }

    public void concatRight(Numero right) {
        if (this.valor == 0) {
            this.valor = right.getNumInt();
        } else {
            this.valor = Integer.parseInt(this.getNumStr() + right.getNumStr());
        }
    }

    public void concatLeft(Numero left) {
        if (this.valor == 0) {
            this.valor = left.getNumInt();
        } else {
            this.valor = Integer.parseInt(left.getNumStr() + this.getNumStr());
        }
    }

    @Override
    public String toString() {
        return getNumStr();
    }
}