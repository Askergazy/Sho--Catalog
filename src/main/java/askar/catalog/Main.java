package askar.catalog;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        System.out.println("Выберите действие!");
        System.out.println("- Создать катергоию [1]");
        System.out.println("- Создать товар [2]");
        System.out.println("- Удалить товар [3]");
        System.out.println("- Обновить товар [4]");

        int input = scanner.nextInt();

        if (input == 1){
            CreateCategory.createCategory();
        } else if (input == 2) {
            CreateProduct.createProduct();
        } else if (input == 3) {
            DeleteProduct.deleteProduct();
        }else if (input == 4){
            UpdateProduct.updateProduct();
        }


    }
}
