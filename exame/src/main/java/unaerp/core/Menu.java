package unaerp.core;

import unaerp.dto.CategoriesDTO;
import unaerp.dto.ProductsDTO;
import unaerp.dto.SuppliersDTO;
import unaerp.entity.Categories;
import unaerp.entity.Products;
import unaerp.entity.Suppliers;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);

    public void OpenMenu() {
        System.out.println("****************");
        System.out.println("1. Inserir nova categoria");
        System.out.println("2. Inserir novo produto");
        System.out.println("3. Inserir novo Fornecedor");
        System.out.println("4. Exibir todas categorias");
        System.out.println("5. Exibir todos produtos");
        System.out.println("6. Exibir todos fornecedores");
        System.out.println("7. Excluir Categoria");
        System.out.println("8. Excluir Produto");
        System.out.println("9. Excluir Fornecedor");
        System.out.println("10. Exibir categoria detalahada");
        System.out.println("11. Exibir produto detalahado");
        System.out.println("12. Exibir fornecedor detalahado");
        System.out.println("****************");

        //Scanner sc = new Scanner(System.in);

        switch (new Scanner(System.in).nextInt()) {
            case 1:
                insertCategorie();
                break;
            case 2:
                insertProduct();
                break;
            case 3:
                insertSupplier();
                break;
            case 4:
                showCategories();
                break;
            case 5:
                showProducts();
                break;
            case 6:
                showSuppliers();
                break;
            case 7:
                deleteCategorie();
                break;
            case 8:
                deleteProduct();
                break;
            case 9:
                deleteSupplier();
                break;
            case 10:
                showCategorie();
                break;
            case 11:
                showProduct();
                break;
            case 12:
                showSupplier();
                break;
            default:
                System.out.println("Tente novamente!");
                OpenMenu();

          sc.close();      
        }
    }

    private void insertCategorie() {
        System.out.println("Category name: ");
        final String name = new Scanner(System.in).next();
        System.out.println("Descricao: ");
        final String desc = new Scanner(System.in).next();
        System.out.println("Picture: ");
        final String pic = new Scanner(System.in).next();

        final Categories obj = new Categories(name, desc, pic);
        final CategoriesDTO dtoObj = new CategoriesDTO(obj.getCategoryName(), obj.getDescription(), obj.getPicture());

        final CategoryBO bo = new CategoryBO(selectDB());

        try {
            bo.insert(dtoObj);
        } catch (final SQLException e) {
            e.printStackTrace();
        }

      sc.close();  
    }

    private void insertProduct() {
        System.out.println("Name: ");
        final String name = new Scanner(System.in).next();
        System.out.println("Supplier id: ");
        final int supplierId = new Scanner(System.in).nextInt();
        System.out.println("Category id: ");
        final int categoryId = new Scanner(System.in).nextInt();
        System.out.println("quantityPerUnit: ");
        final int quantityPerUnit = new Scanner(System.in).nextInt();
        System.out.println("unitPrice: ");
        final double unitPrice = new Scanner(System.in).nextDouble();
        System.out.println("unitsInStock: ");
        final int unitsInStock = new Scanner(System.in).nextInt();
        System.out.println("unitsOnOrder: ");
        final int unitsOnOrder = new Scanner(System.in).nextInt();
        System.out.println("reorderLevel: ");
        final int reorderLevel = new Scanner(System.in).nextInt();
        System.out.println("discontinued: ");
        final int discontinued = new Scanner(System.in).nextInt();

        final ProductsDTO productsDTO = new ProductsDTO(name, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);


        final ProductBO bo = new ProductBO(selectDB());

        try {
            bo.insert(new ProductsDTO(
                    productsDTO.getProductName()
                    , productsDTO.getSupplierId()
                    , productsDTO.getCategoryId()
                    , productsDTO.getQuantityPerUnit()
                    , productsDTO.getUnitPrice()
                    , productsDTO.getUnitsInStock()
                    , productsDTO.getUnitsOnOrder()
                    , productsDTO.getReorderLevel()
                    , productsDTO.getDiscontinued()));
        } catch (final SQLException e) {
            e.printStackTrace();
        }
      
        sc.close();   
    }

    private void insertSupplier() {
        System.out.println("Company Name: ");
        final String compName = new Scanner(System.in).next();
        System.out.println("Contact Name: ");
        final String contaName = new Scanner(System.in).next();
        System.out.println("Contact Title: ");
        final String contaTitle = new Scanner(System.in).next();
        System.out.println("Address: ");
        final String address = new Scanner(System.in).next();
        System.out.println("City: ");
        final String city = new Scanner(System.in).next();
        System.out.println("Region: ");
        final String region = new Scanner(System.in).next();
        System.out.println("Postal Code: ");
        final String postalCode = new Scanner(System.in).next();
        System.out.println("country: ");
        final String country = new Scanner(System.in).next();
        System.out.println("Phone: ");
        final String phone = new Scanner(System.in).next();
        System.out.println("Fax: ");
        final String fax = new Scanner(System.in).next();
        System.out.println("HomePage: ");
        final String homepage = new Scanner(System.in).next();


        final Suppliers suppliers = new Suppliers(compName, contaName, contaTitle, address, city, region, postalCode, country, phone, fax, homepage);

        final SuppliersDTO suppliersDTO =
                new SuppliersDTO(suppliers.getCompanyName()
                        , suppliers.getContactName()
                        , suppliers.getContactTitle()
                        , suppliers.getAddress()
                        , suppliers.getCity()
                        , suppliers.getRegion()
                        , suppliers.getPostalCode()
                        , suppliers.getCountry()
                        , suppliers.getPhone()
                        , suppliers.getFax()
                        , suppliers.getHomePage());

        final SuppliersBO bo = new SuppliersBO(selectDB());

        try {
            bo.insert(suppliersDTO);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCategories() {
        final CategoryBO bo = new CategoryBO(selectDB());
        List<Categories> categoriesList = null;
        try {
            categoriesList = bo.getCategories();
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        for (final Categories categories : categoriesList) {
            System.out.println(categories.toString());
        }
    }

    private void showProducts() {
        final ProductBO bo = new ProductBO(selectDB());
        List<Products> productsList = null;
        try {
            productsList = bo.getProducts();
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        for (final Products products : productsList) {
            System.out.println(products.toString());
        }
    }

    private void showSuppliers() {
        final SuppliersBO bo = new SuppliersBO(selectDB());
        List<Suppliers> suppliersList = null;
        try {
            suppliersList = bo.getSuppliers();
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        for (final Suppliers sup : suppliersList) {
            System.out.println(sup.toString());
        }
    }

    private void deleteCategorie() {
        System.out.println("Informe o id");
        final int id = new Scanner(System.in).nextInt();
        final CategoryBO categoryBO = new CategoryBO(selectDB());
        try {
            categoryBO.delete(id);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct() {
        System.out.println("Informe o id");
        final int id = new Scanner(System.in).nextInt();
        final ProductBO categoryBO = new ProductBO(selectDB());
        try {
            categoryBO.delete(id);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSupplier() {
        System.out.println("Informe o id");
        final int id = new Scanner(System.in).nextInt();
        final SuppliersBO categoryBO = new SuppliersBO(selectDB());
        try {
            categoryBO.delete(id);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCategorie() {
        System.out.println("ID: ");
        final int id = new Scanner(System.in).nextInt();
        System.out.println("Category name: ");
        final String name = new Scanner(System.in).next();
        System.out.println("Descricao: ");
        final String desc = new Scanner(System.in).next();
        System.out.println("Picture: ");
        final String pic = new Scanner(System.in).next();

        final Categories obj = new Categories(id, name, desc, pic);
        final CategoriesDTO dtoObj = new CategoriesDTO(obj.getCategoryId(), obj.getCategoryName(), obj.getDescription(), obj.getPicture());

        final CategoryBO bo = new CategoryBO(selectDB());

        try {
            bo.update(dtoObj);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct() {
        System.out.println("ID: ");
        final int id = new Scanner(System.in).nextInt();
        System.out.println("Name: ");
        final String name = new Scanner(System.in).next();
        System.out.println("Supplier id: ");
        final int supplierId = new Scanner(System.in).nextInt();
        System.out.println("Category id: ");
        final int categoryId = new Scanner(System.in).nextInt();
        System.out.println("quantityPerUnit: ");
        final int quantityPerUnit = new Scanner(System.in).nextInt();
        System.out.println("unitPrice: ");
        final double unitPrice = new Scanner(System.in).nextDouble();
        System.out.println("unitsInStock: ");
        final int unitsInStock = new Scanner(System.in).nextInt();
        System.out.println("unitsOnOrder: ");
        final int unitsOnOrder = new Scanner(System.in).nextInt();
        System.out.println("reorderLevel: ");
        final int reorderLevel = new Scanner(System.in).nextInt();
        System.out.println("discontinued: ");
        final int discontinued = new Scanner(System.in).nextInt();

        final ProductsDTO productsDTO = new ProductsDTO(id, name, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);


        final ProductBO bo = new ProductBO(selectDB());

        try {
            bo.update(new ProductsDTO(productsDTO.getProductId()
                    , productsDTO.getProductName()
                    , productsDTO.getSupplierId()
                    , productsDTO.getCategoryId()
                    , productsDTO.getQuantityPerUnit()
                    , productsDTO.getUnitPrice()
                    , productsDTO.getUnitsInStock()
                    , productsDTO.getUnitsOnOrder()
                    , productsDTO.getReorderLevel()
                    , productsDTO.getDiscontinued()));
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSupplier() {
        System.out.println("ID: ");
        final int id = new Scanner(System.in).nextInt();
        System.out.println("Company Name: ");
        final String compName = new Scanner(System.in).next();
        System.out.println("Contact Name: ");
        final String contaName = new Scanner(System.in).next();
        System.out.println("Contact Title: ");
        final String contaTitle = new Scanner(System.in).next();
        System.out.println("Address: ");
        final String address = new Scanner(System.in).next();
        System.out.println("City: ");
        final String city = new Scanner(System.in).next();
        System.out.println("Region: ");
        final String region = new Scanner(System.in).next();
        System.out.println("Postal Code: ");
        final String postalCode = new Scanner(System.in).next();
        System.out.println("country: ");
        final String country = new Scanner(System.in).next();
        System.out.println("Phone: ");
        final String phone = new Scanner(System.in).next();
        System.out.println("Fax: ");
        final String fax = new Scanner(System.in).next();
        System.out.println("HomePage: ");
        final String homepage = new Scanner(System.in).next();

        final Suppliers suppliers = new Suppliers(id, compName, contaName, contaTitle, address, city, region, postalCode, country, phone, fax, homepage);

        final SuppliersDTO suppliersDTO =
                new SuppliersDTO(
                        suppliers.getSupplierId()
                        , suppliers.getCompanyName()
                        , suppliers.getContactName()
                        , suppliers.getContactTitle()
                        , suppliers.getAddress()
                        , suppliers.getCity()
                        , suppliers.getRegion()
                        , suppliers.getPostalCode()
                        , suppliers.getCountry()
                        , suppliers.getPhone()
                        , suppliers.getFax()
                        , suppliers.getHomePage());

        final SuppliersBO bo = new SuppliersBO(selectDB());

        try {
            bo.update(suppliersDTO);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCategorie() {
        System.out.println("Informe o id");
        final int id = new Scanner(System.in).nextInt();
        final CategoryBO categoryBO = new CategoryBO(selectDB());
        try {
            categoryBO.getCategorie(id).toString();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void showProduct() {
        System.out.println("Informe o id");
        final int id = new Scanner(System.in).nextInt();
        final ProductBO categoryBO = new ProductBO(selectDB());
        try {
            categoryBO.getProduct(id).toString();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSupplier() {
        System.out.println("Informe o id");
        final int id = new Scanner(System.in).nextInt();
        final SuppliersBO categoryBO = new SuppliersBO(selectDB());
        try {
            categoryBO.getSupplier(id).toString();
        } catch (final SQLException e) {
            e.printStackTrace();
        }

    }

    private int selectDB() {
        System.out.println("****************");
        System.out.println("Informe qual bando de dados deseja usar");
        System.out.println("1. MYSQL");
        System.out.println("2. SQLITE");

        return new Scanner(System.in).nextInt();
        
    }

}