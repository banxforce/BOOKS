import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;

public class Main {
    private static List<Book> list;

    private static void init() {
        File file = new File("localData.txt");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            list = (List<Book>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            list = new ArrayList<>();
        }
    }
    private static void save(){
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("localData.txt"))){
            out.writeObject(list);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private static Book getBook(Scanner sc){
        //sc.nextLine();
        Book book=new Book();
        System.out.print("请输入书籍名称:");
        book.setTitle(sc.nextLine());
        System.out.print("请输入书籍的作者:");
        book.setWriter(sc.nextLine());
        while (true) {
            try {
                System.out.print("请输入书籍的价格:");
                double price = sc.nextDouble();
                book.setPrice(price);
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
            }
        }
        return book;
    }
    private static int idCheck(Scanner sc, String operation){
        int id;
        while (true) {
            try {
                System.out.print("输入要"+operation+"的书籍编号:");
                id = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                continue;
            }
            if(id<0||id>=list.size()){
                System.out.println("范围有误，重新输入！");
                continue;
            }
            break;
        }
        sc.nextLine();
        return id;
    }
    private static void contents(){
        System.out.println("==========图书管理系统==========");
        System.out.println("1. 录入书籍信息");
        System.out.println("2. 查询书籍信息");
        System.out.println("3. 删除书籍信息");
        System.out.println("4. 修改书籍信息");
        System.out.println("5. 退出系统");
        System.out.println("=============================");
    }
    private static void insert(Scanner sc){
        Book book=getBook(sc);
        list.add(book);
        System.out.println("书籍录入成功:"+book);
    }
    private static void view(){
        for(int i=0;i<list.size();i++){
            System.out.println(i+"."+list.get(i));
        }
    }
    private static void delete(Scanner sc){
        int id=idCheck(sc,"删除");
        while(true) {
            System.out.print("是否删除编号为" + id + "的书籍信息(y/n):");
            String op=sc.nextLine();
            if(op.equals("y") || op.equals("Y")){
                list.remove(id);
                System.out.println("删除成功！");
                return;
            }
            else if(op.equals("n") || op.equals("N")) {
                System.out.println("取消删除操作");
                return;
            }
        }
    }
    private static void update(Scanner sc){
        int id=idCheck(sc,"修改");
        Book book=getBook(sc);
        list.set(id,book);
    }
    private static boolean choose(Scanner sc){
        try{
            int op=sc.nextInt();
            sc.nextLine();
            switch (op){
                case 1:
                    insert(sc);
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    delete(sc);
                    break;
                case 4:
                    update(sc);
                    break;
                case 5:
                    System.out.println("正在保存图书数据。。。");
                    save();
                    System.out.println("感谢你的使用，再见！");
                    System.exit(0);
                    break;

            }
        } catch(InputMismatchException e){
            sc.nextLine();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        init();
        Scanner sc=new Scanner(System.in);
        while(true){
            contents();
            if(!choose(sc)){
                System.out.println("请输入正确的指令(1-5)");
            }
        }

    }

}