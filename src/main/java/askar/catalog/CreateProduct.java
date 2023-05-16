package askar.catalog;

import askar.catalog.entity.Category;
import askar.catalog.entity.Option;
import askar.catalog.entity.Product;
import askar.catalog.entity.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateProduct {

        public void CreateProduct(){
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                "select c from Category c order by c.id",Category.class);


        List<Category> categories = categoryTypedQuery.getResultList();
        for (Category category : categories){
            System.out.println(category.getName() + " " +category.getId());
        }

        System.out.println("Выберите категорию");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.println("Введите название") ;
        String productName = scanner.nextLine();

        System.out.println("Введите цену");
        Integer price =  Integer.parseInt(scanner.nextLine());

            try {
            manager.getTransaction().begin();

            Category category = manager.find(Category.class,id);

            Product product = new Product();

            product.setCategory(category);
            product.setName(productName);
            product.setPrice(price);
            manager.persist(product);

            TypedQuery<Option>optionTypedQuery = manager.createQuery(
                    "select o from Option o where o.category.id = ?1 order by o.id",Option.class);


                optionTypedQuery.setParameter(1,id);
                List<Option> options = optionTypedQuery.getResultList();

                System.out.println("Заполните характеристики");
                for (Option o : options){
                    System.out.println(o.getName());
                    String valInput = scanner.nextLine();
                    Value value = new Value();
                    value.setProduct(product);
                    value.setOption(o);
                    value.setValue(valInput);
                    manager.persist(value);
                 }

            manager.getTransaction().commit();
            System.out.println("Товар добавлен");
        }catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }













        }

}
