package askar.catalog;

import askar.catalog.entity.Category;
import askar.catalog.entity.Product;
import askar.catalog.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class DeleteProduct {
    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            manager.getTransaction().begin();

            Product product = manager.find(Product.class,id);
            List<Value> valueList = product.getValues();

           for (Value value : valueList){
               manager.remove(value);
           }
          manager.remove(product);




         manager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            manager.getTransaction().rollback();
        }


    }
}
