import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID=123456;
    private String title;
    private String writer;
    private double price;

    public Book(String title, String writer, double price) {
        this.title = title;
        this.writer = writer;
        this.price = price;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "《" + title + "》" +
                ", 作者:" + writer +
                ", 价格:" + price +
                " ￥}";
    }
}
