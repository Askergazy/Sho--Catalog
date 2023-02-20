package askar.catalog;

import askar.catalog.entity.Category;
import askar.catalog.entity.Product;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Jpa {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = entityManagerFactory.createEntityManager();

         // select c.* from categories c where c.id = ?
//        Category category= manager.find(Category.class,1L);
//        System.out.println(category.getName());

//        Product product = manager.find(Product.class,2L);
//        System.out.println(product.getCategory().getName());
//        System.out.println(product.getName());
//        System.out.println(product.getPrice());

//        Category category = manager.find(Category.class,1L);
//        List<Product>products = category.getProducts();
//
//        for (Product product : products){
//            System.out.printf("%s (%d)%n", product.getName(),product.getPrice());
//        }

//                Категория

//        try {
//            manager.getTransaction().begin();
//
//            Category category = new Category();
//            category.setName("Процессоры");
//            manager.persist(category);
//
//            manager.getTransaction().commit();
//        }catch (Exception e){
//               manager.getTransaction().rollback();
//              e.printStackTrace();
//        }

        // Products

        try {
            manager.getTransaction().begin();


        }catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
