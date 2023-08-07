package askar.catalog;

import askar.catalog.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class CreateCategory {
    public  static void createCategory() {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);



        try{
            manager.getTransaction().begin();

            System.out.println("Введите название!");
            String name = scanner.nextLine(); 

            TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                    "select c from  Category c where  c.name = ?1 ",Category.class
            );
            categoryTypedQuery.setParameter(1,name);
            List<Category> categories = categoryTypedQuery.getResultList();

            if (categories.size() == 1){
                for (Category category : categories){
                    System.out.println(category.getName());
                }
            }else if (categories.size() == 0){
                System.out.println("Категория не найдена");
                System.out.println("1 - Добавить категорию");
                String input = scanner.nextLine();



                if (input.equals("1")){
                    Category category = new Category();
                    category.setName(name);
                    manager.persist(category);

                    System.out.println("Категория добавлена");
                }

            }

            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }





//         Характеристики
//        System.out.println("Введите характеристики");
//        String optionName = scanner.nextLine();
//        System.out.println("Введите category_id");
//        Long id = scanner.nextLong();
//        try {
//            manager.getTransaction().begin();
//            String [] options = optionName.split(",");
//            Category category = manager.find(Category.class,id);
//            manager.persist(category);
//            for (int i = 0; i < options.length; i++) {
//                System.out.println(options[i]);
//                Option option = new Option();
//                option.setName(options[i]);
//                option.setCategory(category);
//                manager.persist(option);
//            }
////
//            manager.getTransaction().commit();
//        }catch (Exception e){
//            manager.getTransaction().rollback();
//            e.printStackTrace();
//        }






    }
}
