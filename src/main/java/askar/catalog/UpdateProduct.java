package askar.catalog;

import askar.catalog.entity.Option;
import askar.catalog.entity.Product;
import askar.catalog.entity.Value;
import com.sun.source.tree.PatternTree;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateProduct {


    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);


//
//        try {
//            manager.getTransaction().begin();
//
//            System.out.println("Введите id товара");
//            Long productId = Long.parseLong(scanner.nextLine());
//
//            Product product = manager.find(Product.class,productId);
//
//
//            Category category = product.getCategory();
//
//            List<Option> optionList = category.getOptions();
//
//
//
//
//
//
////                        System.out.println("Изменить название");
////            String newName = scanner.nextLine();
////            product.setName(newName);
////            System.out.println("Измениить цену");
////            int newPrice = Integer.parseInt(scanner.nextLine());
////            product.setPrice(newPrice);
//
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            manager.getTransaction().rollback();
//        }


        System.out.println("Введите id товара");
        Long productId = Long.parseLong(scanner.nextLine());


        try {
            manager.getTransaction().begin();

            Product product = manager.find(Product.class, productId);


            System.out.println("Изменить название");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()){
                product.setName(newName);
            }

            System.out.println("Изменить цену");

            String newPrice = scanner.nextLine();
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(newPrice);


            if (!newPrice.isEmpty()){
                    while (!matcher.find()){
                        System.out.println("Введите корректно");
                        newPrice = scanner.nextLine();
                        matcher = pattern.matcher(newPrice);
                    }
                    product.setPrice(Integer.parseInt(newPrice));
            }


            List<Option> options = product.getCategory().getOptions();
            for (Option option : options) {
                TypedQuery<Value> valueTypedQuery = manager.createQuery(
                        "select  v from Value  v   where v.product.id = ?1 and v.option.id = ?2", Value.class);
                valueTypedQuery.setParameter(1, product.getId());
                valueTypedQuery.setParameter(2, option.getId());

                  try {
                     if (!valueTypedQuery.getSingleResult().getValue().isEmpty()){
                         System.out.println(option.getName() + " " + "Изменить значение");
                         String newVal = scanner.nextLine();
                         if (!newVal.isEmpty()){
                             valueTypedQuery.getSingleResult().setValue(newVal);
                         }
                     }
                  }catch (Exception exception){
                      System.out.println(option.getName() + " " + "Добавьте значение");
                      String newVal = scanner.nextLine();
                      Value value = new Value();
                      value.setValue(newVal);
                      value.setProduct(product);
                      value.setOption(option);
                      manager.persist(value);
             }

            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        }

    }
}
