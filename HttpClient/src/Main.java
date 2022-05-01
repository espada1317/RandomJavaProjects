import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void getCategoryMenu()
    {
        System.out.println("1. Get category by ID");
        System.out.println("2. Get category by name");
        System.out.println("--------------------\n");
    }

    public static void showMenu()
    {
        System.out.println("\n\n\n---- Menu List: ----");
        System.out.println("a. Get list of all categories");
        System.out.println("b. Get category by ID/name");
        System.out.println("c. Create new category");
        System.out.println("d. Delete a category");
        System.out.println("e. Modify name of category");
        System.out.println("f. Create new products in specific category");
        System.out.println("g. List all products for specific category");
        System.out.println("h. Exit app");
        System.out.println("--------------------\n");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClass httpClass = new HttpClass();

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            showMenu();
            System.out.println("Select operation from menu: ");
            String selectedOption = scanner.nextLine();

            switch (selectedOption)
            {
                case "a" : {
                    httpClass.getAllCategoriesRequest();
                    break;
                }
                case "b" : {
                    getCategoryMenu();
                    System.out.println("Select operation from menu: ");
                    String selectSubOption = scanner.nextLine();

                    if (selectSubOption.equals("1"))
                    {
                        System.out.println("Type ID of category: ");
                        String idCateg = scanner.nextLine();
                        httpClass.getCategoryByIdRequest(Integer.parseInt(idCateg));
                        break;
                    }
                    if (selectSubOption.equals("2"))
                    {
                        System.out.println("Type name of category: ");
                        String nameCateg = scanner.next();
                        httpClass.getCategoryByNameRequest(nameCateg);
                        break;
                    }
                    break;
                }
                case "c" : {
                    System.out.println("Give name for category: ");
                    String nameCateg = scanner.nextLine();
                    httpClass.postCategoryRequest(nameCateg);
                    break;
                }
                case "d" : {
                    System.out.println("Give ID for category to delete: ");
                    String idCateg = scanner.nextLine();
                    httpClass.deleteCategoryByIdRequest(Integer.parseInt(idCateg));
                    break;
                }
                case "e" : {
                    System.out.println("Give ID for category to modify: ");
                    String idCateg = scanner.nextLine();
                    System.out.println("Give name for modifying: ");
                    String nameCateg = scanner.nextLine();
                    httpClass.putModifyCategoryName(Integer.parseInt(idCateg), nameCateg);
                    break;
                }
                case "f" : {
                    System.out.println("Give ID for category: ");
                    String idCateg = scanner.nextLine();
                    System.out.println("Give ID for product: ");
                    String idProduct = scanner.nextLine();
                    System.out.println("Give title for product: ");
                    String titleProd = scanner.nextLine();
                    System.out.println("Give price for product: ");
                    String priceProd = scanner.nextLine();
                    httpClass.postProductRequest(Integer.parseInt(idCateg), Integer.parseInt(idProduct), titleProd, Integer.parseInt(priceProd));
                    break;
                }
                case "g" : {
                    System.out.println("Type ID of category: ");
                    String idCateg = scanner.nextLine();
                    httpClass.getProductsFromCategoryID(Integer.parseInt(idCateg));
                    break;
                }
                case "h" : {
                    return;
                }
            }
        }

    }
}
