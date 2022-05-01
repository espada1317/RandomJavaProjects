import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpClass {
    private static final String POST_URL = "http://localhost:47170/api/Category/categories";
    private static final String POST_PUT_URL = "http://localhost:47170/api/Category";

    public void getAllCategoriesRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        List<CategoriesClass> categoryList = mapper.readValue(response.body(), new TypeReference<>() {});

        System.out.println("---- List of categories: ----");
        categoryList.forEach(System.out::println);
    }

    public void getCategoryByIdRequest(int idCategory) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_URL + "/" + idCategory))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(checkStatusCode(response.statusCode()))
        {
            ObjectMapper mapper = new ObjectMapper();
            List<CategoriesClass> categoryInfo = mapper.readValue(response.body(), new TypeReference<>() {});

            System.out.println("---- Category params: ----");
            categoryInfo.forEach(System.out::println);
        } else {
            System.out.println("Category doesn't exist!");
        }
    }

    public void getCategoryByNameRequest(String nameCategory) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_URL + "/search?categoryName=" + nameCategory))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(checkStatusCode(response.statusCode()))
        {
            getCategoryByIdRequest(Integer.parseInt(response.body()));
        } else {
            System.out.println("Category doesn't exist!");
        }
    }

    public void postCategoryRequest(String nameCategory) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\"title\" : \"" + nameCategory + "\"}"))
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(checkStatusCode(response.statusCode()))
        {
            ObjectMapper mapper = new ObjectMapper();
            List<CategoriesClass> categoryInfo = mapper.readValue("[" + response.body() + "]", new TypeReference<>() {});

            System.out.println("---- Created category params: ----");
            categoryInfo.forEach(System.out::println);
        } else {
            System.out.println("Can't create category!");
        }
    }

    public void deleteCategoryByIdRequest(int idCategory) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_URL + "/" + idCategory))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(checkStatusCode(response.statusCode()))
        {
            System.out.println("Category successfully deleted!");
        } else {
            System.out.println("Category can't be deleted!");
        }
    }

    public void putModifyCategoryName(int idCategory, String nameCategory) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString("{\"title\" : \"" + nameCategory + "\"}"))
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_PUT_URL + "/" + idCategory))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(checkStatusCode(response.statusCode()))
        {
            ObjectMapper mapper = new ObjectMapper();
            List<CategoriesClass> categoryInfo = mapper.readValue("[" + response.body() + "]", new TypeReference<>() {});

            System.out.println("---- Modified category params: ----");
            categoryInfo.forEach(System.out::println);
        } else {
            System.out.println("Can't change name of category or it don't exist!");
        }
    }

    public void getProductsFromCategoryID(int idCategory) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_URL + "/" + idCategory + "/products"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(checkStatusCode(response.statusCode()))
        {
            ObjectMapper mapper = new ObjectMapper();
            List<ProductsClass> categoryInfo = mapper.readValue(response.body(), new TypeReference<>() {});

            System.out.println("---- Products for category: ----");
            categoryInfo.forEach(System.out::println);
        } else {
            System.out.println("Category doesn't exist!");
        }
    }

    public void postProductRequest(int idCategory, int idProduct, String nameProduct, int priceProduct) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\"id\" : \"" + idProduct + "\",\"title\" : \"" + nameProduct + "\",\"price\":\"" + priceProduct + "\",\"categoryId\":\"" + idCategory + "\"}"))
                .header("Content-Type", "application/json")
                .uri(URI.create(POST_URL + "/" + idCategory + "/products"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(checkStatusCode(response.statusCode()))
        {
            ObjectMapper mapper = new ObjectMapper();
            List<ProductsClass> productInfo = mapper.readValue("[" + response.body() + "]", new TypeReference<>() {});

            System.out.println("---- Created product params: ----");
            productInfo.forEach(System.out::println);
        } else {
            System.out.println("Can't create product!");
        }
    }

    private Boolean checkStatusCode(int status)
    {
        if(status == 200)
        {
            return true;
        }

        return false;
    }

}